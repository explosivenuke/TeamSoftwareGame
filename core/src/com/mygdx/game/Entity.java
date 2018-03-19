package com.mygdx.game;

public class Entity {
float posX;
float posY;
float sizeX;
float sizeY;
double speed;
double Health;
	public Entity(float spawnX, float spawnY, float width, float height, double moveSpeed, double maxHealth,
			boolean inv) {
		posX = spawnX;
		posY = spawnY;
		sizeX = width;
		sizeY = height;
		speed = moveSpeed;
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
	public void move(float newX, float newY) {
		posX = newX;
		posY = newY;
	}

}
