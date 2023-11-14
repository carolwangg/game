package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import gamestates.GameState;
import main.Game;

import static utilz.Constants.UI.Buttons.*;

public class TextButton extends Button{
	protected Color[] colors = new Color[3];
	protected int heightNormal;
	protected int heightPress;
	protected int height;
	protected int width;
	protected int index = 0;
	protected int xOffset;
	protected FontMetrics metrics;
	protected int xFontOffset, yFontOffset;
	protected Font font = FONT_1;
	protected float fontScale;
	Rectangle2D textBounds;
	Rectangle bounds[] = new Rectangle[3];
	
	protected int yNormal,yPress;
	protected String type;

	public TextButton(int x, int y, int width, int height, float fontScale, String type, GameState state) {
		super(x,y,state);
		this.width = width;
		this.height = height;
		this.type = type;
		initYs();
		initBounds();
		font = font.deriveFont(fontScale*font.getSize());
	}
	
	public void getTextMetrics(Graphics g) {
		//xOffset = 
		metrics = g.getFontMetrics(font);
		textBounds = metrics.getStringBounds(type,g);
		xFontOffset =(int)-(textBounds.getWidth()/2);
		yFontOffset = (int) ((height/2)+(textBounds.getHeight()/2));
	}
	
	public void initBounds() {
		for(int i=0;i<bounds.length;i++) {
			bounds[i] = new Rectangle(x-xOffset,y,width,height);
		}
	}
	public void initYs() {
		yNormal = y;
		yPress = y+20;
		xOffset = width/2;
	}
	
	public void update() {
		index = 0;
		if(mouseHover) {
			index = 1;
		}
		if(mousePress) {
			index = 2;
			y = yPress;
			height = heightPress;
		}
	}
	
	public void draw(Graphics g) {
		getTextMetrics(g);
		g.setColor(colors[index]);
		g.fillRect(x-xOffset,y,width,height);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(type, x+xFontOffset, y+yFontOffset);
	}
	
	public void resetBounds() {
		setMouseHover(false);
		setMousePress(false);
		y = yNormal;
		height = heightNormal; 
	}
	
	public boolean inBound(MouseEvent e) {
		return bounds[index].contains(e.getX(),e.getY());
	}
}