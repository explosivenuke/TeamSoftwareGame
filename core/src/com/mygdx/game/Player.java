package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screens.MainGameScreen;

public class Player extends Entity {

	// variables for player position health and speed and their corresponding
	// getter and setters

	public int speed = 2;
	public boolean justhit;
	public int maxHealth;
	Timer emimymove = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			justhit = false;
			emimymove.stop();
		}
	});

	
	public enum State{UP, DOWN, LEFT, RIGHT};
	public State currentState;
	public State previousState;
	public Body b2body;
	public TextureRegion playerUp;
	private Array<TextureRegion> frames;
	private Sprite sprite;
	
	// New code
		FixtureDef fdef;
		PolygonShape shape;
	
	
	public Player(float initialX, float initialY, float sizeX, float sizeY, float speed, double initialHealth,
			boolean inv, int maxHealth, World world, MainGameScreen screen) {
		super(initialX, initialY, sizeX, sizeY, speed, initialHealth, 0, inv, null, null, true, 0, world, screen);
		this.maxHealth = maxHealth;
		
		sprite = new Sprite(screen.getAtlas().findRegion("player"));
		currentState = State.UP;
		previousState = State.UP;
		frames = new Array<TextureRegion>();
		// player_up
		frames.add(new TextureRegion(sprite.getTexture(), 585, 276, 14, 25)); // This line has been changed
		// player_down
		frames.add(new TextureRegion(sprite.getTexture(), 569, 278, 14, 23)); // This line has been changed
		// player_left
		frames.add(new TextureRegion(sprite.getTexture(), 543, 282, 24, 19)); // This line has been changed
		// player_right
		frames.add(new TextureRegion(sprite.getTexture(), 516, 281, 25, 20)); // This line has been changed
		definePlayer();
		playerUp = new TextureRegion(sprite.getTexture(), 585, 276, 14, 25); // This line has been changed
		setBounds(0, 0, 14 / MyGdxGame.PPM, 25 / MyGdxGame.PPM);
		setRegion(playerUp);
		b2body.setLinearDamping(20f);
		

	}
	
	// New method from Example Game
		public void update(float dt) {
			
			setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
			setRegion(getFrame(dt));
			
			// New code
			shape = (PolygonShape) getShape();
			
		}
		
		// New method
		public Shape getShape() {
			
			currentState = getState();
			
			PolygonShape polygonShape = new PolygonShape();
			
			switch(currentState) {
				case UP:
					polygonShape.setAsBox(14 / MyGdxGame.PPM / 2, 25 / MyGdxGame.PPM / 2);
					break;
				case DOWN:
					polygonShape.setAsBox(14 / MyGdxGame.PPM / 2, 23 / MyGdxGame.PPM / 2);
					break;
				case LEFT:
					polygonShape.setAsBox(24 / MyGdxGame.PPM / 2, 19 / MyGdxGame.PPM / 2);
					break;
				case RIGHT:
					polygonShape.setAsBox(25 / MyGdxGame.PPM / 2, 20 / MyGdxGame.PPM / 2);
					break;
				default:
					polygonShape.setAsBox(14 / MyGdxGame.PPM / 2, 25 / MyGdxGame.PPM / 2);
					break;	
			}
			
			return polygonShape;
			
		}
		
		

		// New method from Example Game
		public TextureRegion getFrame(float dt) {
			
			currentState = getState();

			TextureRegion region;

			switch(currentState) {
			case UP:
				region = frames.get(0);
				break;
			case DOWN:
				region = frames.get(1);
				break;
			case LEFT:
				region = frames.get(2);
				break;
			case RIGHT:
				region = frames.get(3);
				break;
			default:
				region = playerUp;
				break;
			}

			previousState = currentState;

			return region;
			
		}

		// New method from Example Game
		public State getState() {
			
			if(b2body.getLinearVelocity().y > 0) {
				
				return State.UP;
				
			}else if(b2body.getLinearVelocity().y < 0) {
				
				return State.DOWN;
				
			}else if(b2body.getLinearVelocity().x < 0) {
				
				return State.LEFT;
				
			}else {
				
				return State.RIGHT;
				
			}
			
		}

		// New method from Example Game
		public void definePlayer() {
			
			BodyDef bdef = new BodyDef();
			bdef.position.set(32 / MyGdxGame.PPM, 32 / MyGdxGame.PPM);
			bdef.type = BodyDef.BodyType.DynamicBody;
			b2body = world.createBody(bdef);
			
			fdef = new FixtureDef(); // This line has been changed
			shape = new PolygonShape(); // This line has been changed
			shape.setAsBox(14 / MyGdxGame.PPM / 2, 25 / MyGdxGame.PPM / 2); // This line has been changed


			fdef.shape = shape;
			b2body.createFixture(fdef);
			
		}


	public float getxCoordinate() {

		return getPosX();
	}

	public float getyCoordinate() {

		return getPosY();
	}

//	public void setXCoordinate(float newX) {
//		move(newX, getPosY());
//	}
//
//	public void setYCoordinate(float newY) {
//		move(getPosX(), newY);
//	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double newHealth, Entity enemy) {

		if (!justhit) {
			health = newHealth;
			justhit = true;
			emimymove.start();
			//move(getPosX() - 5, getPosY() - 5);
		}

	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

}