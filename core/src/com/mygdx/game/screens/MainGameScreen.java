package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.B2WorldCreator;
import com.mygdx.game.Bird;
import com.mygdx.game.Bullet;
import com.mygdx.game.Dog;
import com.mygdx.game.Entity;
import com.mygdx.game.Mouse;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player;
import com.mygdx.game.ShooterEnemy;
import com.mygdx.game.Spawner;
import com.mygdx.game.Tile;
import com.mygdx.game.TrackerEnemy;
import com.mygdx.game.powerup;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	Texture powerup1;
	Texture powerup2;
	Texture powerup3;
	Texture powerup4;
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	int last = 0;
	int screensize = 1000;
	int bulletheight = 2;
	float bulletspeed = 5;
	float BulletGap = 0;
	Rectangle rectanglePlayer;
	Rectangle rectangleCeiling;
	Rectangle rectangleFloor;
	Rectangle rectangleBullet;
	Tile healthBack;
	Sound shot;
	Music music;
	Player mainP;
	int direction = 0;
	boolean bulletIsLoaded = false;
	int width = 25;
	int height = 25;
	boolean paused = false;
	boolean isOverlappingCeiling;
	boolean isOverlappingFloor;
	boolean isBulletOverlappingCeiling;
	boolean isBulletOverlappingFloor;
	boolean reloading = false;
	int score = 0;
	ShapeRenderer shaperenderer;
	Tile ceiling;
	Tile floor;
	static int buttonGap = 10;
	BitmapFont font;
	float playerdamage;

	ArrayList<TrackerEnemy> trackers;
	ArrayList<Entity> basicEntity;
	ArrayList<powerup> powerups;
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
	private static int continuebuttonY = MyGdxGame.height / 2 - continuebuttonHeight / 2;
	private static int quitbuttonHeight = 50;
	private static int quitbuttonWidth = 300;
	private static int quitbuttonX = MyGdxGame.width / 2 - quitbuttonWidth / 2;
	private static int quitbuttonY = MyGdxGame.height / 2 - quitbuttonHeight / 2 - (buttonGap + continuebuttonHeight);

	// New code added from Example Game
	private TextureAtlas atlas;
	private OrthographicCamera camera;
	private Viewport gamePort;
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private World world;
	private Box2DDebugRenderer b2dr;
	private Dog dog1;
	private Mouse mouse1;
	private Bird bird1;

	int x = MyGdxGame.width / 2 - continuebuttonHeight;
	int remainingbullets = 100;
	int magcapacity = 100;
	int weaponWidth = 20;
	int weaponHeight = 20;
	int ratio;
	int bulletsize = 5;
	char type;

	Vector3 vect = new Vector3();
	Vector3 vect1 = new Vector3();
	Vector3 pcvect = new Vector3();
	Vector3 pevect = new Vector3();
	Texture continueActive;
	Texture continueInactive;
	Texture quitActive;
	Texture quitInactive;
	Texture backdrop;
	Texture shotgun;
	Texture pistol;
	Texture machinegun;
	Texture casing;
	public double basebulletspeed;

	// variables for controlling bullet speed
	float bullSpeedX;
	float bullSpeedY;
	boolean created = false;
	private OrthographicCamera hudcam;

	Timer timer = new Timer(250, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			reloading = false;
			remainingbullets = magcapacity;
			timer.stop();
		}
	});

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
		dog = new Texture("dog_left.png");
		mouse = new Texture("mouse_left.png");
		// squirl is actually bird
		squirl = new Texture("bird_left.png");
		bullet = new Texture("bullet.png");
		powerup1 = new Texture("potion1.png");
		powerup2 = new Texture("potion2.png");
		powerup3 = new Texture("potion3.png");
		powerup4 = new Texture("potion4.png");
		pistol = new Texture("pistol.png");
		shotgun = new Texture("shotgun.png");
		machinegun = new Texture("machinegun.png");
		casing = new Texture("casing.png");
		game.score = 0;
		type = 'm';
		backdrop = new Texture("backdrop.png");
		shot = Gdx.audio.newSound(Gdx.files.internal("pew.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

		// New code added from Example Game
		atlas = new TextureAtlas("player_and enemies.pack");
		camera = new OrthographicCamera();
		gamePort = new FitViewport(MyGdxGame.V_Width / MyGdxGame.PPM, MyGdxGame.V_Height / MyGdxGame.PPM, camera);
		maploader = new TmxMapLoader();
		map = maploader.load("Map1_4.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / MyGdxGame.PPM);
		camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		world = new World(new Vector2(0, 0), true);
		b2dr = new Box2DDebugRenderer();
		new B2WorldCreator(world, map);
		createCollisionListener();

	}

	// New Method
	private void createCollisionListener() {

		world.setContactListener(new ContactListener() {

			@Override
			public void beginContact(Contact contact) {
				// TODO Auto-generated method stub

			}

			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub

			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub

			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub

			}

		});

	}

	public TextureAtlas getAtlas() {

		return atlas;

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

		// Show the Tiled Map
		hudcam = new OrthographicCamera();

		mainP = new Player(50, 50, 10, 10, 2, 100, false, 100, world, this);

		// Changed
		trackers = new ArrayList<TrackerEnemy>();
		dog1 = Spawner.spawnDog(50, 50, 5, 5, 1.5f, 3, 25, dog, game.batch, 10, world, this);
		trackers.add(dog1);
		shooters = new ArrayList<ShooterEnemy>();
		bird1 = Spawner.spawnBird(50, 40, 5, 5, 1, 3, 10, squirl, game.batch, 5, camera, world, this);
		shooters.add(bird1);
		basicEntity = new ArrayList<Entity>();
		mouse1 = Spawner.spawnMouse(50, 30, 5, 5, 1, 3, 10, mouse, game.batch, 1, world, this);
		basicEntity.add(mouse1);
		powerups = new ArrayList<powerup>();
		powerups.add(Spawner.spawnPowerup(50, 20, 10, 10, 0, 100000, 0, powerup1, game.batch, 'h', world, this));
		hudcam.position.set(MyGdxGame.width, MyGdxGame.height, 0);
		hudcam.setToOrtho(false, MyGdxGame.width, MyGdxGame.height);

		powerups = new ArrayList<powerup>();
		// powerups.add(Spawner.spawnPowerup(MyGdxGame.width / 2, MyGdxGame.height / 2,
		// 10, 10, 0, 100000, 0, powerup1,
		// game.batch, (TiledMapTileLayer) map.getLayers().get(0), 'h'));
		powerups.add(Spawner.spawnPowerup(MyGdxGame.width / 2, MyGdxGame.height / 2, 10, 10, 0, 100000, 0, powerup2,
				game.batch, 'd', world, this));

		hudcam.position.set(MyGdxGame.width, MyGdxGame.height, 0);
		hudcam.setToOrtho(false, MyGdxGame.width, MyGdxGame.height);
		playerdamage = 1;
		bulletspeed = 5;
		basebulletspeed = bulletspeed;

		mainP.basedamage = mainP.damage;
	}

	// Updated
	public void handleInput(float dt) {

		float x = 0;
		float y = 0;

		if (Gdx.input.isKeyPressed(Input.Keys.UP) && mainP.b2body.getLinearVelocity().y <= 2) {

			y += mainP.speed;
			// mainP.b2body.applyLinearImpulse(new Vector2(0, 0.1f),
			// mainP.b2body.getWorldCenter(), true);

		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && mainP.b2body.getLinearVelocity().y >= -2) {

			y -= mainP.speed;
			// mainP.b2body.applyLinearImpulse(new Vector2(0, -0.1f),
			// mainP.b2body.getWorldCenter(), true);

		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && mainP.b2body.getLinearVelocity().x >= -2) {

			x -= mainP.speed;
			// mainP.b2body.applyLinearImpulse(new Vector2(-0.1f, 0),
			// mainP.b2body.getWorldCenter(), true);

		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mainP.b2body.getLinearVelocity().x <= 2) {

			x += mainP.speed;
			// mainP.b2body.applyLinearImpulse(new Vector2(0.1f, 0),
			// mainP.b2body.getWorldCenter(), true);

		}

		if (x != 0) {

			mainP.b2body.setLinearVelocity(x * 100 * dt, mainP.b2body.getLinearVelocity().y);

		}

		if (y != 0) {

			mainP.b2body.setLinearVelocity(mainP.b2body.getLinearVelocity().x, y * 100 * dt);

		}

		// Handle Dog movement here

		// Handle Mouse movement here

		// Handle Squirl/Bird movement here

	}

	// Updated
	public void update(float dt) {

		handleInput(dt);

		world.step(1 / 60f, 6, 2);

		mainP.update(dt);

		// Update Dog
		dog1.update(dt);

		// Update Mouse
		mouse1.update(dt);

		// Update Squirl/Bird
		bird1.update(dt);

		camera.position.x = mainP.b2body.getPosition().x;
		camera.position.y = mainP.b2body.getPosition().y;

		camera.update();
		renderer.setView(camera);

	}

	@Override
	public void render(float delta) {

		ratio = (int) ((mainP.getHealth() / mainP.maxHealth) * (healthbackWidth - 20));

		// clearing color and setting background color
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Set camera position
		// camera.position.set(mainP.getPosX() + mainP.sizeX / 2, mainP.getPosY() +
		// mainP.sizeY / 2, 0);
		// camera.update();

		// Render Tiled Map
		renderer.setView(camera);

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {

			if (paused) {
				resume();
			} else {
				pause();

			}

			// game.setScreen(new PauseMenuScreen(game,main));

		}
		// game.batch.begin();
		game.batch.setProjectionMatrix(camera.combined);
		renderer.render();
		vect1.x = mainP.getCenterX();
		vect1.y = mainP.getCenterY();
		if (!paused) {

			// New code added from Example Game
			update(delta);
			renderer.render();
			b2dr.render(world, camera.combined);
			game.batch.setProjectionMatrix(camera.combined);
			game.batch.begin();
			mainP.draw(game.batch);
			dog1.draw(game.batch);
			mouse1.draw(game.batch);
			bird1.draw(game.batch);
			game.batch.end();

			if (Gdx.input.isKeyPressed(Keys.W)) {
				if (!Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					// if(!isOverlappingCeiling)
					{

						up = true;
						last = 0;
						// mainP.setYCoordinate(mainP.getyCoordinate() + mainP.speed);
						// rectanglePlayer.setPosition(mainP.getxCoordinate(), mainP.getyCoordinate() +
						// mainP.speed);
					}
				}
			}
			if (Gdx.input.isKeyPressed(Keys.S)) {
				if (!Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					// if(!isOverlappingFloor)
					{
						last = 2;
						down = true;
						// mainP.setYCoordinate(mainP.getyCoordinate() - mainP.speed);
						// rectanglePlayer.setPosition(mainP.getxCoordinate(), mainP.getyCoordinate() -
						// mainP.speed);
					}
				}
			}
			if (Gdx.input.isKeyPressed(Keys.D)) {
				if (!Gdx.input.isKeyJustPressed(Keys.SPACE)) {

					last = 1;
					right = true;
					// mainP.setXCoordinate(mainP.getxCoordinate() + mainP.speed);
					// rectanglePlayer.setPosition(mainP.getxCoordinate() + mainP.speed,
					// mainP.getyCoordinate());
				}
			}
			if (Gdx.input.isKeyPressed(Keys.A)) {
				if (!Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					last = 3;
					left = true;
					// mainP.setXCoordinate(mainP.getxCoordinate() - mainP.speed);
					// rectanglePlayer.setPosition(mainP.getxCoordinate() - mainP.speed,
					// mainP.getyCoordinate());
				}
			}
			// if(direction == 1) mainP.setYCoordinate(mainP.getyCoordinate() + 1);
			// if(direction == 2) mainP.setYCoordinate(mainP.getyCoordinate() - 1);
			// if(direction == 3) mainP.setXCoordinate(mainP.getxCoordinate() + 1);
			// if(direction == 4) mainP.setXCoordinate(mainP.getxCoordinate() - 1);

			// allows player to shoot using space bar is obsolete and will be replaced
			// by mouse once fully functioning

			// takes mouse click position and fires bullet at correct angle and speed
			// accordingly
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

				if ((bulletIsLoaded || type == 'm') && !reloading) {
					remainingbullets -= 1;

					if (remainingbullets < 1) {
						reload(type);

					}

					Vector3 vecto = new Vector3();
					Vector3 v = new Vector3();
					float targetX = Gdx.input.getX() - 10;
					float targetY = Gdx.input.getY();
					vecto.x = targetX;
					vecto.y = targetY;
					v.x = mainP.getCenterX();
					v.y = mainP.getCenterY();
					v.z = 0;
					camera.unproject(vecto);
					camera.unproject(v);

					vect.x = MyGdxGame.width / 2;
					vect.y = MyGdxGame.height / 2;
					camera.unproject(vect);
					basicEntity.add(Spawner.spawnBullet(vect.x - 5, vect.y + 5, bulletsize, bulletsize, bulletspeed,
							playerdamage, true, game.batch, true, vecto.x, vecto.y, camera, world, this));
					shot.play(1.0f);

					if (type == 's') {

						float disY = vecto.y - vect.y;
						float disX = vecto.x - vect.x;
						float dis = (float) Math.sqrt(Math.pow(disX, 2) + Math.pow(disY, 2));
						float tarY = disY / dis;
						float tarX = disX / dis;
						bulletsize = 3;
						bulletspeed = (float) (basebulletspeed * 2 / 5);
						for (int i = 0; i <= 3; i++) {
							basicEntity.add(Spawner.spawnBullet(vect.x - 5, vect.y + 5, bulletsize, bulletsize,
									bulletspeed, playerdamage, true, game.batch, true, tarX - i, tarY + i, camera,
									world, this));
							basicEntity.add(Spawner.spawnBullet(vect.x - 5, vect.y + 5, bulletsize, bulletsize,
									bulletspeed, playerdamage, true, game.batch, true, tarX + i, tarY - i, camera,
									world, this));
						}
					}

					bulletIsLoaded = false;
				}
			}
			if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				bulletIsLoaded = true;
			}
		}

		// game.batch.begin();
		// determines what image to load for
		// character based on what direction they were facing

		if (!paused) {

			game.batch.begin();

			// for (ShooterEnemy e : shooters) {
			// Bullet b = e.trackPlayer(mainP);
			// if (b != null) {
			// basicEntity.add(b);
			// }
			// if (e.collide(mainP.collision)) {
			// if (e.damage > 0) {
			// mainP.setHealth(mainP.getHealth() - e.damage, e);
			// }
			//
			// }
			// e.Draw();
			// }
			//
			//
			// for (TrackerEnemy e : trackers) {
			// e.trackPlayer(mainP);
			// if (e.collide(mainP.collision)) {
			// if (e.damage > 0) {
			// mainP.setHealth(mainP.getHealth() - e.damage, e);
			// }
			// }
			// e.Draw();
			// }
			// boolean clear = true;
			// for (Entity e : basicEntity) {
			// if (e.fof != mainP.fof) {
			// clear = false;
			// if (e.collide(mainP.collision)) {
			//
			// if (e.damage > 0) {
			// mainP.setHealth(mainP.getHealth() - e.damage, e);
			//
			// e.hurt(5);
			// }
			// }
			//
			// }
			// for (Entity w : basicEntity) {
			// if (e.collide(w.collision)) {
			// if (e.fof != w.fof) {
			// if (e.damage > 0 && w.damage > 0) {
			// e.hurt(w.damage);
			// game.score += e.pointval;
			//
			// w.hurt(e.damage);
			// }
			//
			// }
			// }
			// }
			// for (Entity w : trackers) {
			// if (e.collide(w.collision)) {
			// if (e.fof != w.fof) {
			// if (e.damage > 0 && w.damage > 0) {
			// e.hurt(w.damage);
			//
			// game.score += e.pointval;
			// game.score += w.pointval;
			// w.hurt(e.damage);
			// }
			// }
			// }
			// }
			// for (Entity w : shooters) {
			// if (e.collide(w.collision)) {
			// if (e.fof != w.fof) {
			//
			// if (e.damage > 0 && w.damage > 0) {
			// e.hurt(w.damage);
			// game.score += e.pointval;
			// game.score += w.pointval;
			// w.hurt(e.damage);
			// }
			//
			// }
			// }
			// }

			// e.Draw();

			// if (shooters.isEmpty() && trackers.isEmpty() && clear) {
			for (powerup e : powerups) {

				if (e.collide(mainP.collision)) {
					e.health = -1;

					if (e.poweruptype == 'h') {
						mainP.maxHealth += 5;
						mainP.hurt(-5);
						healthbackWidth += 5;
					}

					if (e.poweruptype == 'm') {
						mainP.speed += 0.5;

					}

					if (e.poweruptype == 'd') {

						mainP.basedamage *= 1.25;
						mainP.damage *= 1.25;
					}

					if (e.poweruptype == 'b') {
						basebulletspeed += 5;

					}

					if (e.poweruptype == 'p') {
						mainP.damage = mainP.basedamage * 2;

					}

					if (e.poweruptype == 's') {
						mainP.damage = mainP.basedamage * 1.5;
						bulletspeed = (float) (basebulletspeed * 0.8);
					}

					if (e.poweruptype == 'm') {
						mainP.damage = mainP.basedamage * 1.5;
						bulletspeed = (float) (basebulletspeed * 5);
					}

				}
				e.Draw();
			}
		}
		// if (up == true) {
		// game.batch.draw(img2, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX,
		// mainP.sizeY);
		// } else if (right == true) {
		// game.batch.draw(img3, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX,
		// mainP.sizeY);
		// } else if (down == true) {
		// game.batch.draw(img4, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX,
		// mainP.sizeY);
		// } else if (left == true) {
		// game.batch.draw(img5, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX,
		// mainP.sizeY);
		// } else {
		// if (last == 0) {
		//
		// game.batch.draw(img2, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX,
		// mainP.sizeY);
		// }
		// if (last == 1) {
		//
		// game.batch.draw(img3, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX,
		// mainP.sizeY);
		// }
		// if (last == 2) {
		//
		// game.batch.draw(img4, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX,
		// mainP.sizeY);
		// }
		// if (last == 3) {
		//
		// game.batch.draw(img5, mainP.getCenterX(), mainP.getCenterY(), mainP.sizeX,
		// mainP.sizeY);
		// }
		// }
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

		Iterator<powerup> powerupss = powerups.iterator();
		while (powerupss.hasNext()) {
			Entity help = powerupss.next();
			if (help.health <= 0) {
				powerupss.remove();
			}
		}

		// New code
		if (!paused) {
			int numContacts = world.getContactCount();

			if (numContacts > 0) {

				System.out.println("Begin Contact");

				for (Contact contact : world.getContactList()) {

					Fixture fixtureA = contact.getFixtureA();
					Fixture fixtureB = contact.getFixtureB();

					Array<Fixture> playerFixtures = mainP.b2body.getFixtureList();
					Array<Fixture> dogFixtures = dog1.b2body.getFixtureList();
					Array<Fixture> mouseFixtures = mouse1.b2body.getFixtureList();
					Array<Fixture> birdFixtures = bird1.b2body.getFixtureList();
					Fixture playerFixture = playerFixtures.get(0);
					Fixture dogFixture = dogFixtures.get(0);
					Fixture mouseFixture = mouseFixtures.get(0);
					Fixture birdFixture = birdFixtures.get(0);

					if (fixtureA.equals(playerFixture)) {

						if (fixtureB.equals(dogFixture) || fixtureB.equals(mouseFixture)
								|| fixtureB.equals(birdFixture)) {

							// System.out.println("Contact A!");

						}

					} else if (fixtureB.equals(playerFixture)) {

						if (fixtureA.equals(dogFixture) || fixtureA.equals(mouseFixture)
								|| fixtureA.equals(birdFixture)) {

							// System.out.println("Contact B!");

						}

					}

					// System.out.println("Contact between " + fixtureA.toString() + " and " +
					// fixtureB.toString());

				}

				// System.out.println("End Contact");

			}

			game.batch.end();
		}
		game.batch.begin();
		if (type == 'p')

		{
			magcapacity = 10;
			bulletsize = 5;
			basebulletspeed = 5;

			game.batch.setProjectionMatrix(hudcam.combined);
			renderer.setView(hudcam);

			game.batch.draw(pistol, 50, 50, weaponWidth + 80, weaponHeight + 40);

			for (int i = remainingbullets; i > 0; i--) {

				game.batch.draw(casing, 40 + 15 * i, 15, weaponWidth + 20, weaponHeight + 10);

			}

		}

		if (type == 's') {
			game.batch.setProjectionMatrix(hudcam.combined);
			renderer.setView(hudcam);
			magcapacity = 5;
			game.batch.draw(shotgun, 50, 75, weaponWidth + 150, weaponHeight + 30);

			for (int i = remainingbullets; i > 0; i--) {

				game.batch.draw(casing, 20 + 20 * i, 5, weaponWidth + 30, weaponHeight + 40);

			}

		}

		if (type == 'm') {
			magcapacity = 250;
			bulletsize = 1;
			bulletspeed = (float) (basebulletspeed * 3 / 10);
			game.batch.setProjectionMatrix(hudcam.combined);
			renderer.setView(hudcam);

			game.batch.draw(machinegun, 50, 50, weaponWidth + 180, weaponHeight + 50);

			for (int i = remainingbullets; i > 0; i--) {

				game.batch.draw(casing, 40 + 10 * i, 15, weaponWidth + 20, weaponHeight + 10);

			}

		}

		game.batch.end();
		game.batch.setProjectionMatrix(hudcam.combined);
		game.batch.begin();
		font.draw(game.batch, "Score:  " + Integer.toString(game.score), game.width - game.width / 10,
				game.height - game.height / 20);
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
			music.stop();
			game.setScreen(new MainMenuScreen(game, game.score, hudcam));
		}

		if (paused) {
			game.batch.setProjectionMatrix(hudcam.combined);
			renderer.setView(hudcam);
			renderer.render();
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
					// this.dispose();
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
			font.draw(game.batch, "Score:  " + Integer.toString(game.score), game.width - game.width / 10,
					game.height - game.height / 20);

			game.batch.end();
		}

	}

	private void reload(char type) {
		reloading = true;

		if (type == 'p') {
			timer.setInitialDelay(1000);

		}

		if (type == 's') {
			timer.setInitialDelay(4000);

		}

		if (type == 'm') {
			timer.setInitialDelay(5000);

		}

		timer.start();

	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);

		hudcam.viewportWidth = width / 5;
		hudcam.viewportHeight = height / 5;
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
		dispose();
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();

	}

}
