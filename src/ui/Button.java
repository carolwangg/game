package ui;

import java.awt.event.MouseEvent;

import gamestates.GameState;

public class Button {
	int x, y;
	GameState state;
	boolean mouseHover,mousePress = false;

	public Button(int x, int y, GameState state) {
		this.x = x;
		this.y = y;
		this.state = state;
	}
	public boolean inBound() {
		return false;
	}
	public void applyGamestate() {
		GameState.state = state;
	}
	
	public boolean isMouseHover() {
		return mouseHover;
	}

	public void setMouseHover(boolean mouseHover) {
		this.mouseHover = mouseHover;
	}

	public boolean isMousePress() {
		return mousePress;
	}

	public void setMousePress(boolean mousePress) {
		this.mousePress = mousePress;
	}
	
}