package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture img1;
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
	}

	@Override
	public void render () {
		if(Gdx.input.isKeyPressed(Keys.UP))
		{
			mainP.setYCoordinate(mainP.getyCoordinate() + mainP.speed);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN))
		{
			mainP.setYCoordinate(mainP.getyCoordinate() - mainP.speed);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			mainP.setXCoordinate(mainP.getxCoordinate() + mainP.speed);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			mainP.setXCoordinate(mainP.getxCoordinate() - mainP.speed);
		}
//		if(direction == 1) mainP.setYCoordinate(mainP.getyCoordinate() + 1);
//		if(direction == 2) mainP.setYCoordinate(mainP.getyCoordinate() - 1);
//		if(direction == 3) mainP.setXCoordinate(mainP.getxCoordinate() + 1);
//		if(direction == 4) mainP.setXCoordinate(mainP.getxCoordinate() - 1);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, mainP.getxCoordinate(), mainP.getyCoordinate());
		batch.draw(img1, 100, 100);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
