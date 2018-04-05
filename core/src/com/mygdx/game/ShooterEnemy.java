package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class ShooterEnemy extends Entity{


	private OrthographicCamera camera;

	public ShooterEnemy(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage,
			boolean inv, Texture img, SpriteBatch map,TiledMapTileLayer collisionLayer, int pointval, OrthographicCamera camera) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, map, false, collisionLayer, pointval);
		this.camera = camera;
	}

	public Bullet trackPlayer(Entity player) {
		OrthographicCamera camera = this.camera;
		float distance = (float) Math.sqrt(Math.pow(player.getCenterX() - this.getCenterX(), 2) + Math.pow(player.getCenterY() - this.getCenterY(), 2));
		float Xspeed = (player.getCenterX() - this.getCenterX())/distance;
		float Yspeed = (player.getCenterY() - this.getCenterY())/distance;
		move(getPosX() + Xspeed*speed, getPosY() + Yspeed*speed);
		return Spawner.spawnBullet(getPosX(), getPosY(), sizeX, sizeY, 2, 10, true, batch, false, player.getCenterX(), player.getCenterY(), collisionLayer, camera);
	}

}