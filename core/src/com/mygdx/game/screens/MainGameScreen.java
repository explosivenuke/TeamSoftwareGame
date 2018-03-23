package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Bullet;
import com.mygdx.game.Entity;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player;
import com.mygdx.game.ShooterEnemy;
import com.mygdx.game.Spawner;
import com.mygdx.game.Tile;
import com.mygdx.game.TrackerEnemy;

import java.util.ArrayList;
import java.util.Iterator;

public class MainGameScreen implements Screen {
	MyGdxGame game;
	MainGameScreen main;
	Texture img;
	Texture img1;
	Texture img2;
	Texture img3;
	Texture img4;
	Texture img5;
	Texture bullet;
	Texture squirl;
	Texture dog;
	Texture mouse;
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	int last = 0;
	int screensize = 1000;
	int bulletheight = 2;
	float bulletSpeed = 2;
	float BulletGap = 0;
	Rectangle rectanglePlayer;
	Rectangle rectangleCeiling;
	Rectangle rectangleFloor;
	Rectangle rectangleBullet;
	Tile healthBack;
	Sound shot;
	Music music;
	Player mainP = new Player(50, 50, 30, 30, 2, 100, false);
	int direction = 0;
	boolean bulletIsLoaded = false;
	int width = 25;
	int height = 25;
	boolean paused = false;
	boolean isOverlappingCeiling;
	boolean isOverlappingFloor;
	boolean isBulletOverlappingCeiling;
	boolean isBulletOverlappingFloor;

	ShapeRenderer shaperenderer;
	Tile ceiling;
	Tile floor;
	static int buttonGap = 10;
	BitmapFont font;

	ArrayList<TrackerEnemy> trackers;
	ArrayList<Entity> basicEntity;
	ArrayList<ShooterEnemy> shooters;

	private static int backdropHeight = MyGdxGame.height - 120;
	private static int backdropWidth = 500;
	private static int backdropX = MyGdxGame.width / 2 - backdropWidth / 2;
	private static int backdropY = MyGdxGame.height / 2 - backdropHeight / 2;

	private static int healthbackHeight = 50;
	private static int healthbackWidth = MyGdxGame.width / 4;
	private static int healthbackX = 0 + buttonGap;
	private static int healthbackY = MyGdxGame.height + 10 - 2 * healthbackHeight;

	private static int continuebuttonHeight = 50;
	private static int continuebuttonWidth = 300;
	private static int continuebuttonX = MyGdxGame.width / 2 - continuebuttonWidth / 2;
	private static int continuebuttonY = MyGdxGame.height / 2 - continuebuttonHeight / 2;
	private static int quitbuttonHeight = 50;
	private static int quitbuttonWidth = 300;
	private static int quitbuttonX = MyGdxGame.width / 2 - quitbuttonWidth / 2;
	private static int quitbuttonY = MyGdxGame.height / 2 - quitbuttonHeight / 2 - (buttonGap + continuebuttonHeight);
	int x = MyGdxGame.width / 2 - continuebuttonHeight;
	int ratio;

	Texture continueActive;
	Texture continueInactive;
	Texture quitActive;
	Texture quitInactive;
	Texture backdrop;

	// variables for controlling bullet speed
	float bullSpeedX;
	float bullSpeedY;
	boolean created = false;

	public MainGameScreen(MyGdxGame game) {
		this.game = game;
		main = game.main;
		font = new BitmapFont();
		font.getData().setScale(1);
		font.setColor(Color.WHITE);
		shaperenderer = new ShapeRenderer();
		healthBack = new Tile(0, MyGdxGame.height, 100, 100);
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
		// dog = new Texture("dog_left.png");
		// mouse = new Texture("mouse_left.png");
		// squirl is actually bird
		// squirl = new Texture("bird_left.png");
		bullet = new Texture("bullet.png");
		dog = bullet;
		squirl = bullet;
		mouse = bullet;
		trackers = new ArrayList<TrackerEnemy>();
		trackers.add(Spawner.spawnDog(500, 500, 10, 10, 2, 3, 100, dog, game.batch));
		shooters = new ArrayList<ShooterEnemy>();
		shooters.add(
				Spawner.spawnSquirl(MyGdxGame.width / 2, MyGdxGame.height / 2, 10, 10, 1, 3, 100, squirl, game.batch));
		basicEntity = new ArrayList<Entity>();
		basicEntity.add(
				Spawner.spawnMouse(MyGdxGame.width / 2, MyGdxGame.height / 2, 10, 10, 1, 3, 100, mouse, game.batch));
		backdrop = new Texture("backdrop.png");
		shot = Gdx.audio.newSound(Gdx.files.internal("pew.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
	}

	@Override
	public void show() {
		// creating shot sound and background music

		// Gdx.graphics.setWindowedMode(1000, 10000);

		// creating the sprites and their textures

		// creating boundary for collision on player and bullet,
		// the bullet not being implemented fully yet
		music.isLooping();
		music.play();
		// booleans that are checking collision

		// calcuates rise and run between player and bullet click position and slope
		float rise;
		float run;

		float slope;
	}

	@Override
	public void render(float delta) {

		ratio = (int) ((mainP.getHealth() / 100) * (healthbackWidth - 20));

		// clearing color and setting background color
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// creating the ceiling boundary and floor boundary

		// rectanglePlayer = new Rectangle(
		// mainP.getxCoordinate(),mainP.getyCoordinate(),img2.getWidth(),img2.getHeight());
		//// if(!created)
		//// {
		// Tile ceiling = new Tile(0,Gdx.graphics.getHeight()-((float)
		// (Gdx.graphics.getHeight()*0.10)),Gdx.graphics.getWidth(),(float)
		// (Gdx.graphics.getHeight()*0.10));
		// ceiling.renderTile();
		// Tile floor = new Tile(0,0,Gdx.graphics.getWidth(),(float)
		// (Gdx.graphics.getHeight()*0.10));
		// floor.renderTile();
		//// }
		//
		//// if(!created)
		//// {
		// rectangleCeiling = new
		// Rectangle(ceiling.getXCoordinate(),ceiling.getYCoordinate(),ceiling.getWidth(),ceiling.getHeight());
		// rectangleFloor = new
		// Rectangle(floor.getXCoordinate(),floor.getYCoordinate(),floor.getWidth(),floor.getHeight());
		// created = true;
		//// }
		//
		// //booleans that are checking collision
		// isOverlappingCeiling = rectanglePlayer.overlaps(rectangleCeiling);
		// isOverlappingFloor = rectanglePlayer.overlaps(rectangleFloor);
		//

		// controls players action based on input and checks to see if space is pressed
		// while
		// they are moving so that the bullet can travel while player is moving
		// also does the check for collision and responds accordingly

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			this.dispose();

			if (paused) {
				resume();
			} else {
				pause();

			}

			// game.setScreen(new PauseMenuScreen(game,main));

		}

		if (!paused) {

			if (Gdx.input.isKeyPressed(Keys.UP)) {
				if (!Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					// if(!isOverlappingCeiling)
					{

						up = true;
						last = 0;
						mainP.setYCoordinate(mainP.getyCoordinate() + mainP.speed);
						// rectanglePlayer.setPosition(mainP.getxCoordinate(), mainP.getyCoordinate() +
						// mainP.speed);
					}
				}
			}
			if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				if (!Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					// if(!isOverlappingFloor)
					{
						last = 2;
						down = true;
						mainP.setYCoordinate(mainP.getyCoordinate() - mainP.speed);
						// rectanglePlayer.setPosition(mainP.getxCoordinate(), mainP.getyCoordinate() -
						// mainP.speed);
					}
				}
			}
			if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				if (!Gdx.input.isKeyJustPressed(Keys.SPACE)) {

					last = 1;
					right = true;
					mainP.setXCoordinate(mainP.getxCoordinate() + mainP.speed);
					// rectanglePlayer.setPosition(mainP.getxCoordinate() + mainP.speed,
					// mainP.getyCoordinate());
				}
			}
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				if (!Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					last = 3;
					left = true;
					mainP.setXCoordinate(mainP.getxCoordinate() - mainP.speed);
					// rectanglePlayer.setPosition(mainP.getxCoordinate() - mainP.speed,
					// mainP.getyCoordinate());
				}
			}
			// if(direction == 1) mainP.setYCoordinate(mainP.getyCoordinate() + 1);
			// if(direction == 2) mainP.setYCoordinate(mainP.getyCoordinate() - 1);
			// if(direction == 3) mainP.setXCoordinate(mainP.getxCoordinate() + 1);
			// if(direction == 4) mainP.setXCoordinate(mainP.getxCoordinate() - 1);

			// allows player to shoot using space bar is obsolete and will be replaced
			// by mouse oonce fully functioning

			// takes mouse click position and fires bullet at correct anle and speed
			// accordingly
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

				if (bulletIsLoaded) {
					float targetX = Gdx.input.getX() - 10;
					float targetY = Gdx.graphics.getHeight() - 1 - Gdx.input.getY();
					basicEntity.add(Spawner.spawnBullet(mainP.getCenterX(), mainP.getCenterY(), 10, 10, 2, 100, true,
							game.batch, true, targetX, targetY));
					shot.play(1.0f);
					bulletIsLoaded = false;
				}
			}
			if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				bulletIsLoaded = true;
			}
		}

		game.batch.begin();
		// determines what image to load for
		// character based on what direction they were facing
		if (up == true) {
			game.batch.draw(img2, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX, mainP.sizeY);
		} else if (right == true) {
			game.batch.draw(img3, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX, mainP.sizeY);
		} else if (down == true) {
			game.batch.draw(img4, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX, mainP.sizeY);
		} else if (left == true) {
			game.batch.draw(img5, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX, mainP.sizeY);
		} else {
			if (last == 0) {

				game.batch.draw(img2, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX, mainP.sizeY);
			}
			if (last == 1) {

				game.batch.draw(img3, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX, mainP.sizeY);
			}
			if (last == 2) {

				game.batch.draw(img4, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX, mainP.sizeY);
			}
			if (last == 3) {

				game.batch.draw(img5, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX, mainP.sizeY);
			}
		}

		if (!paused) {
			for (ShooterEnemy e : shooters) {
				Bullet b = e.trackPlayer(mainP);
				if (b != null) {
					basicEntity.add(b);
				}
				if (e.collide(mainP.collision))
					mainP.hurt(e.damage);
				e.Draw();
			}
			for (TrackerEnemy e : trackers) {
				e.trackPlayer(mainP);
				if (e.collide(mainP.collision))
					mainP.hurt(e.damage);
				e.Draw();
			}
			for (Entity e : basicEntity) {
				for (Entity w : basicEntity) {
					if (e.collide(w.collision)) {
						if (e.fof != w.fof) {
							e.hurt(w.damage);
							w.hurt(e.damage);
						}
					}
				}
				for (Entity w : trackers) {
					if (e.collide(w.collision)) {
						if (e.fof != w.fof) {
							e.hurt(w.damage);
							w.hurt(e.damage);
						}
					}
				}
				for (Entity w : shooters) {
					if (e.collide(w.collision)) {
						if (e.fof != w.fof) {
							e.hurt(w.damage);
							w.hurt(e.damage);
						}
					}
				}

				e.Draw();
			}
			Iterator<Entity> test = basicEntity.iterator();
			while (test.hasNext()) {
				Entity help = test.next();
				if (help.health <= 0) {
					test.remove();
				}
			}
			Iterator<TrackerEnemy> test2 = trackers.iterator();
			while (test2.hasNext()) {
				Entity help = test2.next();
				if (help.health <= 0) {
					test2.remove();
				}
			}
			Iterator<ShooterEnemy> test3 = shooters.iterator();
			while (test3.hasNext()) {
				Entity help = test3.next();
				if (help.health <= 0) {
					test3.remove();
				}
			}
		}
		game.batch.end();
		shaperenderer.begin(ShapeType.Filled);
		shaperenderer.setColor(Color.BLUE);
		shaperenderer.rect(healthbackX, healthbackY, healthbackWidth, healthbackHeight);
		shaperenderer.end();

		shaperenderer.begin(ShapeType.Filled);
		shaperenderer.setColor(Color.RED);
		shaperenderer.rect(healthbackX + 10, healthbackY + 10, ratio, healthbackHeight - 20);
		shaperenderer.end();
		// font.draw(game.batch,"HP:",healthbackX,healthbackY + 40 );

		left = false;
		right = false;
		up = false;
		down = false;
		if (mainP.getHealth() <= 0) {
			// will change the screen back to main menu for use on death of player
			game.setScreen(new MainMenuScreen(game));
		}

		if (paused) {

			game.batch.begin();
			music.stop();

			game.batch.draw(backdrop, MyGdxGame.width / 2 - backdropWidth / 2,
					MyGdxGame.height / 2 - backdropHeight / 2, backdropWidth, backdropHeight);

			if (Gdx.input.getX() < x + continuebuttonWidth && Gdx.input.getX() > x
					&& MyGdxGame.height - Gdx.input.getY() < continuebuttonY + continuebuttonHeight
					&& MyGdxGame.height - Gdx.input.getY() > continuebuttonY) {
				game.batch.draw(continueActive, MyGdxGame.width / 2 - continuebuttonWidth / 2,
						MyGdxGame.height / 2 - continuebuttonHeight / 2, continuebuttonWidth, continuebuttonHeight);

				if (Gdx.input.isTouched()) {
					this.dispose();
					resume();
				}

			} else {
				game.batch.draw(continueInactive, MyGdxGame.width / 2 - continuebuttonWidth / 2,
						MyGdxGame.height / 2 - continuebuttonHeight / 2, continuebuttonWidth, continuebuttonHeight);

			}

			if (Gdx.input.getX() < x + quitbuttonWidth && Gdx.input.getX() > x
					&& MyGdxGame.height - Gdx.input.getY() < quitbuttonY + quitbuttonHeight
					&& MyGdxGame.height - Gdx.input.getY() > quitbuttonY) {

				game.batch.draw(quitActive, MyGdxGame.width / 2 - quitbuttonWidth / 2,
						MyGdxGame.height / 2 - quitbuttonHeight / 2 - (buttonGap + continuebuttonHeight),
						quitbuttonWidth, quitbuttonHeight);

				if (Gdx.input.isTouched()) {
					Gdx.app.exit();
				}

			} else {

				game.batch.draw(quitInactive, MyGdxGame.width / 2 - quitbuttonWidth / 2,
						MyGdxGame.height / 2 - quitbuttonHeight / 2 - (buttonGap + continuebuttonHeight),
						quitbuttonWidth, quitbuttonHeight);

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
		music.pause();

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
