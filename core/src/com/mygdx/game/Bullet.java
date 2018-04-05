package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

public class Bullet extends Entity {

	// variables for the bullets coordinates and damage direction and speed
	float xcoordinate = -100;
	float ycoordinate = -100;
	int damage = 1;
	int direction = -1;
	int speed = 5;
	
	float Xspeed;
	float Yspeed;
	float maxDistance = 1000;
	Vector3 vect;
	Vector3 vector;
	public OrthographicCamera camera;
	
			
	
	public Bullet(float spawnX, float spawnY, float width, float height, float moveSpeed, double damage, boolean inv,
			 SpriteBatch map, boolean type, float targetX, float targetY, TiledMapTileLayer collisionLayer, OrthographicCamera camera) {
		super(spawnX, spawnY, width, height, moveSpeed,1, damage, true, new Texture("bullet.png"), map, type,collisionLayer,0);
		this.camera = camera;
		float distance = (float) Math.sqrt(Math.pow(targetX - this.getCenterX(), 2) + Math.pow(targetY - this.getCenterY(), 2));
		Xspeed = (targetX - this.getCenterX())/distance;
		Yspeed = (targetY - this.getCenterY())/distance;
		
		vect = new Vector3();
	}
	public void Draw() {
		
		vect.x = getPosX();
		vect.y = getPosY();
		
		move(vect.x + Xspeed*speed, vect.y + Yspeed*speed);
		maxDistance -= speed;
		if(maxDistance <= 0) {
			health = -1;
		}
		super.Draw();
	}

	public int getspeed() {
		return speed;
	}

	public void setspeed(int newspeed) {
		speed = newspeed;
	}

	public int getdirection() {
		return direction;
	}

	public void setdirection(int newd) {
		direction = newd;
	}

	public float getx() {

		return xcoordinate;
	}

	public float gety() {

		return ycoordinate;
	}

	public void sety(float newy) {
		ycoordinate = newy;
	}

	public void setx(float newx) {
		xcoordinate = newx;
	}

}