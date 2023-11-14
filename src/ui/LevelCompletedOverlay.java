package ui;

import static utilz.Constants.UI.Buttons.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import gamestates.GameState;
import gamestates.Playing;
import main.Game;

public class LevelCompletedOverlay {
	
	private LevelCompletedButton menu;
	private LevelCompletedButton next;
	
	private Playing playing;
	private int width = (int)(Game.GAME_WIDTH/2);
	private int height = (int)(Game.GAME_HEIGHT - 50*Game.SCALE);
	private int x = Game.GAME_WIDTH/2 - width/2;
	private int y = (int)(10*Game.SCALE);
	private Font font = FONT_1;
	private Font font2 = font.deriveFont(12*Game.SCALE);
	private FontMetrics metrics;
	private int xFontOffset, yFontOffset;
	Rectangle2D textBounds;
	
	public LevelCompletedOverlay(Playing playing) {
		initButtons();
		this.playing = playing;
	}
	public void initButtons() {
		menu = new LevelCompletedButton(x+(width/2), y+(height/3), "MENU", GameState.MENU);
		next = new LevelCompletedButton(x+(width/2), y+(height/2), "NEXT LEVEL", GameState.PLAYING);
	}
	public void update() {
		menu.update();
		next.update();
	}
	public void draw(Graphics g) {
		backgroundDraw(g);
		menu.draw(g);
		next.draw(g);
	}
	public void backgroundDraw(Graphics g){
		g.setColor(new Color(0,0,0,100));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
		g.setColor(Color.lightGray);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.setFont(font);
		getTextMetrics(g, "LEVEL COMPLETED!", font);
		g.drawString("LEVEL COMPLETED!", x+(width/2)+xFontOffset, y+(height/15)+yFontOffset);		
	}
	public void getTextMetrics(Graphics g, String s, Font font) {
		metrics = g.getFontMetrics(font);
		textBounds = metrics.getStringBounds(s,g);
		xFontOffset =(int)-(textBounds.getWidth()/2);
		yFontOffset = (int)(textBounds.getHeight()-10);
	}
	
	public void mousePressed(MouseEvent e) {
		menu.setMousePress(false);
		next.setMousePress(false);
		if(menu.inBound(e)) {
			menu.setMousePress(true);
		}else if(next.inBound(e)) {
			next.setMousePress(true);
		}	
	}
	public void mouseMoved(MouseEvent e) {
		menu.setMouseHover(false);
		next.setMouseHover(false);
		if(menu.inBound(e)) {
			menu.setMouseHover(true);
		}else if(next.inBound(e)) {
			next.setMouseHover(true);
		}	
	}
	public void mouseReleased(MouseEvent e) {
		if(menu.inBound(e)) {
			menu.applyGamestate();
		}
		if(next.inBound(e)) {
			playing.loadNextLevel();
		}
		menu.resetBounds();
		next.resetBounds();
	}
}
