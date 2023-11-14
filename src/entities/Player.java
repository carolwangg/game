package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.GRAVITY;
import static utilz.Constants.ANI_SET;
import static utilz.HelpMethods.*;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;


public class Player extends Entity{
	
	private int[][] lvl_data;
	private Playing playing;
	
	//player bug; since we're only checking corners, objects smaller than player will cause problems because player only checks corners for collisions
	private BufferedImage[][] animations;
	private int startAni = -1;
	
	private float defaultPlayerOffsetX = 4;
	private float playerOffsetX = defaultPlayerOffsetX*SCALE*Game.SCALE;
	private float defaultPlayerOffsetY = 14;
	private float playerOffsetY = defaultPlayerOffsetY*SCALE*Game.SCALE;

	private boolean moving = false, attacking = false;
	private int playerDir = RIGHT;
	private float jumpSpeed = -1.8f*Game.SCALE;
	private float fallSpeedAfterCollision = 0.05f*Game.SCALE;
	private boolean up,down,left,right,inAir,jump;
	
	private float attackPower = 10;
	private float healthUnitStrength; //strength of each unit
	public int healthBarUnits;
	
	private final static int DEFAULT_PLAYER_SIZE = 48;
	private final int DEFAULT_HITBOX_WIDTH = 18;
	private final int DEFAULT_HITBOX_HEIGHT = 33;
	private final static float SCALE = 0.85f;
	public final static int PLAYER_SIZE = (int)(DEFAULT_PLAYER_SIZE*SCALE);
	private final int HITBOX_WIDTH = (int)(DEFAULT_HITBOX_WIDTH*SCALE);
	private final int HITBOX_HEIGHT = (int)(DEFAULT_HITBOX_HEIGHT*SCALE);
	
	public Player(float x, float y, float width, float height, Playing playing){
		super(x, y, width, height);
		this.state = IDLE;
		this.airSpeed = 0.0f;
		this.walkSpeed = 0.8f*Game.SCALE;
		this.playing = playing;
		setHealth();
		initHitbox((int)(HITBOX_WIDTH*Game.SCALE),(int)(HITBOX_HEIGHT*Game.SCALE));
		System.out.println("x:"+this.x+", y:"+this.y+", width:"+this.width+", height:"+this.height);
		importImg();
	}
	private void setHealth() {
		maxHealth = 10;
		healthBar = maxHealth; //strength of healthbar
		healthUnitStrength = 2; //strength of each unit
		healthBarUnits = (int) (healthBar / healthUnitStrength);
	}
	private void importImg() {
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS_IDLE);
		BufferedImage img2 = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS_RUN);
		BufferedImage img3 = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS_ATTACK);
		BufferedImage img4 = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS_JUMP);
		animations = new BufferedImage[6][6];//last two are null
		for(int i = 0; i<animations.length;i++) {
			for(int j = 0; j< getSpriteAmount(i)  /*animations[i].length*/  ;j++) {
				//animations[i][j] = img.getSubimage(j*64, i*64, 64, 64);
				if(i == 0) {
					animations[i][j] = img.getSubimage(j*48, 0, 48, 48);
				}else if(i == 1) {
					animations[i][j] = img2.getSubimage(j*48, 0, 48, 48);
				}else if(i == 2) {
					animations[i][j] = img3.getSubimage(j*48, 0, 48, 48);
				}else if(i==3){
					animations[i][j] = img4.getSubimage(j*48, 0, 48, 48);
				}
			}
		}
	}
	private void updateImg() {
		aniTick++;
		if (aniTick>=ANI_SET) {
			aniTick=0;
			aniIndex++;
			if(aniIndex>= getSpriteAmount(state)){
				aniIndex=0;
				attacking = false;
			}
			/*if (state == IDLE) {
				aniIndex = 0;
			}*/
			//System.out.println();
			//why does it not work with that string??^
		}
	}
	public void getPos() {
		if (jump) {
			jump();
		}
		
		moving = false;
		if(!right&&!left&&!inAir) {
			return;
		}
		float tempSpeedX = 0;
		if(right) {
			tempSpeedX += walkSpeed;
		}
		if(left) {
			tempSpeedX -= walkSpeed;
		}
		changeDir(tempSpeedX);
		if(!inAir) {
			if(!entityOnFloor(hitBox, lvl_data)) {
				inAir = true;
			}
		}
		if(inAir) {
			if(checkMovable(x,y+airSpeed, (int) hitBox.width, (int) hitBox.height, lvl_data)) {
				y+=airSpeed;
				airSpeed+=GRAVITY;
			}else {
				if(airSpeed>0) {
					y = getEntityCeilOrFloor(hitBox, airSpeed);
					resetInAir();
				}else {
					y = getEntityCeilOrFloor(hitBox, airSpeed);
					airSpeed = fallSpeedAfterCollision;
				}
			}
		}
		if(attacking) {
			//can't move left or right when attacking
			return;
		}
		updateXPos(tempSpeedX);
		moving = true;
	}
	
	private void changeDir(float tempSpeedX){
		if(tempSpeedX>0) {
			playerDir = RIGHT;
		}else if(tempSpeedX<0) {
			playerDir = LEFT;
		}
		//if its 0, no change
	}
	
	public void resetInAir() {
		inAir = false;
		airSpeed = 0.0f;
	}
	public void jump() {
		if(inAir) {
			if(checkMovable(x-1, y, hitBox.width, hitBox.height, lvl_data)){
				if(checkMovable(x+1, y, hitBox.width, hitBox.height, lvl_data)) {
					return;
				}
			}
		}
		inAir = true;
		airSpeed = jumpSpeed;
	}
	public void updateXPos(float tempSpeedX){
		if(checkMovable(x+tempSpeedX,y, hitBox.width, hitBox.height, lvl_data)) {
			x+=tempSpeedX;
		}else {
			x=getEntityXPosNextToWall(hitBox, tempSpeedX);
		}
	}
	
	private void updateState() {
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
			if(startAni != ATTACKING) {
				aniTick = 0;
				aniIndex = 3;
				return;
			}
		}
		if(startAni!=state) {
			aniTick = 0;
			aniIndex = 0;
		}
	}
	
	public void update() {
		checkDead();
		getPos();
		updateHitbox();
		updateHealthUnits();
		updateState();
		updateImg();
	}
	
	public void render(Graphics g, int gameXOffset) {
		if(playerDir == RIGHT) {
			g.drawImage(animations[state][aniIndex],(int)(this.x-playerOffsetX)-gameXOffset, (int)(this.y-playerOffsetY), (int)this.width, (int)this.height, null);
		}else {
			g.drawImage(animations[state][aniIndex],(int)(this.x+playerOffsetX+hitBox.width)-gameXOffset, (int)(this.y-playerOffsetY), (int)-this.width, (int)this.height, null);
		}
		drawHitbox(g, gameXOffset);
	}
	
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	public float getHitboxX() {
		return hitBox.x;
	}
	public Rectangle2D.Float getHitbox() {
		return hitBox;
	}
	public void loadLvlData(int[][] lvldata) {
		lvl_data = lvldata;
		checkInAir();
	}
	public void checkInAir() {
		if(!entityOnFloor(hitBox, lvl_data)) {
			inAir = true;
		}
	}
	public void resetDirBooleans() {
		up = false;
		down = false;
		left = false;
		right = false;
		jump = false;
	}
	public void resetAll() {
		resetDirBooleans();
		state = IDLE;
		healthBar = maxHealth;
		x = 0f;
		updateHitbox();
		checkInAir();
	}
	public boolean getAttacking(Rectangle2D.Float enemyHitbox) {
		if(attacking) {
			if(entitiesCollided(hitBox, enemyHitbox)) {
				return true;
			}
		}
		return false;
	}
	public void updateHealthUnits() {
		healthBarUnits = (int) Math.ceil(healthBar / healthUnitStrength);
	}
	public void checkDead() {
		if(healthBar==0) {
			state = DEAD;
		}
		if (state == DEAD) {
			playing.setGameOver(true);
		}

	}
	public void changeHealthBar(float value) {
		healthBar+=value;
		if(healthBar < 0) {
			healthBar = 0;
		}else if(healthBar > maxHealth) {
			healthBar = maxHealth;
		}
	}
	public float getAttackPower() {
		return attackPower;
	}
}
