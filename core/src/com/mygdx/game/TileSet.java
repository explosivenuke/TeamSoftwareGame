package com.mygdx.game;

import java.util.ArrayList;

public class TileSet {

	ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public TileSet(int numTiles) {
		for(int i = 0; i < numTiles; i++) {
			Tile newtile = new Tile(TileSet.generateXCoordinate(),TileSet.generateYCoordinate(),1,1);
			tiles.add(newtile);
		}
	}
	
	public static float generateXCoordinate() {
		float xcoordinate = 0;
		return xcoordinate;
	}
	
	public static float generateYCoordinate() {
		float ycoordinate = 0;
		return ycoordinate;
	}
	
}