package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.MainGameScreen;

public class TimerEnemy extends Entity{
//	int direction;
//	ActionListener taskPerformer = new ActionListener() {
//		public void actionPerformed(ActionEvent arg0) {
////			direction = (int) Math.floor(Math.random() * 4) + 1;
//		}
//	};
	
	public TimerEnemy(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage,
			boolean inv, Texture img, SpriteBatch map, int pointval, World world, MainGameScreen screen) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, map, false, pointval, world, screen);
//		direction = 0;
		
	}
	
	
	
}