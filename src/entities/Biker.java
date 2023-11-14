package entities;

import static utilz.Constants.EnemyConstants.BIKER;
import static utilz.Constants.EnemyConstants.getHitboxHeight;
import static utilz.Constants.EnemyConstants.getHitboxWidth;
import static utilz.Constants.EnemyConstants.RIGHT;
import static utilz.Constants.EnemyConstants.getSpriteAmount;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Biker extends Enemy{
	private BufferedImage[][] animations;
	public Biker(float x, float y, float width, float height) {
		super(x, y, width, height, BIKER);
		initHitbox((int)(getHitboxWidth(BIKER)*Game.SCALE),(int)(getHitboxHeight(BIKER)*Game.SCALE));
		initBoxes();
		offsetPos(); //puts enemy on ground and randomizes x pos, might remove 2nd part if too much runtime

		maxHealth = 10;
		healthBar = maxHealth;
		attackPower = 2;
		attackIndex = 2;
		importImg();
	}
	private void importImg() {
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.ENEMY_ATLAS_IDLE);
		BufferedImage img2 = LoadSave.getSpriteAtlas(LoadSave.ENEMY_ATLAS_RUN);
		BufferedImage img3 = LoadSave.getSpriteAtlas(LoadSave.ENEMY_ATLAS_ATTACK_2);
		BufferedImage img4 = LoadSave.getSpriteAtlas(LoadSave.ENEMY_ATLAS_JUMP);
		BufferedImage img5 = LoadSave.getSpriteAtlas(LoadSave.ENEMY_ATLAS_DEAD);
		animations = new BufferedImage[5][6];
		System.out.println("animationlength:"+animations.length);
		for(int i = 0; i<animations.length;i++) {
			for(int j = 0; j<getSpriteAmount(BIKER, i);j++) {
				if(i == 0) {
					animations[i][j] = img.getSubimage(j*48, 0, 48, 48);
				}else if(i == 1) {
					animations[i][j] = img2.getSubimage(j*48, 0, 48, 48);
				}else if(i == 2) {
					animations[i][j] = img3.getSubimage(j*48, 0, 48, 48);
				}else if(i == 3){
					animations[i][j] = img4.getSubimage(j*48, 0, 48, 48);
				}else{
					animations[i][j] = img5.getSubimage(j*48, 0, 48, 48);
				}
			}
		}
	}
	@Override
	public void render(Graphics g, int gameXOffset) {
		if(enemyDir == RIGHT) {
			g.drawImage(animations[state][aniIndex],(int)(this.x-enemyOffsetX)-gameXOffset, (int)(this.y-enemyOffsetY), (int)this.width, (int)this.height, null);
		}else {
			g.drawImage(animations[state][aniIndex],(int)(this.x+enemyOffsetX+hitBox.width)-gameXOffset, (int)(this.y-enemyOffsetY), (int)-this.width, (int)this.height, null);
		}
		drawHitbox(g, gameXOffset);
	}
}
