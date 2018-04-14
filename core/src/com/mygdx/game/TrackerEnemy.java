package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.MainGameScreen;

public class TrackerEnemy extends Entity{

	public TrackerEnemy(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage,
			boolean inv, Texture img, SpriteBatch map, int pointval, World world, MainGameScreen screen) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, map, false, pointval, world, screen);
	}

	public void trackPlayer(Entity player) {
		float distance = (float) Math.sqrt(Math.pow(player.getCenterX() - this.getCenterX(), 2) + Math.pow(player.getCenterY() - this.getCenterY(), 2));
		float Xspeed = (player.getCenterX() - this.getCenterX())/distance;
		float Yspeed = (player.getCenterY() - this.getCenterY())/distance;
		move(getPosX() + Xspeed*speed, getPosY() + Yspeed*speed);
	}

}