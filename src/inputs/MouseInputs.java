package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.GameState;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener{
	private GamePanel gamePanel;
	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch(GameState.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseMoved(e);
			break;
		case MENU:
			gamePanel.getGame().getMenu().mouseMoved(e);
			break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//gamePanel.setRectPos(e.getX(), e.getY());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch(GameState.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying().mousePressed(e);
			break;
		case MENU:
			gamePanel.getGame().getMenu().mousePressed(e);
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch(GameState.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseReleased(e);
			break;
		case MENU:
			gamePanel.getGame().getMenu().mouseReleased(e);
			break;
		}		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
