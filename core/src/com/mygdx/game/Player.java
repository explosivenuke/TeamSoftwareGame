package com.mygdx.game;

public class Player extends Entity{
	
	//variables for player position health and speed and their corresponding
	//getter and setters
	
	
	double health;
	int speed = 2;
	
	
	public Player(float initialX, float initialY, float sizeX, float sizeY, double speed, double initialHealth, boolean inv)
	{
		super(initialX, initialY, sizeX, sizeY, speed, initialHealth, inv);
		health = initialHealth;
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
	
	
	
	

}
