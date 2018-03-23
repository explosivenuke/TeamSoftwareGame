package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Spawner {
	public static Dog spawnDog(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, TiledMapTileLayer collisionLayer) {
		return new Dog( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch, collisionLayer);
	}
	public static Mouse spawnMouse(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, TiledMapTileLayer collisionLayer) {
		return new Mouse( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch,collisionLayer);
	}
	public static Bullet spawnBullet(float spawnX, float spawnY, float width, float height, float moveSpeed, double damage, boolean inv,
			 SpriteBatch map, boolean playerShot, float targetX, float targetY, TiledMapTileLayer collisionLayer) {
		return new Bullet(spawnX, spawnY, width, height, moveSpeed, damage, true, map, playerShot, targetX, targetY, collisionLayer);
		
	}
	public static Squirl spawnSquirl(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, TiledMapTileLayer collisionLayer) {
		return new Squirl( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch, collisionLayer);
	}
}