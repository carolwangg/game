package levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Biker;
import main.Game;
import utilz.HelpMethods;

public class Level {
	private int[][] lvlData;
	
	private BufferedImage img;
	private ArrayList<Biker> bikers;
	//private int gameXOffset; //only using if need to save progress at levels
	private int tilesWidth;
	private int maxXOffset;
	
	public Level(BufferedImage img) {
		this.img = img;
		createLvlData();
		createEnemies();
		calcLvlOffsets();
	}
	
	private void createLvlData() {
		lvlData = HelpMethods.getLevel(img);		
	}
	public void createEnemies() {
		bikers = HelpMethods.getBikers(img);
	}
	private void calcLvlOffsets() {
		tilesWidth = img.getWidth();
		maxXOffset = (tilesWidth - Game.TILES_IN_WIDTH)*Game.TILE_SIZE;		
	}
	
	public int getBgIndex(int x, int y) {
		return lvlData[y][x];
	}
	public int[][] getLvlData() {
		return lvlData;
	}
	public int getLvlOffset() {
		return maxXOffset;
	}
	public int maxXPos() {
		System.out.println("maxXPos:"+(Game.TILE_SIZE));
		return (tilesWidth*Game.TILE_SIZE);
	}
	public ArrayList getBikers() {
		return bikers;
	}
}
