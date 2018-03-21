package com.mygdx.game;

public class Bullet extends Entity{

	
	
	

	public Bullet(float spawnX, float spawnY, float width, float height, double moveSpeed, double maxHealth,
			boolean inv) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, inv);
		// TODO Auto-generated constructor stub
	}

	//variables for the bullets coordinates and damage direction and speed
	public float xcoordinate = -100;
	public float ycoordinate = -100;
	int damage = 1;
	int direction = -1;
	public int speed = 5;
	
	
	public int getspeed()
	{
		return speed;
	}
	
	public void setspeed(int newspeed)
	{
		speed = newspeed;
	}
	
	
	public int getdirection()
	{
		return direction;
	}
	
	public void setdirection(int newd)
	{
		direction = newd;
	}
	
	public float getx() {
		
		return xcoordinate;
	}
	
	public float gety() {
		
		return ycoordinate;
	}
	
	public void sety(float newy)
	{
		ycoordinate = newy;
	}
	
	public void setx(float newx)
	{
		xcoordinate = newx;
	}
	
	
	
}
