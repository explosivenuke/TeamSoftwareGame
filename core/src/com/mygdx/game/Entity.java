package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.MainGameScreen;

public class Entity extends Sprite{
float posX;
float posY;
public float sizeX;
public float sizeY;
float speed;
public double health;
double maxHealth;
public boolean fof;
SpriteBatch batch;
Texture sprite;
public Rectangle collision;
public double damage;
public double basedamage;
TiledMapTileLayer collisionLayer;
public int pointval = 0;

public World world;
public MainGameScreen screen;


	public Entity(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage,
			boolean inv, Texture img, SpriteBatch map, boolean freindly, int pointval, World world, MainGameScreen screen) {
		this.world = world;
		this.screen = screen;
		posX = spawnX;
		posY = spawnY;
		sizeX = width;
		sizeY = height;
		speed = moveSpeed;
		sprite = img;
		batch = map;
		this.maxHealth = maxHealth;
		health = maxHealth;
		this.damage = damage;
		this.basedamage = basedamage;
		collision = new Rectangle(spawnX, spawnY, width, height);
		fof = freindly;
		this.pointval = pointval;
		this.collisionLayer = collisionLayer;
	}
	public float getCenterX() {
		return posX + (sizeX * 0.5f);
	}
	public float getCenterY() {
		return posY + (sizeY * 0.5f);
	}
	public float getPosX() {
		return posX;
	}
	public float getPosY() {
		return posY;
	}
	public void hurt(double damage) {
		health -= damage;
		
	}
	public void move(float newX, float newY) {
		// Saves Old Position
				float oldX = getPosX();
				float oldY = getPosY();
				
				// Tile Dimesions
				float tileWidth = collisionLayer.getTileWidth();
				float tileHeight = collisionLayer.getTileHeight();
				
				// Collision Detectors
				boolean collidedX = false;
				boolean collidedY = false;
				
				// Move in the x direction
				posX = newX;
//				// Moving Right
//				if(oldX-posX < 0) {
//					// Top Right
//					collidedX = collisionLayer.getCell((int)((getPosX() + sizeX) / tileWidth), (int)((getPosY() + sizeY) / tileHeight))
//							.getTile().getProperties().containsKey("Blocked");
//					
//					// Middle Right
//					if(!collidedX) {
//						collidedX = collisionLayer.getCell((int)((getPosX() + sizeX) / tileWidth), (int)((getPosY() + sizeY / 2) / tileHeight))
//								.getTile().getProperties().containsKey("Blocked");
//					}
//					
//					// Bottom Right
//					if(!collidedX) {
//						collidedX = collisionLayer.getCell((int)((getPosX() + sizeX) / tileWidth), (int)(getPosY() / tileHeight))
//								.getTile().getProperties().containsKey("Blocked");
//					}
//				}
//				// Moving Left
//				else if(oldX-posX > 0){
//					// Top Left
//					collidedX = collisionLayer.getCell((int)(getPosX() / tileWidth), (int)((getPosY() + sizeY) / tileHeight))
//							.getTile().getProperties().containsKey("Blocked");
//					
//					// Middle Left
//					if(!collidedX) {
//						collidedX = collisionLayer.getCell((int)(getPosX() / tileWidth), (int)((getPosY() + sizeY / 2) / tileHeight))
//								.getTile().getProperties().containsKey("Blocked");
//					}
//					
//					// Bottom Left
//					if(!collidedX) {
//						collidedX = collisionLayer.getCell((int)(getPosX() / tileWidth), (int)(getPosY() / tileHeight))
//								.getTile().getProperties().containsKey("Blocked");
//					}
//				}
//				
//				// React to x collision
//				if(collidedX) {
//					posX = oldX;
//				}
//				
				// Move in the y direction
				posY = newY;
//				// Moving Up
//				if(oldY-posY < 0) {
//					// Top Left
//					collidedY = collisionLayer.getCell((int)(getPosX() / tileWidth), (int)((getPosY() + sizeY) / tileHeight))
//							.getTile().getProperties().containsKey("Blocked");
//					
//					// Top Middle
//					if(!collidedY) {
//						collidedY = collisionLayer.getCell((int)((getPosX() + sizeX / 2) / tileWidth), (int)((getPosY() + sizeY) / tileHeight))
//								.getTile().getProperties().containsKey("Blocked");
//					}
//					
//					// Top Right
//					if(!collidedY) {
//						collidedY = collisionLayer.getCell((int)((getPosX() + sizeX) / tileWidth), (int)((getPosY() + sizeY) / tileHeight))
//								.getTile().getProperties().containsKey("Blocked");
//					}
//				}
//				// Moving Down
//				else if(oldY-posY > 0) {
//					// Bottom Left
//					collidedY = collisionLayer.getCell((int)(getPosX() / tileWidth), (int)(getPosY() / tileHeight))
//							.getTile().getProperties().containsKey("Blocked");
//					
//					// Bottom Middle
//					if(!collidedY) {
//						if(!collidedY) {
//							collidedY = collisionLayer.getCell((int)((getPosX() + sizeX / 2) / tileWidth), (int)(getPosY() / tileHeight))
//									.getTile().getProperties().containsKey("Blocked");
//						}
//					}
//					
//					// Bottom Right
//					if(!collidedY) {
//						if(!collidedY) {
//							collidedY = collisionLayer.getCell((int)((getPosX() + sizeX) / tileWidth), (int)(getPosY() / tileHeight))
//									.getTile().getProperties().containsKey("Blocked");
//						}
//					}
//				}
//				
//				// React to y collision
//				if(collidedY) {
//					posY = oldY;
//				}
		
		collision.set(getPosX(), getPosY(), sizeX, sizeY);
	}
	public boolean collide(Rectangle touch) {
		return collision.overlaps(touch);
	}
	public void Draw() {
		if(health > 0)
		batch.draw(sprite, posX, posY, sizeX, sizeY);
	}

}