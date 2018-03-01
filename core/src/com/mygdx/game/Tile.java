package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Tile {

	float xcoordinate = 0;
	float ycoordinate = 0;
	float width = 0;
	float height = 0;
	
	public Tile(float xcoordinate,float ycoordinate,float width,float height) {
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.width = width;
		this.height = height;
	}
	
	public void renderTile() {
		ShapeRenderer shaperenderer = new ShapeRenderer();
		shaperenderer.begin(ShapeType.Filled);
		shaperenderer.setColor(Color.BROWN);
		shaperenderer.rect(xcoordinate, ycoordinate, width, height);
		shaperenderer.end();
	}
	
	public float getXCoordinate() {
		return xcoordinate;
	}
	
	public float getYCoordinate() {
		return ycoordinate;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
}