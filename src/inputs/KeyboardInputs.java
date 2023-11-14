package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.GameState;
import main.GamePanel;
import static utilz.Constants.PlayerConstants.*;

public class KeyboardInputs implements KeyListener{
	private GamePanel gamePanel;
	
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(GameState.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying().keyPressed(e);
			break;
		case MENU:
			gamePanel.getGame().getMenu().keyPressed(e);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(GameState.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying().keyReleased(e);
			break;
		case MENU:
			gamePanel.getGame().getMenu().keyReleased(e);
			break;
		}
	}
	//at 16:12, debug key up and down

}
