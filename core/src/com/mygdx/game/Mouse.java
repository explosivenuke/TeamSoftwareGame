package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.MainGameScreen;

public class Mouse extends TimerEnemy{
	int direction;
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			direction = (int) Math.floor(Math.random() * 4) + 1;
		}
	};
	
	// New code from Example Game
		public Body b2body;
		private TextureRegion texture;
		private Sprite sprite;
	
	Timer emimymove = new Timer(1000, taskPerformer);
	public Mouse(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, boolean inv,
			Texture img, SpriteBatch map,  int pointval, World world, MainGameScreen screen) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, map, pointval, world, screen);
		direction = 0;
		emimymove.setInitialDelay(10);
		emimymove.start();
		

		// New code from Example Game
		sprite = new Sprite(screen.getAtlas().findRegion("mouse_left"));
		defineMouse();
		texture = new TextureRegion(sprite.getTexture(), 179, 44, 16, 11);
		setBounds(0, 0, 16 / MyGdxGame.PPM, 11 / MyGdxGame.PPM);
		setRegion(texture);
		
	}
	

	// New method from Example Game
	public void update(float dt) {

		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
		setRegion(texture);

	}

	// New method from Example Game
	public void defineMouse() {

		BodyDef bdef = new BodyDef();
		bdef.position.set(300 / MyGdxGame.PPM, 200 / MyGdxGame.PPM);
		bdef.type = BodyDef.BodyType.StaticBody;
		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(5 / MyGdxGame.PPM);

		fdef.shape = shape;
		b2body.createFixture(fdef);

	}
	
	
	public void Draw() {
		if(direction == 1) move(getPosX(), getPosY() + 1);
		if(direction == 2) move(getPosX(), getPosY() - 1);
		if(direction == 3) move(getPosX() + 1, getPosY());
		if(direction == 4) move(getPosX() - 1, getPosY());
		super.Draw();
		super.sizeX = 7;
		super.sizeY = 7;
	}
}