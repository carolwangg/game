package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;
import ui.IconButton;
import static utilz.Constants.UI.Buttons.*;

public class Menu extends State implements StateMethods{
	private MenuButton[] button = new MenuButton[2];
	
	public Menu(Game game) {
		super(game);
		initButtons();
	}
	public void initButtons() {
		button[0] = new MenuButton(Game.GAME_WIDTH/2, Game.GAME_HEIGHT/5, "PLAY", GameState.PLAYING);
		button[1] = new MenuButton(Game.GAME_WIDTH/2, Game.GAME_HEIGHT/2, "QUIT", GameState.QUIT);
	}
	
	@Override
	public void update() {
		for(MenuButton mb: button) {
			mb.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		for(MenuButton mb: button) {
			mb.draw(g);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SHIFT:
			GameState.state = GameState.PLAYING;
		break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(MenuButton mb:button) {
			mb.setMousePress(false);
		}
		for(MenuButton mb:button) {
			if(mb.inBound(e)) {
				mb.setMousePress(true);
			}
		}	
	}
	public void mouseMoved(MouseEvent e) {
		for(MenuButton mb:button) {
			mb.setMouseHover(false);
		}
		for(MenuButton mb:button) {
			if(mb.inBound(e)) {
				mb.setMouseHover(true);
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		for(MenuButton mb:button) {
			if(mb.inBound(e)) {
				mb.applyGamestate();
			}
		}		
		for(MenuButton mb:button) {
			mb.resetBounds();
		}	
	}
}
