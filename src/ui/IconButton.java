package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import gamestates.GameState;
import utilz.Constants.UI.Buttons;
import static utilz.Constants.UI.Buttons.*;


public class IconButton extends Button{
	protected int[][] button;
	protected int type;
	protected int xOffset = (int)(ICON_B_SIZE/2);
	protected Rectangle bounds;
	protected Color[] colorF = {Color.white, Color.lightGray, Color.lightGray}; 
	protected Color[] colorP = {Color.black, Color.black, Color.red};
	protected int index = 0;
	protected float UNIT_SCALE;
	
	public IconButton(int x, int y, int type, GameState state) {
		super(x,y,state);
		this.type = type;
		initButton();
		initBounds();
	}
	public void initButton() {
		button = Buttons.getArray(type);
		UNIT_SCALE = (float)ICON_B_SIZE/button.length;
	}
	public void initBounds() {
		bounds = new Rectangle(x-xOffset,y,(int)(ICON_B_SIZE),(int)(ICON_B_SIZE));
	}
	public void draw(Graphics g) {
		for(int i = 0; i<button.length;i++) {
			for(int j = 0; j<button.length;j++) {
				if (button[i][j]==0) {
					g.setColor(colorF[index]);
				}else {
					g.setColor(colorP[index]);
				}
				g.fillRect((int)(x-xOffset + (j*UNIT_SCALE)),(int)(y + (i*UNIT_SCALE)), (int)(Math.ceil(UNIT_SCALE)),(int)(Math.ceil(UNIT_SCALE)));
			}
		}
	}
	public void update() {
		index = 0;
		if(mouseHover) {
			index = 1;
		}
		if(mousePress) {
			index = 2;
		}
	}
	public boolean inBound(MouseEvent e) {
		return bounds.contains(e.getX(),e.getY());
	}
	public void resetBounds() {
		setMouseHover(false);
		setMousePress(false);
	}
}