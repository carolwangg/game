package utilz;

import static utilz.Constants.EnemyConstants.BIKER;
import static utilz.Constants.EnemyConstants.ENEMY_SIZE;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Biker;
import main.Game;

public class HelpMethods {
	
	public static boolean checkMovable(float x, float y, float width, float height, int[][] lvldata){
		if(!(checkSolid(x,y,lvldata))) {
			if(!(checkSolid(x+width,y,lvldata))) {
				if(!(checkSolid(x,y+height,lvldata))) {
					if(!(checkSolid(x+width,y+height,lvldata))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean checkSolid(float x, float y, int[][] lvldata){
		if(x<0 || x>=(24*Game.TILE_SIZE)) {
			return true;
		}else if(y<0 || y>=Game.GAME_HEIGHT) {
			return true;
		}
		int checkX = (int) x/Game.TILE_SIZE;
		int checkY = (int) y/Game.TILE_SIZE;
		
		int value = lvldata[checkY][checkX];
		if(value<=96&&value>=0&&value!=11) {
			return true;
		}
		return false;
	}
	
	public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float tempSpeedX) {
		//the way that the tiles are "drawn", any x/y on the line between two tiles will check the tile to the right/down
		//so only have to -1 for x/y that is travelling right/down;
		int currentPos = (int) (hitbox.x/Game.TILE_SIZE);
		if (tempSpeedX > 0) {
			float xOffset = Game.TILE_SIZE-hitbox.width;
		return (currentPos * Game.TILE_SIZE) + xOffset - 1;
		}else 
			return currentPos*Game.TILE_SIZE; //+ 1;
	}
	
	public static float getEntityCeilOrFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentPos = (int) ((hitbox.y+hitbox.height)/Game.TILE_SIZE);
		if (airSpeed > 0) {
			float yOffset = Game.TILE_SIZE-hitbox.height;
			return (currentPos * Game.TILE_SIZE) + yOffset - 1;
		}else 
			return currentPos*Game.TILE_SIZE; //+ 1;
		
	}
	
	public static boolean entityOnFloor(Rectangle2D.Float hitbox, int[][] lvldata) {
		if(!checkSolid(hitbox.x, hitbox.y+hitbox.height+1, lvldata)) {
			if(!checkSolid(hitbox.x+hitbox.width, hitbox.y+hitbox.height+1, lvldata)) {
				return false;
			}
		}
		return true;		
	}
	
	public static boolean entityOnFloorNext(Rectangle2D.Float hitbox, float xSpeed, int[][] lvldata) {
		if(checkSolid(hitbox.x+xSpeed,hitbox.y+hitbox.height+1,lvldata)) {
			if(checkSolid(hitbox.x+hitbox.width+xSpeed,hitbox.y+hitbox.height+1,lvldata)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean entityOnIsland(Rectangle2D.Float hitbox, float xSpeed, int[][] lvldata) {
		if(!(entityOnFloorNext(hitbox, Game.TILE_SIZE, lvldata))){
			if(!(entityOnFloorNext(hitbox, -Game.TILE_SIZE, lvldata))){
				return true;
			}
		}
		return false;
	}
	
	public static boolean entitiesCollided(Rectangle2D.Float hitbox1, Rectangle2D.Float hitbox2) {
		//hitbox1 always has to be able to fit within hitbox2 or check will not work(bc checks if corners of h1 are in h2)
		if(!(hitbox2.contains(hitbox1.x, hitbox1.y))) {
			if(!(hitbox2.contains(hitbox1.x+hitbox1.width, hitbox1.y))) {
				if(!(hitbox2.contains(hitbox1.x+hitbox1.width, hitbox1.y+hitbox1.height))) {
					if(!(hitbox2.contains(hitbox1.x, hitbox1.y+hitbox1.height))) {
						return false;
					}
				}
			}
		}
		return true;
	}
	//if floats x and y are given, will use them for smaller hitbox
	/*public static boolean entitiesCollided(float x, float y, Rectangle2D.Float hitbox1, Rectangle2D.Float hitbox2) {
		if(!(hitbox2.contains(x, y))) {
			if(!(hitbox2.contains(x+hitbox1.width, y))) {
				if(!(hitbox2.contains(x+hitbox1.width, y+hitbox1.height))) {
					if(!(hitbox2.contains(x, y+hitbox1.height))) {
						return false;
					}
				}
			}
		}
		return true;
	}*/
	
	public static ArrayList<Biker> getBikers(BufferedImage img){
		ArrayList<Biker> bikers = new ArrayList<Biker>();
		for(int i=0; i<img.getHeight(); i++) {
			for(int j=0; j<img.getWidth(); j++) {
				Color color = new Color(img.getRGB(j,i));
				int value = color.getGreen();
				if(value == BIKER) {
					bikers.add(new Biker(j*Game.TILE_SIZE,i*Game.TILE_SIZE,ENEMY_SIZE*Game.SCALE,ENEMY_SIZE*Game.SCALE)); 
				}
			}
		}
		return bikers;
	}
	
	public static int[][] getLevel(BufferedImage img) {
		int[][] level = new int[img.getHeight()][img.getWidth()];
		for(int i=0; i<level.length; i++) {
			for(int j=0; j<level[0].length; j++) {
				Color color = new Color(img.getRGB(j,i));
				level[i][j]= color.getRed();
			}
		}
		return level;
	}
	
}
