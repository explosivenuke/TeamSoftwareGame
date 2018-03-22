package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;

public class PauseMenuScreen implements Screen{

	MyGdxGame game;
	MainGameScreen main;
	static int buttonGap = 10;
	private static int continuebuttonHeight = 50;
	private static int continuebuttonWidth = 300;
	private static int continuebuttonX = MyGdxGame.width/2 - continuebuttonWidth/2;
	private static int continuebuttonY = MyGdxGame.height/2 - continuebuttonHeight/2;
	private static int quitbuttonHeight = 50;
	private static int quitbuttonWidth =  300;
	private static int quitbuttonX = MyGdxGame.width/2 - quitbuttonWidth/2;
	private static int quitbuttonY = MyGdxGame.height/2 - quitbuttonHeight/2 - (buttonGap + continuebuttonHeight);
	int x = MyGdxGame.width/2 - continuebuttonHeight;

	Texture continueActive;
	Texture continueInactive;
	Texture quitActive;
	Texture quitInactive;
	
	
	
	public PauseMenuScreen(MyGdxGame game, MainGameScreen main)
	{
		this.game = game;
		this.main = main;
		continueActive = new Texture("continue_green.png");
		continueInactive = new Texture("continue_red.png");
		quitActive = new Texture("quit_green.png");
		quitInactive = new Texture("quit_red.png");
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		if(Gdx.input.getX() < x + continuebuttonWidth && Gdx.input.getX() > x && MyGdxGame.height - Gdx.input.getY() < continuebuttonY + continuebuttonHeight && MyGdxGame.height - Gdx.input.getY() > continuebuttonY)
		{
			game.batch.draw(continueActive, MyGdxGame.width/2 - continuebuttonWidth/2, MyGdxGame.height/2 - continuebuttonHeight/2,continuebuttonWidth,continuebuttonHeight);
			
			if(Gdx.input.isTouched())
			{
				this.dispose();
				game.setScreen(new MainGameScreen(game));
			}
			
		}
		else
		{
			game.batch.draw(continueInactive, MyGdxGame.width/2 - continuebuttonWidth/2, MyGdxGame.height/2 - continuebuttonHeight/2,continuebuttonWidth,continuebuttonHeight);

		}
		
		if(Gdx.input.getX() < x + quitbuttonWidth && Gdx.input.getX() > x && MyGdxGame.height - Gdx.input.getY() < quitbuttonY + quitbuttonHeight && MyGdxGame.height - Gdx.input.getY() > quitbuttonY)
		{
			
		game.batch.draw(quitActive, MyGdxGame.width/2 - quitbuttonWidth/2,  MyGdxGame.height/2 - quitbuttonHeight/2 - (buttonGap + continuebuttonHeight),quitbuttonWidth, quitbuttonHeight);
		
		if(Gdx.input.isTouched())
		{
			Gdx.app.exit();
		}
		
		}
		else
		{
			
		game.batch.draw(quitInactive, MyGdxGame.width/2 - quitbuttonWidth/2,  MyGdxGame.height/2 - quitbuttonHeight/2 - (buttonGap + continuebuttonHeight),quitbuttonWidth, quitbuttonHeight);
	
		}
		
		game.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
				
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
				
	}

	@Override
	public void hide() {
				
	}

	@Override
	public void dispose() {
				
	}

}
