package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Bullet;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player;
import com.mygdx.game.Tile;

public class MainGameScreen implements Screen{
	MyGdxGame game;
	MainGameScreen main;
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
	float bulletSpeed = 2;
	float BulletGap = 0;
	Rectangle rectanglePlayer;
	Rectangle rectangleCeiling;
	Rectangle rectangleFloor;
	Rectangle rectangleBullet;
	Sound shot;
	Music music;
	Player mainP = new Player(50,50,100,100,2,100,false);
	Bullet bull = new Bullet(0, 0, bulletheight, bulletheight, bulletSpeed, 0, false);
	int direction = 0;
	boolean bulletIsLoaded = false;
	int width = 25;
	int height = 25;
	boolean paused = false;
	boolean isOverlappingCeiling;
	boolean isOverlappingFloor;
	boolean isBulletOverlappingCeiling;
	boolean isBulletOverlappingFloor;
	Tile ceiling;
	Tile floor;
	static int buttonGap = 10;
	
	
	private static int backdropHeight = MyGdxGame.height - 120;
	private static int backdropWidth = 500;
	private static int backdropX = MyGdxGame.width/2 - backdropWidth/2;
	private static int backdropY = MyGdxGame.height/2 - backdropHeight/2;
	
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
	Texture backdrop;
	
	//variables for controlling bullet speed
		float bullSpeedX;
		float bullSpeedY;
		boolean created = false;
		
		public MainGameScreen (MyGdxGame game)
		{
			this.game = game;
			main = game.main;
			continueActive = new Texture("continue_green.png");
			continueInactive = new Texture("continue_red.png");
			quitActive = new Texture("quit_green.png");
			quitInactive = new Texture("quit_red.png");
			img = new Texture("greensquare.png");
			img1 = new Texture("yellowsquare.jpg");
			img2 = new Texture("0.png");
			img3 = new Texture("1.png");
			img4 = new Texture("2.png");
			img5 = new Texture("3.png");
			
			bullet = new Texture("bullet.png");
		 	backdrop = new Texture("backdrop.png");
		 	shot = Gdx.audio.newSound(Gdx.files.internal("pew.wav"));
			music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
			
		}
	
	@Override
	public void show() {
		//creating shot sound and background music
		
		//Gdx.graphics.setWindowedMode(1000, 10000);
	
		//creating the sprites and their textures
		
	
		//creating boundary for collision on player and bullet, 
		//the bullet not being implemented fully yet
		 music.isLooping();
		music.play();
		//booleans that are checking collision
		
		//calcuates rise and run between player and bullet click position and slope
		float rise;
		float run;
		
	float slope;		
	}

	@Override
	public void render(float delta) {
		//clearing color and setting background color
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//creating the ceiling boundary and floor boundary 
				rectanglePlayer = new Rectangle(
						mainP.getxCoordinate(),mainP.getyCoordinate(),img2.getWidth(),img2.getHeight());
				rectangleBullet = new Rectangle(bull.getx(),bull.gety(), bullet.getWidth(), bullet.getHeight());
//				if(!created)
//				{
				Tile ceiling = new Tile(0,Gdx.graphics.getHeight()-((float) (Gdx.graphics.getHeight()*0.10)),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.10));
				ceiling.renderTile();
				Tile floor = new Tile(0,0,Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.10));
				floor.renderTile();
//				}
				
//				if(!created)
//				{
				rectangleCeiling = new Rectangle(ceiling.getXCoordinate(),ceiling.getYCoordinate(),ceiling.getWidth(),ceiling.getHeight());
				rectangleFloor = new Rectangle(floor.getXCoordinate(),floor.getYCoordinate(),floor.getWidth(),floor.getHeight());
				created = true;
//				}
				
				//booleans that are checking collision
				isOverlappingCeiling = rectanglePlayer.overlaps(rectangleCeiling);
				isOverlappingFloor = rectanglePlayer.overlaps(rectangleFloor);
				isBulletOverlappingCeiling = rectangleBullet.overlaps(rectangleCeiling);
				isBulletOverlappingFloor = rectangleBullet.overlaps(rectangleFloor);
				
				//calcuates rise and run between player and bullet click position and slope
				float rise = (Math.abs(bull.gety() - mainP.getyCoordinate()));
				float run = (Math.abs(bull.getx() - mainP.getxCoordinate()));
				
			float slope = (Math.abs(bull.gety() - mainP.getyCoordinate())) / (Math.abs(bull.getx() - mainP.getxCoordinate()));
			//controls players action based on input and checks to see if space is pressed while
			//they are moving so that the bullet can travel while player is moving
			//also does the check for collision and responds accordingly
			
			if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			{
				this.dispose();
				
				if(paused)
				{
					resume();
				}
				else
				{
					pause();
					
					
					
				}
				
//				game.setScreen(new PauseMenuScreen(game,main));
				
			}
			
			if(!paused)
			{
			
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
//				if(direction == 1) mainP.setYCoordinate(mainP.getyCoordinate() + 1);
//				if(direction == 2) mainP.setYCoordinate(mainP.getyCoordinate() - 1);
//				if(direction == 3) mainP.setXCoordinate(mainP.getxCoordinate() + 1);
//				if(direction == 4) mainP.setXCoordinate(mainP.getxCoordinate() - 1);
				
				
				//allows player to shoot using space bar is obsolete and will be replaced 
				//by mouse oonce fully functioning
				if(Gdx.input.isKeyJustPressed(Keys.SPACE) && last == 0)
				{
					shot.play(1.0f);
					bull.setx(mainP.getxCoordinate());
					bull.sety(mainP.getyCoordinate() + BulletGap);
					bull.setdirection(-1);
					bulletIsLoaded = true;
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
				
				
				//takes mouse click position and fires bullet at correct anle and speed 
				//accordingly
				if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
				{
				
				float targetX = Gdx.input.getX() - 10;
				float targetY  = Gdx.graphics.getHeight() - 1 -  Gdx.input.getY();
				float tempy = Gdx.graphics.getHeight() - 1 - mainP.getyCoordinate();
				float bullSlope = (float) Math.sqrt(Math.pow(targetX - mainP.getxCoordinate(), 2) + Math.pow(targetY - mainP.getyCoordinate(), 2));
				bullSpeedX = ((targetX - mainP.getxCoordinate())/bullSlope)  * bull.speed;
				bullSpeedY = ((targetY - mainP.getyCoordinate())/bullSlope) * bull.speed;
				shot.play(1.0f);
			
//				bull.xcoordinate = targetX;
//				bull.ycoordinate = tempy;
				bull.xcoordinate = mainP.getxCoordinate();
//				if(bull.xcoordinate > targetX) {
//					bullSpeedX = -bullSpeedX;
//				}
				bull.ycoordinate = mainP.getyCoordinate();
//				if(bull.ycoordinate > targetY) {
//					bullSpeedY = -bullSpeedY;
//				}
				
				//everything here is obsolete was testing in attempt to get mouse working
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
				
			
				game.batch.begin(); 
				//determines what image to load for 
				//character based on what direction they were facing
				if(up == true)
				{
				game.batch.draw(img2, mainP.getxCoordinate(), mainP.getyCoordinate(),width,height);
				}
				else if(right == true)
				{
				game.batch.draw(img3, mainP.getxCoordinate(), mainP.getyCoordinate(),width,height);
				}
				else if(down == true)
				{
				game.batch.draw(img4, mainP.getxCoordinate(), mainP.getyCoordinate(),width,height);
				}
				else if(left == true)
				{
				game.batch.draw(img5, mainP.getxCoordinate(), mainP.getyCoordinate(),width,height);
				}
				else
				{
					if(last == 0)
					{
						if(bull.getdirection() == -1)
						{
							bull.setdirection(0);
						}
					game.batch.draw(img2, mainP.getxCoordinate(), mainP.getyCoordinate(), width, height);
					}
					if(last == 1)
					{
						if(bull.getdirection() == -1)
						{
							bull.setdirection(1);
						}
						game.batch.draw(img3, mainP.getxCoordinate(), mainP.getyCoordinate(),width, height);
					}
					if(last == 2)
					{
						if(bull.getdirection() == -1)
						{
							bull.setdirection(2);
						}
						game.batch.draw(img4, mainP.getxCoordinate(), mainP.getyCoordinate(), width, height);
					}
					if(last == 3)
					{
						if(bull.getdirection() == -1)
						{
							bull.setdirection(3);
						}
						game.batch.draw(img5, mainP.getxCoordinate(), mainP.getyCoordinate(), width, height);
					}
				}
				
				//obsolete code from click to shoot attempts
				
				/*if(bull.getdirection() == 0)
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
				
				//where we actually draw the sprites every tick of the game
				
				bull.setx(bull.xcoordinate + bullSpeedX);
				bull.sety(bull.ycoordinate + bullSpeedY);
				if(!(bull.getx() >= 100000) && !(bull.gety()>=100000))
				{
				game.batch.draw(bullet, bull.getx(),  bull.gety(), width, height);
				}
				game.batch.end();
				left = false;
				right = false;
				up = false;
				down = false;
		}
			
			if(paused)
			{
			
			game.batch.begin();
			music.stop();
			
			game.batch.draw(backdrop, MyGdxGame.width/2 - backdropWidth/2,MyGdxGame.height/2 - backdropHeight/2,backdropWidth,backdropHeight);
			
			if(Gdx.input.getX() < x + continuebuttonWidth && Gdx.input.getX() > x && MyGdxGame.height - Gdx.input.getY() < continuebuttonY + continuebuttonHeight && MyGdxGame.height - Gdx.input.getY() > continuebuttonY)
			{
				game.batch.draw(continueActive, MyGdxGame.width/2 - continuebuttonWidth/2, MyGdxGame.height/2 - continuebuttonHeight/2,continuebuttonWidth,continuebuttonHeight);
				
				if(Gdx.input.isTouched())
				{
					this.dispose();
					resume();
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
				
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		paused = true;	
		
	}

	@Override
	public void resume() {
		paused = false;		
		music.play();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
