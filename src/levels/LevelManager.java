package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.Game;
import static main.Game.TILES_IN_HEIGHT;
import static main.Game.TILES_IN_WIDTH;
import utilz.LoadSave;

public class LevelManager {
	
	private BufferedImage[] bgRefs;
	private BufferedImage bg;
	private Game game;
	private ArrayList<Level> levels;
	private int lvlIndex = 0;
	private Random rand = new Random();
	private int bgOffset;
	private float bgCount;
	//private Color[] colorT = new Color[120];
		
	public LevelManager(Game game){
		this.game = game;
		loadBgSet();
		levels = new ArrayList<>();
		buildAllLevels();
		//initColor();
	}

	private void buildAllLevels() {
		BufferedImage[] allLevels = LoadSave.getAllLevels();
		for(BufferedImage b : allLevels) {
			levels.add(new Level(b));
		}
	}

	private void initColor() {
		for(int i=0; i<TILES_IN_HEIGHT; i++) {
			for(int j=0; j<TILES_IN_WIDTH; j++) {
				//colorT[i*12+j] = colorRandom();
			}
		}
	}
	
	public void loadBgSet() {
		bgRefs = new BufferedImage[48];
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.BG);
		bg = LoadSave.getSpriteAtlas(LoadSave.LVL_BG);
		for(int i=0; i<4; i++) {
			for(int j=0; j<12; j++) {
				int count = i*12 + j;
				bgRefs[count]= img.getSubimage(j*32, i*32, 32, 32);
			}
		}
	}
	public void update() {
		
	}
	
	public void draw(Graphics g, int gameXOffset) {
		bgCount+=0.15f;
		if(bgCount>=1.0) {
			bgOffset = gameXOffset/6;
			bgCount--;
		}
		g.drawImage(bg, -bgOffset, 0, 1200, 600, null);
		for(int i=0; i<TILES_IN_HEIGHT; i++) {
			for(int j=0; j<levels.get(lvlIndex).getLvlData()[0].length; j++) {
				int index = levels.get(lvlIndex).getBgIndex(j, i);
				g.drawImage(bgRefs[index],((j*Game.TILE_SIZE)-gameXOffset),i*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
				//g.setColor(colorT[i*12+j]);
				g.drawRect((j*Game.TILE_SIZE)-gameXOffset,i*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
			}
		}
	}
	public Level getLevel() {
		return levels.get(lvlIndex);
	}
	/*private Color colorRandom() {
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
		return new Color(r,g,b);
	}*/

	public void getNext() {
		lvlIndex++;
		if(lvlIndex>=levels.size()) {
			lvlIndex = 0;
			System.out.println("game completed!");
		}
	}
	
}
