package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

public class Spawner {
	public static Dog spawnDog(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, TiledMapTileLayer collisionLayer, int pointval) {
		return new Dog( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch, collisionLayer, pointval);
	}
	public static Mouse spawnMouse(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, TiledMapTileLayer collisionLayer, int pointval) {
		return new Mouse( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch,collisionLayer, pointval);
	}
	public static Bullet spawnBullet(float spawnX, float spawnY, float width, float height, float moveSpeed, double damage, boolean inv,
			 SpriteBatch map, boolean playerShot, float targetX, float targetY, TiledMapTileLayer collisionLayer, OrthographicCamera camera) {
							Vector3 vect = new Vector3(spawnX,spawnY,0);
							camera.unproject(vect);
		return new Bullet(spawnX, spawnY, width, height, moveSpeed, damage, true, map, playerShot, targetX, targetY, collisionLayer, camera);
		
	}
	public static Squirl spawnSquirl(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, TiledMapTileLayer collisionLayer, int pointval, OrthographicCamera camera) {
		return new Squirl( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch, collisionLayer, pointval, camera);
	}
	
	
	public static powerup spawnPowerup(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, TiledMapTileLayer collisionLayer, char powerup) {
		return new powerup( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch, false, collisionLayer, 0, powerup);
	}
	
}