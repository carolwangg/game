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

public class MenuButton extends TextButton{
	
	public MenuButton(int x, int y, String type, GameState state) {
		super(x, y, MENU_B_WIDTH, MENU_B_HEIGHT, 1f, type, state);
		colors[0] = Color.WHITE;
		colors[1] = Color.GRAY;
		colors[2] = Color.RED;
		heightNormal = MENU_B_HEIGHT;
		heightPress = MENU_B_HEIGHT-20;
		
	}
}
