package com.mygdx.game;

public class Bullet extends Entity{

	float xcoordinate = -1;
	float ycoordinate = -1;
	int damage = 1;
	int direction = -1;
	int speed = 5;
	
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
		// TODO Auto-generated method stub
		return xcoordinate;
	}
	
	public float gety() {
		// TODO Auto-generated method stub
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
