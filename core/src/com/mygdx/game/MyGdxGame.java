package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.screens.MainGameScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.badlogic.gdx.math.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Timer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class MyGdxGame extends Game {
	public static int width = 1366;
	public static int height = 768;
	public MainGameScreen main;
	public SpriteBatch batch;
	public int score;
	
	public static final int V_Width = 400;
	public static final int V_Height = 208;
	public static final float PPM = 100;
	
	public void create () {
		
		batch = new SpriteBatch();
		OrthographicCamera cam = new OrthographicCamera();
		cam.position.set(MyGdxGame.width, MyGdxGame.height, 0);
		batch.setProjectionMatrix(cam.combined);

		this.setScreen(new MainMenuScreen(this, score,cam));
	}

	public void render () {
		super.render();
	
		
	}
	
	
	@Override
	public void dispose () {
		
		batch.dispose();
		
	}
	
	

}
