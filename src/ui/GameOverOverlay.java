package ui;

import static utilz.Constants.UI.Buttons.FONT_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import gamestates.GameState;
import gamestates.Playing;
import main.Game;

public class GameOverOverlay {
	private Playing playing;
	private int width = (int)(Game.GAME_WIDTH);
	private int height = (int)(Game.GAME_HEIGHT);
	private Font font = FONT_1;
	private Font font2 = font.deriveFont(12*Game.SCALE);
	private FontMetrics metrics;
	private int xFontOffset, yFontOffset;
	Rectangle2D textBounds;
	
	public GameOverOverlay(Playing playing) {
		this.playing = playing;
	}
	public void draw(Graphics g) {
		g.setColor(new Color(0,0,0,100));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
		g.setColor(Color.white);
		g.setFont(font);
		getTextMetrics(g, "GAME ENDED", font);
		g.drawString("GAME ENDED", (width/2)+xFontOffset, (height/15)+yFontOffset);
		g.setFont(font2);
		getTextMetrics(g, "You died! Press ESC to replay", font2);
		g.drawString("You died! Press ESC to replay", (width/2)+xFontOffset, (height/8)+yFontOffset);
	}
	
	public void getTextMetrics(Graphics g, String s, Font font) {
		metrics = g.getFontMetrics(font);
		textBounds = metrics.getStringBounds(s,g);
		xFontOffset =(int)-(textBounds.getWidth()/2);
		yFontOffset = (int)(textBounds.getHeight()-10);
	}
	
	public void update() {
		
	}
	
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			playing.reset();
			break;
		}
	}
}
