package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.MainGameScreen;

public class Bird extends ShooterEnemy{
	int direction;
	boolean fireable;
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			direction = (int) Math.floor(Math.random() * 5) + 1;
			fireable = true;
		}
	};
	
	// New code from Example Game
		public Body b2body;
		private TextureRegion texture;
		private Sprite sprite;
	
	Timer emimymove = new Timer(250, taskPerformer);
	public Bird(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, boolean inv,
			Texture img, SpriteBatch map, int pointval, OrthographicCamera camera, World world, MainGameScreen screen) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, map,  pointval, camera, world, screen);
		direction = 0;
		emimymove.setInitialDelay(10);
		emimymove.start();
		fireable =true;
		

		// New code from Example Game
		sprite = new Sprite(screen.getAtlas().findRegion("bird_left"));
		defineBird();
		texture = new TextureRegion(sprite.getTexture(), 515, 255, 24, 20);		
		setBounds(0, 0, 24 / MyGdxGame.PPM, 20 / MyGdxGame.PPM);
		setRegion(texture);
		
	}
	
	
	// New method from Example Game
		public void update(float dt) {

			setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
			setRegion(texture);

		}

		// New method from Example Game
		public void defineBird() {

			BodyDef bdef = new BodyDef();
			bdef.position.set(300 / MyGdxGame.PPM, 100 / MyGdxGame.PPM);
			bdef.type = BodyDef.BodyType.StaticBody;
			b2body = world.createBody(bdef);

			FixtureDef fdef = new FixtureDef();
			PolygonShape shape = new PolygonShape(); // This line has been changed
			shape.setAsBox(24 / MyGdxGame.PPM / 2, 20 / MyGdxGame.PPM / 2); // This line has been changed
			fdef.shape = shape;
			b2body.createFixture(fdef);

		}

	
	
//	
//	public void Draw() {
//		if(direction == 1) move(getPosX(), getPosY() + 1);
//		if(direction == 2) move(getPosX(), getPosY() - 1);
//		if(direction == 3) move(getPosX() + 1, getPosY());
//		if(direction == 4) move(getPosX() - 1, getPosY());
//		super.Draw();
//		
//		
//	}
	public Bullet trackPlayer(Entity player) {
		if(direction == 5) {
			if(fireable) {
				fireable = false;
				return super.trackPlayer(player);
			}
		}
		return null;
		
	}
	
}