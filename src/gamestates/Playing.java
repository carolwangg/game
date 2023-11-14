package gamestates;

import static utilz.Constants.UI.Buttons.ICON_B_SIZE;
import static utilz.Constants.UI.Buttons.TYPE_PAUSE_ICON;
import static entities.Player.PLAYER_SIZE;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import entities.EnemyManager;
import levels.Level;
import levels.LevelManager;
import main.Game;
import ui.GameOverOverlay;
import ui.HealthBar;
import ui.IconButton;
import ui.LevelCompletedOverlay;
import ui.PauseOverlay;

public class Playing extends State implements StateMethods {
	private Player player;
	private LevelManager lm;
	private EnemyManager em;
	private HealthBar healthBar;
	private IconButton pauseButton;
	private int pauseButtonOffsetX = (int)(ICON_B_SIZE + 10*game.SCALE);
	private int pauseButtonOffsetY = (int)(10*game.SCALE);
	private PauseOverlay pauseOverlay;
	private GameOverOverlay gameOverOverlay;
	private LevelCompletedOverlay lvlCompletedOverlay;
	private boolean paused, gameOver, completed = false;
	
	private int gameXOffset;
	private int maxXOffset;
	private int maxXPos;
	
	private int leftBound = Game.GAME_WIDTH/2;
	private int rightBound = Game.GAME_WIDTH/2;
	
	public Playing(Game game)  {
		super(game);
		player = new Player(90*game.SCALE,90f*game.SCALE,PLAYER_SIZE*game.SCALE,PLAYER_SIZE*game.SCALE, this);
		lm = new LevelManager(game);
		em = new EnemyManager(this);
		healthBar = new HealthBar(20*game.SCALE, 10*game.SCALE, player.healthBarUnits);
		player.loadLvlData(lm.getLevel().getLvlData());
		
		getLvlOffsets();
		getEnemies();
		
		initButtons();
		initOverlays();
	}
	private void getEnemies() {
		em.initEnemies(lm.getLevel());
	}
	private void getLvlOffsets() {
		maxXOffset = lm.getLevel().getLvlOffset();
		maxXPos = lm.getLevel().maxXPos();
	}
	public void initButtons() {
		pauseButton = new IconButton(Game.GAME_WIDTH - pauseButtonOffsetX,0+pauseButtonOffsetY,TYPE_PAUSE_ICON, GameState.PLAYING);
	}
	public void initOverlays() {
		pauseOverlay = new PauseOverlay(this);
		gameOverOverlay = new GameOverOverlay(this);
		lvlCompletedOverlay = new LevelCompletedOverlay(this);
	}
	@Override
	public void update() {
		if(gameOver) {
			gameOverOverlay.update();
		}else if(completed){
			lvlCompletedOverlay.update();
		}else if(paused) {
			pauseOverlay.update();
		}else {
			player.update();
			lm.update();
			em.update(lm.getLevel().getLvlData(), player);
			healthBar.update(player.healthBarUnits);
			checkBound();
		}
		pauseButton.update();
	}
	@Override
	public void draw(Graphics g) {
		if(gameOver) {
			gameOverOverlay.draw(g);
		}else {
			lm.draw(g, gameXOffset);
			em.draw(g, gameXOffset);
			player.render(g, gameXOffset);
			healthBar.draw(g);
			pauseButton.draw(g);
			if(completed){
				lvlCompletedOverlay.draw(g);
			}else if(paused) {
				pauseOverlay.draw(g);
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(gameOver) {
			gameOverOverlay.keyReleased(e);
		}else {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				player.setUp(false);
				break;
			case KeyEvent.VK_A:
				player.setLeft(false);
				break;
			case KeyEvent.VK_S:
				player.setDown(false);
				break;
			case KeyEvent.VK_D:
				player.setRight(false);
				break;
			case KeyEvent.VK_SPACE:
				player.setJump(false);
				break;
			}
		}	
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			System.out.println("W");
			player.setUp(true);
			break;
		case KeyEvent.VK_A:
			System.out.println("A");
			player.setLeft(true);
			break;
		case KeyEvent.VK_S:
			System.out.println("S");
			player.setDown(true);
			break;
		case KeyEvent.VK_D:
			System.out.println("D");
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			System.out.println("space");
			player.setJump(true);
			break;
		case KeyEvent.VK_F:
			System.out.println("f");
			player.setAttacking(true);
			break;
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(pauseButton.inBound(e)) {
			pauseButton.setMousePress(true);
		}
		if(paused) {
			pauseOverlay.mousePressed(e);
		}else if(completed) {
			lvlCompletedOverlay.mousePressed(e);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		pauseButton.setMouseHover(false);
		if(pauseButton.inBound(e)) {
			pauseButton.setMouseHover(true);
		}	
		if(paused) {
			pauseOverlay.mouseMoved(e);
		}else if(completed) {
			lvlCompletedOverlay.mouseMoved(e);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(pauseButton.inBound(e)) {
			paused = !paused;
		}
		pauseButton.resetBounds();
		if(paused) {
			pauseOverlay.mouseReleased(e);
		}else if(completed) {
			lvlCompletedOverlay.mouseReleased(e);
		}
	}
	
	//getters and setters 
	public Player getPlayer() {
		return player;
	}
	public boolean getPaused() {
		return paused;
	}
	public void loadNextLevel() {
		lm.getNext();
		reset();
		updateLvlData();
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	//separate methods
	public void reset() {
		player.resetAll();
		em.reset(lm.getLevel());
		System.out.println(lm.getLevel().getBikers().get(0));
		paused = false;
		gameOver = false;
		completed = false;
	}
	
	public void updateLvlData() {
		//update lvldata in player, enemies, and maxLvlOffset
		player.loadLvlData(lm.getLevel().getLvlData()); 
		maxXOffset = lm.getLevel().getLvlOffset();
		maxXPos = lm.getLevel().maxXPos();
	}
	
	//update methods
	private void checkBound() {
		int currentX = (int) (player.getHitboxX() - gameXOffset);
		if(currentX > rightBound) {
			gameXOffset += currentX - rightBound;
		}else if(currentX < leftBound) {
			gameXOffset += currentX - leftBound;
		}
		if(gameXOffset<0) {
			gameXOffset = 0;
		}else if(gameXOffset>maxXOffset) {
			gameXOffset = maxXOffset;
		}
		if(player.getHitboxX() == (maxXPos-player.getHitbox().width-1)) {
			completed = true;
		}
	}
	
}
