package main;
import java.awt.Dimension;
import java.awt.Graphics;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;


public class GamePanel extends JPanel{
	private MouseInputs mouseinputs;
	private int frames = 0;
	private long lastFrame = 0;
	private Game game;

	public GamePanel(Game game) {
		this.game = game;
		setPanelSize();
		mouseinputs = new MouseInputs(this);
		addKeyListener(new KeyboardInputs(this));		
		addMouseListener(mouseinputs);
		addMouseMotionListener(mouseinputs);
	}
	public Game getGame() {
		return game;
	}
	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);//4096,3215
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
	}
	
	public void getFPS() {
		if((System.currentTimeMillis()-lastFrame) >= 1000) {
			System.out.println("FPS:"+frames);
			frames = 0;
			lastFrame = System.currentTimeMillis();
		}
		frames++;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //makes sure JPanel clears the canvas/finishes what it needs to do before it starts painting
		game.render(g);
	}
}