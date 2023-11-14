package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.GRAVITY;
import static utilz.Constants.ANI_SET;
import static utilz.HelpMethods.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import main.Game;

public class Enemy extends Entity{
	
	protected float attackPower;
	protected Rectangle2D.Float sightHitbox;
	protected Rectangle2D.Float attackHitbox;
	protected float sightBoxOffset;
	protected float attackBoxOffsetX = 22*Game.SCALE;
	protected float attackBoxOffsetY = 4*Game.SCALE;
	protected float attackBoxWidth = 14*Game.SCALE;
	protected float attackBoxHeight = 12*Game.SCALE;
	private boolean firstCheck = true;
	protected int enemyType;
	public float xSpeed;
	public int enemyDir = LEFT;
	private final int ENEMY_FPS = 6;
	protected int startAni = -1;
	protected boolean moving, attacking, attack, attackChecked, inAir, dead;
	protected boolean active = true;
	protected int attackIndex;
	
	protected float defaultEnemyOffsetX = 7;
	protected float enemyOffsetX = defaultEnemyOffsetX*SCALE*Game.SCALE;
	protected float defaultEnemyOffsetY = 14;
	protected float enemyOffsetY = defaultEnemyOffsetY*SCALE*Game.SCALE;
	
	private Random rand = new Random();
	
	public Enemy(float x, float y, float width, float height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		this.walkSpeed = 0.2f*Game.SCALE;
	}
	protected void initBoxes() {
		sightBoxOffset = (int)(hitBox.width/1.2);
		sightHitbox = new Rectangle2D.Float(hitBox.x+sightBoxOffset, hitBox.y-10, hitBox.width+walkSpeed*120, hitBox.height+20);
		attackHitbox = new Rectangle2D.Float(hitBox.x+attackBoxOffsetX, hitBox.y+attackBoxOffsetY, attackBoxWidth, attackBoxHeight);
	}
	protected void offsetPos() {
		//put enemy on ground
		y += (Game.TILE_SIZE-(hitBox.height+1));
		System.out.println("hitBox.height:"+hitBox.height);
		//center enemy
		x += rand.nextInt(2)*(Game.TILE_SIZE/3);
		updateHitbox();
	}
	private void checkOnIsland(int[][] lvldata) {
		if(entityOnIsland(hitBox, xSpeed, lvldata)) {
			moving = false;
		}else {
			moving = true;
		}
	}
	public void update(int lvldata[][], Rectangle2D.Float playerHitbox) {
		updateMove(lvldata);
		updateHitbox();
		updateSightbox();
		updateAttackbox();
		getState();
		updateImg();
		updateAttack(playerHitbox);
		updateActive();
	}
	
	public void render(Graphics g, int gameXOffset) {
	}
	private void updateImg() {
		aniTick++;
		if (aniTick>=ANI_SET) {
			aniTick=0;
			aniIndex++;
			attack = false;
			if(aniIndex>=getSpriteAmount(enemyType, state)){
				aniIndex=0;
				dead = false;
			}
		}
		if(state == ATTACKING && aniIndex == attackIndex) {
			if(attackChecked == false) {
				attack = true; 
				attackChecked = true;
			}else {
				attack = false;
			}
		}else {
			attackChecked = false;
		}
//		System.out.println("attack:"+attack);
//		System.out.println("attackChecked:"+attackChecked);

	}
	private void updateActive() {
		if(state == DEAD && dead == false) {
			active = false;
		}
	}
	private void updateMove(int[][] lvldata) {
		if(firstCheck) {
			checkOnIsland(lvldata);
			firstCheck = false;
		}
		if(moving) {
			if(enemyDir == LEFT) {
				xSpeed = -walkSpeed;
			}else{
				xSpeed = walkSpeed;
			}
			if(checkMovable(x+xSpeed, y, (int) hitBox.width, (int) hitBox.height, lvldata)){
				if(entityOnFloorNext(hitBox, xSpeed, lvldata)){
					x+=xSpeed;
					return;
				}
			}
			changeDir();
		}
	}
	private void updateSightbox() {
		if(enemyDir == LEFT) {
			sightHitbox.x = x +(hitBox.width-sightHitbox.width) - sightBoxOffset;
			sightHitbox.y = y-10;
		}else {
			sightHitbox.x = x + sightBoxOffset;
			sightHitbox.y = y-10;
		}
	}
	private void updateAttackbox() {
		if(enemyDir == LEFT) {
			attackHitbox.x = x +(hitBox.width-attackHitbox.width) - attackBoxOffsetX;
			attackHitbox.y = y + attackBoxOffsetY;
		}else {
			attackHitbox.x = x + attackBoxOffsetX;
			attackHitbox.y = y + attackBoxOffsetY;
		}
	}
	private void updateAttack(Rectangle2D.Float playerHitbox) {
		if(entitiesCollided(playerHitbox,sightHitbox)) {
			attacking = true;
		}else {
			attacking = false;
		}
	}
	
	private void changeDir() {
		if(enemyDir == LEFT) {
			enemyDir = RIGHT;
		}else {
			enemyDir = LEFT;
		}
	}
	
	public void getState() {
		if(dead) {
			return;
		}
		startAni = state;
		if(moving) {
			if(inAir) {
				state = JUMPING;
			}
			state = RUNNING;
		}else {
			state = IDLE;
		}
		if(attacking) {
			state = ATTACKING;
		}
		if(healthBar<=0) {
			state = DEAD;
			dead = true;
			moving = false;
		}
		if(startAni!=state) {
			if(!(startAni == RUNNING && state == ATTACKING)) {
				aniTick = 0;
				aniIndex = 0;
			}	
		}
	}
	
	protected void drawHitbox(Graphics g, int gameXOffset){
		g.setColor(Color.RED);
		g.drawRect((int)hitBox.x - gameXOffset,(int)hitBox.y,(int)hitBox.width,(int)hitBox.height);
		g.drawRect((int)sightHitbox.x - gameXOffset,(int)sightHitbox.y,(int)sightHitbox.width,(int)sightHitbox.height);
		g.drawRect((int)attackHitbox.x - gameXOffset,(int)attackHitbox.y,(int)attackHitbox.width,(int)attackHitbox.height);
	}
	
	public void changeHealthBar(float value) {
		healthBar+=value;
		if(healthBar < 0) {
			healthBar = 0;
		}else if(healthBar > maxHealth) {
			healthBar = maxHealth;
		}
	}
	
	//getters and setters
	public boolean getAttacking(Rectangle2D.Float playerHitbox) {
		if(attack) {
			if(entitiesCollided(attackHitbox, playerHitbox)) {
				return true;
			}
		}
		return false;
	}
	public float getAttackPower() {
		return attackPower;
	}
	public boolean isActive() {
		return active;
	}
	
	public void reset() {
		aniTick = 0;
		aniIndex = 0;
		firstCheck = true;
		state = IDLE;
		active = true;
		healthBar = maxHealth;
	}
	
}
