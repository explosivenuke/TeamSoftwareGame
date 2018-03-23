package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mouse extends TimerEnemy{
	int direction;
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			direction = (int) Math.floor(Math.random() * 4) + 1;
		}
	};
	Timer emimymove = new Timer(1000, taskPerformer);
	public Mouse(float spawnX, float spawnY, float width, float height, float moveSpeed, double maxHealth, double damage, boolean inv,
			Texture img, SpriteBatch map) {
		super(spawnX, spawnY, width, height, moveSpeed, maxHealth, damage, inv, img, map);
		direction = 0;
		emimymove.setInitialDelay(10);
		emimymove.start();
	}
	public void Draw() {
		if(direction == 1) move(getPosX(), getPosY() + 1);
		if(direction == 2) move(getPosX(), getPosY() - 1);
		if(direction == 3) move(getPosX() + 1, getPosY());
		if(direction == 4) move(getPosX() - 1, getPosY());
		super.Draw();
	}
}