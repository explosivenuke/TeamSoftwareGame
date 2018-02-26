package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture img1;
	Texture img2;
	Texture img3;
	Texture img4;
	Texture img5;
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	int last = 0;
	
	
	Player mainP = new Player(50,50,100);
	int direction = 0;
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			direction = (int) Math.floor(Math.random() * 4) + 1;
		}
	};
	Timer emimymove = new Timer(1000, taskPerformer);
	
//	@Override
	public void create () {
		emimymove.setInitialDelay(10);
		emimymove.start();
		batch = new SpriteBatch();
		img = new Texture("greensquare.png");
		img1 = new Texture("yellowsquare.jpg");
		img2 = new Texture("0.png");
		img3 = new Texture("1.png");
		img4 = new Texture("2.png");
		img5 = new Texture("3.png");
		
	}

	@Override
	public void render () {
		if(Gdx.input.isKeyPressed(Keys.UP))
		{
			up = true;
			last = 0;
			mainP.setYCoordinate(mainP.getyCoordinate() + mainP.speed);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN))
		{
			last = 2;
			down = true;
			mainP.setYCoordinate(mainP.getyCoordinate() - mainP.speed);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			last = 1;
			right = true;
			mainP.setXCoordinate(mainP.getxCoordinate() + mainP.speed);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			last = 3;
			left = true;
			mainP.setXCoordinate(mainP.getxCoordinate() - mainP.speed);
		}
//		if(direction == 1) mainP.setYCoordinate(mainP.getyCoordinate() + 1);
//		if(direction == 2) mainP.setYCoordinate(mainP.getyCoordinate() - 1);
//		if(direction == 3) mainP.setXCoordinate(mainP.getxCoordinate() + 1);
//		if(direction == 4) mainP.setXCoordinate(mainP.getxCoordinate() - 1);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(up == true)
		{
		batch.draw(img2, mainP.getxCoordinate(), mainP.getyCoordinate());
		}
		else if(right == true)
		{
		batch.draw(img3, mainP.getxCoordinate(), mainP.getyCoordinate());
		}
		else if(down == true)
		{
		batch.draw(img4, mainP.getxCoordinate(), mainP.getyCoordinate());
		}
		else if(left == true)
		{
		batch.draw(img5, mainP.getxCoordinate(), mainP.getyCoordinate());
		}
		else
		{
			if(last == 0)
			{
			batch.draw(img2, mainP.getxCoordinate(), mainP.getyCoordinate());
			}
			if(last == 1)
			{
				batch.draw(img3, mainP.getxCoordinate(), mainP.getyCoordinate());
			}
			if(last == 2)
			{
				batch.draw(img4, mainP.getxCoordinate(), mainP.getyCoordinate());
			}
			if(last == 3)
			{
				batch.draw(img5, mainP.getxCoordinate(), mainP.getyCoordinate());
			}
		}
		
		
		batch.draw(img1, 100, 100);
		batch.end();
		left = false;
		right = false;
		up = false;
		down = false;
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		img1.dispose();
		img2.dispose();
		img3.dispose();
		img4.dispose();
		img5.dispose();
	}
}
