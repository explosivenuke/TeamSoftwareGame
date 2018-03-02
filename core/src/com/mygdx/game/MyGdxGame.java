package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.math.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Timer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture img1;
	Texture img2;
	Texture img3;
	Texture img4;
	Texture img5;
	Texture bullet;
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	int last = 0;
	int screensize = 1000;
	int bulletheight = 20;
	private OrthographicCamera cam;
	float BulletGap = 30;
	Rectangle rectanglePlayer;
	Rectangle rectangleCeiling;
	Rectangle rectangleFloor;
	Rectangle rectangleBullet;
	Sound shot;
	Music music;
	Player mainP = new Player(50,50,100);
	Bullet bull = new Bullet();
	int direction = 0;
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			direction = (int) Math.floor(Math.random() * 4) + 1;
		}
	};
	Timer emimymove = new Timer(1000, taskPerformer);
	
//	@Override
	public void create () {
		shot = Gdx.audio.newSound(Gdx.files.internal("pew.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		 music.isLooping();
		emimymove.setInitialDelay(10);
		emimymove.start();
		batch = new SpriteBatch();
		img = new Texture("greensquare.png");
		img1 = new Texture("yellowsquare.jpg");
		img2 = new Texture("0.png");
		img3 = new Texture("1.png");
		img4 = new Texture("2.png");
		img5 = new Texture("3.png");
		
		bullet = new Texture("bullet.png");
		rectanglePlayer = new Rectangle(
				mainP.getxCoordinate(),mainP.getyCoordinate(),img2.getWidth(),img2.getHeight());
		rectangleBullet = new Rectangle(bull.getx(),bull.gety(), bullet.getWidth(), bullet.getHeight());
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Tile ceiling = new Tile(0,Gdx.graphics.getHeight()-((float) (Gdx.graphics.getHeight()*0.10)),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.10));
		ceiling.renderTile();
		Tile floor = new Tile(0,0,Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.10));
		floor.renderTile();
		rectangleCeiling = new Rectangle(ceiling.getXCoordinate(),ceiling.getYCoordinate(),ceiling.getWidth(),ceiling.getHeight());
		rectangleFloor = new Rectangle(floor.getXCoordinate(),floor.getYCoordinate(),floor.getWidth(),floor.getHeight());
		
		boolean isOverlappingCeiling = rectanglePlayer.overlaps(rectangleCeiling);
		boolean isOverlappingFloor = rectanglePlayer.overlaps(rectangleFloor);
		boolean isBulletOverlappingCeiling = rectangleBullet.overlaps(rectangleCeiling);
		boolean isBulletOverlappingFloor = rectangleBullet.overlaps(rectangleFloor);
		float rise = (Math.abs(bull.gety() - mainP.getyCoordinate()));
		float run = (Math.abs(bull.getx() - mainP.getxCoordinate()));
	float slope = (Math.abs(bull.gety() - mainP.getyCoordinate())) / (Math.abs(bull.getx() - mainP.getxCoordinate()));
		if(Gdx.input.isKeyPressed(Keys.UP))
		{
			if(!Gdx.input.isKeyJustPressed(Keys.SPACE))
			{
				if(!isOverlappingCeiling)
				{
					
				
			
			
			up = true;
			last = 0;
			mainP.setYCoordinate(mainP.getyCoordinate() + mainP.speed);
			rectanglePlayer.setPosition(mainP.getxCoordinate(), mainP.getyCoordinate() + mainP.speed);
				}
			}
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN))
		{
			if(!Gdx.input.isKeyJustPressed(Keys.SPACE))
			{
				if(!isOverlappingFloor)
				{
			last = 2;
			down = true;
			mainP.setYCoordinate(mainP.getyCoordinate() - mainP.speed);
			rectanglePlayer.setPosition(mainP.getxCoordinate(), mainP.getyCoordinate() - mainP.speed);
				}
			}
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			if(!Gdx.input.isKeyJustPressed(Keys.SPACE))
			{
				
			last = 1;
			right = true;
			mainP.setXCoordinate(mainP.getxCoordinate() + mainP.speed);
			rectanglePlayer.setPosition(mainP.getxCoordinate() + mainP.speed, mainP.getyCoordinate());
			}
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			if(!Gdx.input.isKeyJustPressed(Keys.SPACE))
			{
			last = 3;
			left = true;
			mainP.setXCoordinate(mainP.getxCoordinate() - mainP.speed);
			rectanglePlayer.setPosition(mainP.getxCoordinate() - mainP.speed, mainP.getyCoordinate());
			}
		}
//		if(direction == 1) mainP.setYCoordinate(mainP.getyCoordinate() + 1);
//		if(direction == 2) mainP.setYCoordinate(mainP.getyCoordinate() - 1);
//		if(direction == 3) mainP.setXCoordinate(mainP.getxCoordinate() + 1);
//		if(direction == 4) mainP.setXCoordinate(mainP.getxCoordinate() - 1);
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) && last == 0)
		{
			shot.play(1.0f);
			bull.setx(mainP.getxCoordinate());
			bull.sety(mainP.getyCoordinate() + BulletGap);
			bull.setdirection(-1);
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) && last == 1)
		{
			shot.play(1.0f);
			bull.setx(mainP.getxCoordinate() + BulletGap);
			bull.sety(mainP.getyCoordinate());
			bull.setdirection(-1);
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) && last == 2)
		{
			shot.play(1.0f);
			bull.setx(mainP.getxCoordinate());
			bull.sety(mainP.getyCoordinate() - BulletGap);
			bull.setdirection(-1);
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) && last == 3)
		{
			shot.play(1.0f);
			bull.setx(mainP.getxCoordinate() - BulletGap);
			bull.sety(mainP.getyCoordinate());
			bull.setdirection(-1);
		}
		
		
		
		
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
		
		bull.setx(Gdx.input.getX() - 10);
		bull.sety( Gdx.graphics.getHeight() - 1 -  Gdx.input.getY());
		/*
		if(mainP.getxCoordinate() > bull.getx())
		{
			bull.setx(mainP.getxCoordinate() - 20*(bull.getx()/mainP.getxCoordinate()));
		}
		
		if(mainP.getxCoordinate() < bull.getx())
		{
			bull.setx(mainP.getxCoordinate() + 20*(bull.getx()/mainP.getxCoordinate()));

		}
		
		if(mainP.getxCoordinate() == bull.getx() && mainP.getyCoordinate() < bull.gety())
		{
			bull.setx(mainP.getxCoordinate() + 20);

		}
			
		if(mainP.getyCoordinate() == bull.gety() && mainP.getxCoordinate() < bull.getx())
		{
			bull.sety(mainP.getyCoordinate() + 20);

		}
		
		
		if(mainP.getyCoordinate() > bull.gety())
		{
			bull.sety(mainP.getyCoordinate() - 20*(bull.gety()/mainP.getyCoordinate()));

		}
		
		if(mainP.getyCoordinate() < bull.gety())
		{
			bull.sety(mainP.getyCoordinate() + 20*(bull.gety()/mainP.getyCoordinate()));

		}
	*/
		}
		
		
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
				if(bull.getdirection() == -1)
				{
					bull.setdirection(0);
				}
			batch.draw(img2, mainP.getxCoordinate(), mainP.getyCoordinate());
			}
			if(last == 1)
			{
				if(bull.getdirection() == -1)
				{
					bull.setdirection(1);
				}
				batch.draw(img3, mainP.getxCoordinate(), mainP.getyCoordinate());
			}
			if(last == 2)
			{
				if(bull.getdirection() == -1)
				{
					bull.setdirection(2);
				}
				batch.draw(img4, mainP.getxCoordinate(), mainP.getyCoordinate());
			}
			if(last == 3)
			{
				if(bull.getdirection() == -1)
				{
					bull.setdirection(3);
				}
				batch.draw(img5, mainP.getxCoordinate(), mainP.getyCoordinate());
			}
		}
		
		if(bull.getdirection() == 0)
		{
			
			
			bull.sety(bull.gety() + bull.getspeed());
			
			
		}
		
		else if(bull.getdirection() == 1)
		{
			bull.setx(bull.getx() + bull.getspeed());
		}
		
		else if(bull.getdirection() == 2)
		{
			
			bull.sety(bull.gety() - bull.getspeed());
			
		}
		
		else if(bull.getdirection() == 3)
		{
			bull.setx(bull.getx() - bull.getspeed());
		}
			
		/*
		bull.setx(bull.getx() + slope);
		bull.sety(bull.gety() + slope);
		*/
		
		batch.draw(bullet, bull.getx(),  bull.gety(), 10, 10);
		
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
		bullet.dispose();
		shot.dispose();
		music.dispose();
	}
}
