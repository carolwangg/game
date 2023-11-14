package utilz;

import static utilz.Constants.EnemyConstants.BIKER;
import static utilz.Constants.EnemyConstants.ENEMY_SIZE;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Biker;
import main.Game;

public class LoadSave {
	public static final String PLAYER_ATLAS_IDLE = "Cyborg_idle.png";
	public static final String PLAYER_ATLAS_RUN = "Cyborg_run.png";
	public static final String PLAYER_ATLAS_ATTACK = "Cyborg_attack1.png";
	public static final String PLAYER_ATLAS_JUMP = "Cyborg_jump.png";
	
	public static final String ENEMY_ATLAS_IDLE = "Biker_idle.png";
	public static final String ENEMY_ATLAS_RUN = "Biker_run.png";
	public static final String ENEMY_ATLAS_ATTACK_1 = "Biker_attack1.png";
	public static final String ENEMY_ATLAS_ATTACK_2 = "Biker_run_attack.png";
	public static final String ENEMY_ATLAS_JUMP = "Biker_jump.png";
	public static final String ENEMY_ATLAS_DEAD = "Biker_death.png";
	
	public static final String BG = "pixil-frame-0.png";//"outside_sprites.png";
	public static final String LEVEL_1 = "level_1.png";
	public static final String LVL_BG = "bg-image.png";
	
	public static BufferedImage getSpriteAtlas(String sprite) {
		BufferedImage img;
		img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/"+sprite);
		try{
			img = ImageIO.read(is);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static BufferedImage[] getAllLevels() {
		BufferedImage[] imgs;
		
		URL url = LoadSave.class.getResource("/lvls");
		File folder = null;
		try {
			folder = new File(url.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File[] files = folder.listFiles();
		File[] filesSorted = new File[files.length];
		imgs = new BufferedImage[filesSorted.length];
		for(int i = 0; i<filesSorted.length;i++) {
			for(int j = 0; i<files.length;i++) {
				if(files[j].getName().equals((i+1)+".png")) {
					filesSorted[i] = files[j];
					
					try {
						imgs[i] = ImageIO.read(filesSorted[i]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
				}
			}
		}
		return imgs;
	}
}
