package entities;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;

import static utilz.LoadSave.LEVEL_1;
import static utilz.Constants.EnemyConstants.DEAD;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class EnemyManager {
	private Playing playing;
	private ArrayList<Biker> bikers;

	public EnemyManager(Playing playing) {
		this.playing = playing;
	}
	public void initEnemies(Level level) {
		level.createEnemies();
		bikers = level.getBikers();
	}
	
	public void update(int[][] lvldata, Player player) {
		for(Biker b : bikers) {
			if(b.isActive()) {
				b.update(lvldata, player.hitBox);
			}
		}
		updateAttacked(player);
	}
	public void draw(Graphics g, int gameXOffset) {
		for(Biker b : bikers) {
			if(b.isActive()) {
				b.render(g, gameXOffset);
			}
		}
	}
	public ArrayList<Biker> getEnemies(){
		return bikers;
	}
	public void updateAttacked(Player player) {
		//update attacks on enemies and on players
		for(Biker b : bikers) {
			if(player.getAttacking(b.hitBox)) {
				b.changeHealthBar(-player.getAttackPower());
			}
			if(b.getAttacking(player.hitBox)) {
				player.changeHealthBar(-b.getAttackPower());
			}
		}
	}
	public void reset(Level level) {
		for(Biker b : bikers) {
			b.reset();
		}
	}
}
