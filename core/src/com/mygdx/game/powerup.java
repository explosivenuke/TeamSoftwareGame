package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.MainGameScreen;

public class powerup extends Entity{
	public char poweruptype;
	public powerup(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth,
			double damage, boolean inv, Texture img, SpriteBatch map, boolean freindly,
			 int pointval, char powerup, World world, MainGameScreen screen) {
		super(spawnX, spawnY, 25, 25, moveSpeed, maxHealth, damage, inv, img, map, freindly, pointval, world, screen);
		// TODO Auto-generated constructor stub
		this.poweruptype = powerup;
	}

}
