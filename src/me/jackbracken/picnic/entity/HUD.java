package me.jackbracken.picnic.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import java.awt.Font;

public class HUD {

	private TrueTypeFont scoreFont, gameOverFont;
	private int height, width, score, hp;
	private Image basket;
	private boolean gameOver;

	public HUD(GameContainer gc) throws SlickException {
		scoreFont = new TrueTypeFont(new Font("Verdana", Font.BOLD, 38), true);
		gameOverFont = new TrueTypeFont(new Font("Verdana", Font.BOLD, 48),
				true);
		height = gc.getHeight() / 2;
		width = gc.getWidth();
		score = 0;
		hp = 3;
		gameOver = false;
		basket = new Image("res/sprites/basket.png");
	}

	public void render(GameContainer gc, Graphics g) {
		for (int i = 0; i < hp; i++) {
			basket.draw((i * basket.getWidth() + 30), 30);
		}

		scoreFont.drawString(width - 80, 30, "" + score, Color.darkGray);
		
		if(gameOver) {
			gameOverFont.drawString(centerText(gc, gameOverFont, "Game over!"), height, "Game over!", Color.darkGray);
		}
	}
	
	private int centerText(GameContainer gc, TrueTypeFont tff, String text) {
		return gc.getWidth() / 2 - tff.getWidth(text) / 2;
	}

	public void update(GameContainer gc, long delta, int score) {
		this.score = score;

		if (hp <= 0) {
			gameOver = true;
		}
	}

	public void hurt() {
		hp--;
	}

	public void heal() {
		hp++;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void gameOver(GameContainer gc) {
		
	}
}
