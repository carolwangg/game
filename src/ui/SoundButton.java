package ui;

import gamestates.GameState;
import static utilz.Constants.UI.Buttons.*;

import java.awt.Color;

public class SoundButton extends IconButton{
	private boolean muted = false;
	
	public SoundButton(int x, int y, int type, GameState state) {
		super(x, y, type, state);
		changeColors();
	}
	public void changeColors() {
		colorP[2] = Color.black;
	}
	public void setMuted(boolean muted) {
		this.muted = muted;
	}
	public boolean isMuted() {
		return muted;
	}
	public void update() {
		index = 0;
		if(mouseHover) {
			index = 1;
		}
		if(mousePress) {
			index = 2;
		}
		if(muted) {
			button = getArray(TYPE_MUTE_ICON);
		}else {
			button = getArray(TYPE_SOUND_ICON);
		}
	}
	
}
