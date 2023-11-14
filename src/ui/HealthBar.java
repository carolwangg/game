package ui;
import static utilz.Constants.UI.Buttons.*;

import java.awt.Color;
import java.awt.Graphics;

import entities.Player;
import main.Game;
import utilz.Constants.UI.Buttons;

public class HealthBar {
	private float x, y;
	private int amount;
	private HealthUnit[] units;
	public HealthBar(float x, float y, int amount) {
		this.x = x;
		this.y = y;
		this.amount = amount;
		initUnits();
	}
	private void initUnits() {
		units = new HealthUnit[amount];
		float unitOffset = 0;
		float spaceBetween = 4*Game.SCALE;
		for(int i = 0; i< amount; i++) {
			units[i] = new HealthUnit(x + unitOffset, y);
			unitOffset += (units[i].width + spaceBetween);
			System.out.println(units[i].width);
		}
	}
	public void update(int amount) {
		this.amount = amount;
	}
	public void draw(Graphics g) {
		for(int i = 0; i<amount; i++) {
			units[i].draw(g);
		}
	}
}
class HealthUnit{
	private int[][] unit;
	private float x, y;
	private float pixelWidth = (float)(1.6*Game.SCALE);
	private float pixelHeight = (float)(1.6*Game.SCALE);
	public float width, height;
	public HealthUnit(float x, float y) {
		unit = Buttons.getArray(TYPE_HEALTH_BAR_UNIT);
		this.x = x;
		this.y = y;
		initInts();
	}
	private void initInts() {
		width = pixelWidth * unit[0].length;
		height = pixelHeight * unit.length;
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i<unit.length;i++) {
			for(int j = 0; j<unit[0].length;j++) {
				if (unit[i][j] == 0) {
					g.setColor(Color.white);
				}else {
					g.setColor(Color.black);
				}
				g.fillRect((int)(x + pixelWidth*j), (int)(y + pixelHeight*i), (int)(pixelWidth), (int)(pixelHeight));
			}
		}
	}
}
