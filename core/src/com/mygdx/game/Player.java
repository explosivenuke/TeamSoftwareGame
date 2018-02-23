package com.mygdx.game;

public class Player {
	float xCoordinate;
	float yCoordinate;
	double health;
	int speed = 2;
	
	
	public Player(float initialX, float initialY, double initialHealth)
	{
		xCoordinate = initialX;
		yCoordinate = initialY;
		health = initialHealth;
	}
	
	public float getxCoordinate()
	{
		
		return xCoordinate;
	}
	
	public float getyCoordinate()
	{
		
		return yCoordinate;
	}
	
	public void setXCoordinate(float newX)
	{
		xCoordinate = newX;
	}
	
	public void setYCoordinate(float newY)
	{
		yCoordinate = newY;
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
