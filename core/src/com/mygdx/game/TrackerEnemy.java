package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TrackerEnemy extends Entity{

	public TrackerEnemy(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage,
			boolean inv, Texture img, SpriteBatch map,TiledMapTileLayer collisionLayer) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, map, false, collisionLayer);
	}

	public void trackPlayer(Entity player) {
		float distance = (float) Math.sqrt(Math.pow(player.getCenterX() - this.getCenterX(), 2) + Math.pow(player.getCenterY() - this.getCenterY(), 2));
		float Xspeed = (player.getCenterX() - this.getCenterX())/distance;
		float Yspeed = (player.getCenterY() - this.getCenterY())/distance;
		move(getPosX() + Xspeed*speed, getPosY() + Yspeed*speed);
	}

}