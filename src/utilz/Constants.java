package utilz;

import java.awt.Font;

import main.Game;

public class Constants {
	public static final float GRAVITY = 0.04f*Game.SCALE;
	public static final int ENTITY_FPS  = 6;
	public static final int ANI_SET  = Game.FPS_SET/ENTITY_FPS; //playerSpeed -> FPS/number = updates per second
	

	public static class PlayerConstants{
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACKING = 2;
		public static final int JUMPING = 3;
		public static final int LANDING = 4;
		public static final int DEAD = 5;

		public static final int LEFT = 0;
		public static final int RIGHT = 1;
	
		public static int getSpriteAmount(int playerActions){
			switch (playerActions) {
				/*case UP:
				case DOWN:
				case LEFT:
				case RIGHT:*/
			case IDLE:
			case JUMPING:
				return 4;
			case RUNNING:
			case ATTACKING:
			case LANDING:
					return 6;
				default:
					return 0;
					
			}
		}
	}
	public static class EnemyConstants{
		
		public final static int DEFAULT_ENEMY_SIZE = 48; //enemy picture size
		public final static int DEFAULT_BIKERH_WIDTH = 20; //hitbox width
		public final static int DEFAULT_BIKERH_HEIGHT = 33; //hitbox height
		public final static float SCALE = 0.85f; //change scale to change ENEMY_SIZE, HITBOX_WIDTH, and HITBOX_HEIGHT
		public final static int ENEMY_SIZE = (int)(DEFAULT_ENEMY_SIZE*SCALE);
		public final static int BIKERH_WIDTH = (int)(DEFAULT_BIKERH_WIDTH*SCALE);
		public final static int BIKERH_HEIGHT = (int)(DEFAULT_BIKERH_HEIGHT*SCALE);
		
		public static final int BIKER = 0;
		
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACKING = 2;
		public static final int JUMPING = 3;
		public static final int DEAD = 4;
		
		public static final int LEFT = 0;
		public static final int RIGHT = 1;
	
		public static int getSpriteAmount(int enemyType, int enemyActions){
			switch (enemyType) {
				case BIKER:
					switch (enemyActions) {
					case IDLE:
					case JUMPING:
						return 4;
					case RUNNING:
					case ATTACKING:
					case DEAD:
							return 6;
						default:
							return 0;	
					}
				default:
					return 0;	
			}
		}
		public static int getHitboxWidth(int enemyType){
			switch (enemyType) {
				case BIKER:
					return BIKERH_WIDTH;	
				default:
					return 0;	
			}
		}
		public static int getHitboxHeight(int enemyType){
			switch (enemyType) {
				case BIKER:
					return BIKERH_HEIGHT;	
				default:
					return 0;	
			}
		}
	}
	public static class UI{
		public static class Buttons{
			public static final Font FONT_1 = new Font("Arial", Font.PLAIN, (int)(16*Game.SCALE));
			public static final int TYPE_PLAY_ICON = 0;
			public static final int TYPE_PAUSE_ICON = 1;
			public static final int TYPE_SOUND_ICON = 2;
			public static final int TYPE_MUTE_ICON = 3;
			public static final int TYPE_RESET_ICON = 4;
			public static final int TYPE_MENU_ICON = 5;
			public static final int TYPE_HEALTH_BAR_UNIT = 6;
			
			public final static int MENU_B_HEIGHT = (int)(50*Game.SCALE);
			public final static int MENU_B_WIDTH = (int)(70*Game.SCALE);
			public final static float ICON_B_SIZE = 20*Game.SCALE;
			
			public static final int[][] PLAY_ICON = 
				{{1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,0,0,1,1,0,0,0,0,1},
				{1,0,0,1,1,1,0,0,0,1},
				{1,0,0,1,1,1,1,0,0,1},
				{1,0,0,1,1,1,1,0,0,1},
				{1,0,0,1,1,1,0,0,0,1},
				{1,0,0,1,1,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1}};
			public static final int[][] PAUSE_ICON = 
				{{1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,0,1,1,0,0,1,1,0,1},
				{1,0,1,1,0,0,1,1,0,1},
				{1,0,1,1,0,0,1,1,0,1},
				{1,0,1,1,0,0,1,1,0,1},
				{1,0,1,1,0,0,1,1,0,1},
				{1,0,1,1,0,0,1,1,0,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1}};
			public static final int[][] SOUND_ICON = 
				{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,1,1,1,1,1,0,0,1,0,0,1,1},
				{1,1,0,0,0,0,0,1,1,1,1,1,1,0,1,1,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,1,0,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,1,1,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,1,1,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,1,0,0,0,1,1},
				{1,1,0,0,0,0,0,1,1,1,1,1,1,0,1,1,0,0,1,1},
				{1,1,0,0,0,0,0,0,1,1,1,1,1,0,0,1,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
			public static final int[][] MUTE_ICON = 
				{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,1,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,1,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
			public static final int[][] RESET_ICON =
				{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1},
				{1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,1,1},
				{1,1,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,0,1,1},
				{1,1,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,1,1,0,0,1,1,1,1,1,1,1,0,0,0,1,1},
				{1,1,0,0,1,1,0,0,1,1,1,1,1,1,1,1,0,0,1,1},
				{1,1,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,1,1},
				{1,1,0,0,1,1,0,0,0,0,1,0,0,0,1,1,0,0,1,1},
				{1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1},
				{1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
			public static final int[][] MENU_ICON =
				{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,1,1},
				{1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1},
				{1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1},
				{1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1},
				{1,1,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,1,1},
				{1,1,0,0,0,1,1,0,1,1,1,1,0,1,1,0,0,0,1,1},
				{1,1,0,0,0,1,1,0,1,1,0,1,0,1,1,0,0,0,1,1},
				{1,1,0,0,0,1,1,0,1,1,1,1,0,1,1,0,0,0,1,1},
				{1,1,0,0,0,1,1,0,1,1,1,1,0,1,1,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
			public static final int[][] HEALTH_BAR_UNIT = 
				{{1,1,1,1},
				{1,0,0,1},
				{1,0,0,1},
				{1,0,0,1},
				{1,0,0,1},
				{1,0,0,1},
				{1,0,0,1},
				{1,0,0,1},
				{1,0,0,1},
				{1,1,1,1}};
		
			//public final static int UNIT_SCALE = ICON_B_SIZE/PLAY_ICON.length;
			
			public static int[][] getArray(int type){
				switch(type) {
				case(TYPE_PLAY_ICON):
					return PLAY_ICON;
				case(TYPE_PAUSE_ICON):
					return PAUSE_ICON;
				case(TYPE_SOUND_ICON):
					return SOUND_ICON;
				case(TYPE_MUTE_ICON):
					return MUTE_ICON;
				case(TYPE_RESET_ICON):
					return RESET_ICON;
				case(TYPE_MENU_ICON):
					return MENU_ICON;
				case(TYPE_HEALTH_BAR_UNIT):
					return HEALTH_BAR_UNIT;
				default:
					return null;
				}
			}

		}
	} 
}
