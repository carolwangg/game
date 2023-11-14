package main;

import java.awt.Graphics;

import gamestates.GameState;
import gamestates.Playing;
import gamestates.Menu;


public class Game implements Runnable {
	
	private GameFrame gameframe;
	private GamePanel gamepanel;
	private Thread gameThread;
	public static final int FPS_SET = 120;
	public static final int UPS_SET = 200;
	
	private Playing playing;
	private Menu menu;
	
	public final static int DEFAULT_TILE_SIZE = 30;
	public final static float SCALE = 2.5f;
	public final static int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
	public final static int TILES_IN_WIDTH = 12;
	public final static int TILES_IN_HEIGHT = 10;
	public final static int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;
	
	public Game() {
		initClasses();
		gamepanel = new GamePanel(this);
		gameframe = new GameFrame(gamepanel);
		gamepanel.setFocusable(true);
		gamepanel.requestFocus();
		startGameLoop();
	}
	
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	private void initClasses() {
		playing = new Playing(this);
		menu = new Menu(this);
	} 
	
	public void update() {
		switch(GameState.state) {
			case PLAYING:
				playing.update();
				break;
			case MENU:
				menu.update();
				break;
			case QUIT:
			default:
				System.exit(0);
				break;
		}
	}
	public void render(Graphics g) {
		switch(GameState.state) {
		case PLAYING:
			playing.draw(g);
			break;
		case MENU:
			menu.draw(g);
			break;
		default:
			break;
		}
	}
	
	
	public void windowFocusLost() {
		if(GameState.state == GameState.PLAYING) {
			playing.getPlayer().resetDirBooleans();
		}
	}
	public Playing getPlaying() {
		return playing;
	}

	public Menu getMenu() {
		return menu;
	}
	

	public void run() {
		
		double timePerFrame = 1000000000.0/FPS_SET; //1 nano = 10^-9 seconds
		double timePerUpdate = 1000000000.0/UPS_SET;
		long previousTime = System.nanoTime();
		long currentTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		double deltaU = 0;
		double deltaF = 0;
		long lastCheck = System.currentTimeMillis();
		
		while(true) {
			currentTime = System.nanoTime();
			
			deltaU += (currentTime-previousTime)/timePerUpdate;
			deltaF += (currentTime-previousTime)/timePerFrame;
			
			previousTime = currentTime;
			
			if (deltaU >=1) {
				updates++;
				deltaU--;
				update();
			}
			
			if (deltaF >=1) {
				frames++;
				deltaF--;
				gamepanel.repaint();
			}
			
			if((System.currentTimeMillis()-lastCheck) >= 1000) {
				//System.out.println("FPS:"+frames);
				//System.out.println("UPS:" + updates);
				frames = 0;
				updates = 0;
				lastCheck = System.currentTimeMillis();
			}
		}
	}
	
}
