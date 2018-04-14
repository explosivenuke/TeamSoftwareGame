package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.MainGameScreen;

public class Spawner {
	public static Dog spawnDog(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, int pointval, World world, MainGameScreen screen) {
		return new Dog( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch, pointval, world, screen);
	}
	public static Mouse spawnMouse(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, int pointval, World world, MainGameScreen screen) {
		return new Mouse( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch, pointval, world, screen);
	}
	public static Bullet spawnBullet(float spawnX, float spawnY, float width, float height, float moveSpeed, double damage, boolean inv,
			 SpriteBatch map, boolean playerShot, float targetX, float targetY, OrthographicCamera camera, World world, MainGameScreen screen) {
							Vector3 vect = new Vector3(spawnX,spawnY,0);
							camera.unproject(vect);
		return new Bullet(spawnX, spawnY, width, height, moveSpeed, damage, true, map, playerShot, targetX, targetY, camera, world, screen);
		
	}
	public static Bird spawnBird(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, int pointval, OrthographicCamera camera, World world, MainGameScreen screen) {
		return new Bird( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch, pointval, camera, world, screen);
	}
	
	
	public static powerup spawnPowerup(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, Texture img,  SpriteBatch batch, char powerup, World world, MainGameScreen screen) {
 		return new powerup( spawnX,  spawnY,  width,  height,  moveSpeed,  maxHealth, damage, false, img, batch, false, 0, powerup, world, screen);
	}
	
}