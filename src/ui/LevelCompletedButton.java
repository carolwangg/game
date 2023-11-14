package ui;

import static utilz.Constants.UI.Buttons.FONT_1;
import static utilz.Constants.UI.Buttons.MENU_B_HEIGHT;
import static utilz.Constants.UI.Buttons.MENU_B_WIDTH;

import java.awt.Color;

import gamestates.GameState;

public class LevelCompletedButton extends TextButton{

	public LevelCompletedButton(int x, int y, String type, GameState state) {
		super(x, y, MENU_B_WIDTH/2, MENU_B_HEIGHT/2, 0.5f, type, state);
		colors[0] = Color.WHITE;
		colors[1] = Color.GRAY;
		colors[2] = Color.GRAY;
		heightNormal = MENU_B_HEIGHT/2;
		heightPress = MENU_B_HEIGHT/2;
	}
}
