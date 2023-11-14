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

public class PauseOverlay {
	
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
	
	private SoundButton musicButton;
	private SoundButton sfxButton;
	private int soundXOffset = (int)(x+(width/4));
	private int musicYOffset = (int)(y+height/5);
	private int sfxYOffset =(int)(y+height/3);
	
	private IconButton playButton;
	private IconButton resetButton;
	private IconButton menuButton;
	private int iconYOffset = (int)(y+height/2);
	private int playXOffset = (int)(x-(width/4));
	private int resetXOffset = (int)x;
	private int menuXOffset = (int)(x+(width/4));

	public PauseOverlay(Playing playing) {
		initButtons();
		this.playing = playing;
	}
	public void initButtons() {
		musicButton = new SoundButton(x+soundXOffset, y+musicYOffset, TYPE_SOUND_ICON, GameState.PLAYING);
		sfxButton = new SoundButton(x+soundXOffset, y+sfxYOffset, TYPE_MUTE_ICON, GameState.PLAYING);
		playButton = new IconButton(x+playXOffset, y+iconYOffset, TYPE_PLAY_ICON, GameState.PLAYING);
		resetButton = new IconButton(x+resetXOffset, y+iconYOffset, TYPE_RESET_ICON, GameState.PLAYING);
		menuButton = new IconButton(x+menuXOffset, y+iconYOffset, TYPE_MENU_ICON, GameState.MENU);
	}
	public void update() {
		musicButton.update();
		sfxButton.update();
		playButton.update();
		resetButton.update();
		menuButton.update();
	}
	public void draw(Graphics g) {
		backgroundDraw(g);
		musicButton.draw(g);
		sfxButton.draw(g);
		playButton.draw(g);
		resetButton.draw(g);
		menuButton.draw(g);
	}
	public void backgroundDraw(Graphics g){
		g.setColor(new Color(0,0,0,100));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
		g.setColor(Color.lightGray);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.setFont(font);
		getTextMetrics(g, "GAME PAUSED", font);
		g.drawString("GAME PAUSED", x+(width/2)+xFontOffset, y+(height/15)+yFontOffset);
		g.setFont(font2);
		getTextMetrics(g, "MUSIC", font2);
		g.drawString("MUSIC", x+(width/3)+xFontOffset, y+musicYOffset+yFontOffset);
		getTextMetrics(g, "SFX", font2);
		g.drawString("SFX", x+(width/3)+xFontOffset, y+sfxYOffset+yFontOffset);
	}
	public void getTextMetrics(Graphics g, String s, Font font) {
		metrics = g.getFontMetrics(font);
		textBounds = metrics.getStringBounds(s,g);
		xFontOffset =(int)-(textBounds.getWidth()/2);
		yFontOffset = (int)(textBounds.getHeight()-10);
	}
	public void mousePressed(MouseEvent e) {
		if(musicButton.inBound(e)) {
			musicButton.setMousePress(true);
		}else if(sfxButton.inBound(e)) {
			sfxButton.setMousePress(true);
		}else if(playButton.inBound(e)) {
			playButton.setMousePress(true);
		}else if(menuButton.inBound(e)) {
			menuButton.setMousePress(true);
		}else if(resetButton.inBound(e)) {
			resetButton.setMousePress(true);
		}
	}
	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseHover(false);
		sfxButton.setMouseHover(false);
		sfxButton.setMouseHover(false);
		playButton.setMouseHover(false);
		menuButton.setMouseHover(false);
		resetButton.setMouseHover(false);
		if(musicButton.inBound(e)) {
			musicButton.setMouseHover(true);
		}else if(sfxButton.inBound(e)) {
			sfxButton.setMouseHover(true);
		}else if(playButton.inBound(e)) {
			playButton.setMouseHover(true);
		}else if(menuButton.inBound(e)) {
			menuButton.setMouseHover(true);
		}else if(resetButton.inBound(e)) {
			resetButton.setMouseHover(true);
		}
	}
	public void mouseReleased(MouseEvent e) {
		if(musicButton.inBound(e)) {
			musicButton.setMuted(!musicButton.isMuted());
		}else if(sfxButton.inBound(e)) {
			sfxButton.setMuted(!sfxButton.isMuted());
		}else if(playButton.inBound(e)) {
			playing.setPaused(!playing.getPaused());
		}else if(menuButton.inBound(e)) {
			playing.setPaused(false);
			menuButton.applyGamestate();
		}else if(resetButton.inBound(e)) {
			playing.reset();
		}
		musicButton.resetBounds();
		sfxButton.resetBounds();
		playButton.resetBounds();
		menuButton.resetBounds();
		resetButton.resetBounds();
	}
}
