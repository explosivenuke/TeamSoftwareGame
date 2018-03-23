package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Spawner {
	public static Dog spawnDog(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch) {
		return new Dog( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch);
	}
	public static Mouse spawnMouse(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch) {
		return new Mouse( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch);
	}
	public static Bullet spawnBullet(float spawnX, float spawnY, float width, float height, float moveSpeed, double damage, boolean inv,
			 SpriteBatch map, boolean playerShot, float targetX, float targetY) {
		return new Bullet(spawnX, spawnY, width, height, moveSpeed, damage, true, map, playerShot, targetX, targetY);
		
	}
	public static Squirl spawnSquirl(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch) {
		return new Squirl( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch);
	}
}