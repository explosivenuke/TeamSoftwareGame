package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.MainGameScreen;

public class powerup extends Entity{
	public char poweruptype;
	
	// New code
		public Body b2body;
		private TextureRegion texture;
		private Sprite sprite;
	
	
	public powerup(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth,
			double damage, boolean inv, Texture img, SpriteBatch map, boolean freindly,
			 int pointval, char powerup, World world, MainGameScreen screen) {
		super(spawnX, spawnY, 25, 25, moveSpeed, maxHealth, damage, inv, img, map, freindly, pointval, world, screen);
		// TODO Auto-generated constructor stub
		this.poweruptype = powerup;
		
		// New code
				sprite = new Sprite(screen.getAtlas().findRegion("bird_left"));
				definePowerup();
				// Put a switch statement or if else statement here to choose texture and bounds
				texture = new TextureRegion(sprite.getTexture(), 515, 255, 24, 20);
				setBounds(0, 0, 24 / MyGdxGame.PPM, 20 / MyGdxGame.PPM);
				setRegion(texture);
		
	}
	

	// New method
	public void update(float dt) {

		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
		setRegion(texture);

	}

	// New method
	public void definePowerup() {

		BodyDef bdef = new BodyDef();
		bdef.type = BodyDef.BodyType.StaticBody;
		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		// Put a switch statement or if else statement here to choose the dimensions of the box
		shape.setAsBox(24 / MyGdxGame.PPM / 2, 20 / MyGdxGame.PPM / 2);

		fdef.shape = shape;
		b2body.createFixture(fdef);

	}
	

}
