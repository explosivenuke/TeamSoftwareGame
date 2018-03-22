package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;

public class MainMenuScreen implements Screen{

	MyGdxGame game = new MyGdxGame();
	static int buttonGap = 10;
	private static int startbuttonHeight = 50;
	private static int startbuttonWidth = 300;
	private static int startbuttonX = MyGdxGame.width/2 - startbuttonWidth/2;
	private static int startbuttonY = MyGdxGame.height/2 - startbuttonHeight/2;
	private static int quitbuttonHeight = 50;
	private static int quitbuttonWidth =  300;
	private static int quitbuttonX = MyGdxGame.width/2 - quitbuttonWidth/2;
	private static int quitbuttonY = MyGdxGame.height/2 - quitbuttonHeight/2 - (buttonGap + startbuttonHeight);
	int x = MyGdxGame.width/2 - startbuttonHeight;


 
	Texture startActive;
	Texture startInactive;
	Texture quitActive;
	Texture quitInactive;
	
	
	public MainMenuScreen(MyGdxGame game)
	{
		this.game = game;
		startActive = new Texture("start_green.png");
		startInactive = new Texture("start_red.png");
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

		
		if(Gdx.input.getX() < x + startbuttonWidth && Gdx.input.getX() > x && MyGdxGame.height - Gdx.input.getY() < startbuttonY + startbuttonHeight && MyGdxGame.height - Gdx.input.getY() > startbuttonY)
		{
			game.batch.draw(startActive, MyGdxGame.width/2 - startbuttonWidth/2, MyGdxGame.height/2 - startbuttonHeight/2,startbuttonWidth,startbuttonHeight);
			
			if(Gdx.input.isTouched())
			{
				this.dispose();
				MainGameScreen gamer = new MainGameScreen(game);
				game.main = gamer;
				game.setScreen(gamer);
			}
			
		}
		else
		{
			game.batch.draw(startInactive, MyGdxGame.width/2 - startbuttonWidth/2, MyGdxGame.height/2 - startbuttonHeight/2,startbuttonWidth,startbuttonHeight);

		}
		
		if(Gdx.input.getX() < x + quitbuttonWidth && Gdx.input.getX() > x && MyGdxGame.height - Gdx.input.getY() < quitbuttonY + quitbuttonHeight && MyGdxGame.height - Gdx.input.getY() > quitbuttonY)
		{
			
		game.batch.draw(quitActive, MyGdxGame.width/2 - quitbuttonWidth/2,  MyGdxGame.height/2 - quitbuttonHeight/2 - (buttonGap + startbuttonHeight),quitbuttonWidth, quitbuttonHeight);
		
		if(Gdx.input.isTouched())
		{
			Gdx.app.exit();
		}
		
		}
		else
		{
			
		game.batch.draw(quitInactive, MyGdxGame.width/2 - quitbuttonWidth/2,  MyGdxGame.height/2 - quitbuttonHeight/2 - (buttonGap + startbuttonHeight),quitbuttonWidth, quitbuttonHeight);
	
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
