package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import main.Game;

public abstract class Entity {
	protected float x, y;
	protected float width, height;
	protected Rectangle2D.Float hitBox;
	protected int aniTick, aniIndex = 0;
	protected int state;
	protected float airSpeed, walkSpeed;
	protected float healthBar, maxHealth;
	protected Entity(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	protected void initHitbox(int width, int height){
		hitBox = new Rectangle2D.Float(this.x, this.y, width, height);
	}
	protected void updateHitbox(){
		hitBox.x = (int) this.x;
		hitBox.y = (int) this.y;
	}
	protected void drawHitbox(Graphics g, int gameXOffset){
		g.setColor(Color.RED);
		g.drawRect((int)hitBox.x - gameXOffset,(int)hitBox.y,(int)hitBox.width,(int)hitBox.height);
	}
}