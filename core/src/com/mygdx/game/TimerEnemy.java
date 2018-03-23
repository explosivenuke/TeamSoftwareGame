package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TimerEnemy extends Entity{
//	int direction;
//	ActionListener taskPerformer = new ActionListener() {
//		public void actionPerformed(ActionEvent arg0) {
////			direction = (int) Math.floor(Math.random() * 4) + 1;
//		}
//	};
	
	public TimerEnemy(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage,
			boolean inv, Texture img, SpriteBatch map, TiledMapTileLayer collisionLayer) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, map, false, collisionLayer);
//		direction = 0;
		
	}
	
	
	
}