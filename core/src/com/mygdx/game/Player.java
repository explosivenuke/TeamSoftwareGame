package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Player extends Entity {

	// variables for player position health and speed and their corresponding
	// getter and setters

	public int speed = 2;
	public boolean justhit;

	Timer emimymove = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			justhit = false;
			emimymove.stop();
		}
	});

	public Player(float initialX, float initialY, float sizeX, float sizeY, float speed, double initialHealth,
			boolean inv, TiledMapTileLayer collisionLayer) {
		super(initialX, initialY, sizeX, sizeY, speed, initialHealth, 0, inv, null, null, true, collisionLayer);
	}

	public float getxCoordinate() {

		return getPosX();
	}

	public float getyCoordinate() {

		return getPosY();
	}

	public void setXCoordinate(float newX) {
		move(newX, getPosY());
	}

	public void setYCoordinate(float newY) {
		move(getPosX(), newY);
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double newHealth) {

		if (!justhit) {
			health = newHealth;
			justhit = true;
			emimymove.start();
			move(getPosX() - 20, getPosY());
		}

	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

}