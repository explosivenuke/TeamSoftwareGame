package com.mygdx.game;

public class Player {
	double xCoordinate;
	double yCoordinate;
	double health;
	
	public Player(double initialX, double initialY, double initialHealth)
	{
		xCoordinate = initialX;
		yCoordinate = initialY;
		health = initialHealth;
	}
	
	public double getxCoordinate()
	{
		
		return xCoordinate;
	}
	
	public double getyCoordinate()
	{
		
		return yCoordinate;
	}
	
	public void setXCoordinate(double newX)
	{
		xCoordinate = newX;
	}
	
	public void setYCoordinate(double newY)
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
