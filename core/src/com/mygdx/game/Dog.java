package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Dog extends TrackerEnemy{

	public Dog(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, boolean inv, Texture img, SpriteBatch batch, TiledMapTileLayer collisionLayer) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, batch, collisionLayer);
	}
	public void Draw() {
		batch.draw(sprite, posX, posY, sizeX, sizeY);
	}
}