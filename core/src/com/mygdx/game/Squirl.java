package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Squirl extends ShooterEnemy{
	int direction;
	boolean fireable;
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			direction = (int) Math.floor(Math.random() * 5) + 1;
			fireable = true;
		}
	};
	Timer emimymove = new Timer(250, taskPerformer);
	public Squirl(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, boolean inv,
			Texture img, SpriteBatch map, TiledMapTileLayer collisionLayer, int pointval, OrthographicCamera camera) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, map, collisionLayer, pointval, camera);
		direction = 0;
		emimymove.setInitialDelay(10);
		emimymove.start();
		fireable =true;
	}
	public void Draw() {
		if(direction == 1) move(getPosX(), getPosY() + 1);
		if(direction == 2) move(getPosX(), getPosY() - 1);
		if(direction == 3) move(getPosX() + 1, getPosY());
		if(direction == 4) move(getPosX() - 1, getPosY());
		super.Draw();
		
		
	}
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