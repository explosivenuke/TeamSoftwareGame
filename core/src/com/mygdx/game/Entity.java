package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
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
	public Entity(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage,
			boolean inv, Texture img, SpriteBatch map, boolean freindly) {
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
		collision = new Rectangle(spawnX, spawnY, width, height);
		fof = freindly;
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
		posX = newX;
		posY = newY;
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