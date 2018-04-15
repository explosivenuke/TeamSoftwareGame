package com.mygdx.game;

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

public class Dog extends TrackerEnemy{

	
	// New code from Example Game
			public Body b2body;
			private TextureRegion texture;
			private Sprite sprite;
	
	public Dog(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, boolean inv, Texture img, SpriteBatch batch, int pointval, World world, MainGameScreen screen) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, batch, pointval, world, screen);
	
		// New code from Example Game
				sprite = new Sprite(screen.getAtlas().findRegion("dog_left"));
				defineDog();
				texture = new TextureRegion(sprite.getTexture(), 1, 14, 46, 35); // This line has been changed
				setBounds(0, 0, 46 / MyGdxGame.PPM, 35 / MyGdxGame.PPM);
				setRegion(texture);
				
	}
	
	
public void update(float dt) {
		
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
		setRegion(texture);
		
	}

	// New method from Example Game
	public void defineDog() {
		
		BodyDef bdef = new BodyDef();
		bdef.position.set(300 / MyGdxGame.PPM, 300 / MyGdxGame.PPM);
		bdef.type = BodyDef.BodyType.StaticBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape(); // This line has been changed
		shape.setAsBox(46 / MyGdxGame.PPM / 2, 35 / MyGdxGame.PPM / 2); // This line has been changed
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
	}
	
	public void Draw() {
		batch.draw(sprite, posX, posY, sizeX+10, sizeY+10);
	}
	
	
}