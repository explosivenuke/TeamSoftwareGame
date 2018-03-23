package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Player extends Entity{
	
	//variables for player position health and speed and their corresponding
	//getter and setters
	
	
	public int speed = 2;
	
	
	public Player(float initialX, float initialY, float sizeX, float sizeY, float speed, double initialHealth, boolean inv, TiledMapTileLayer collisionLayer)
	{
		super(initialX, initialY, sizeX, sizeY, speed, initialHealth, 0, inv, null, null, true,collisionLayer);
	}
	
	public float getxCoordinate()
	{
		
		return getPosX();
	}
	
	public float getyCoordinate()
	{
		
		return getPosY();
	}
	
	public void setXCoordinate(float newX)
	{
		move(newX,getPosY());
	}
	
	public void setYCoordinate(float newY)
	{
		move(getPosX(),newY);
	}
	
	public double getHealth()
	{
		return health;
	}
	
	public void setHealth(double newHealth)
	{
		health = newHealth;
	}
	
	public TiledMapTileLayer getCollisionLayer()
	{
		return collisionLayer;
	}
	
	
	

}