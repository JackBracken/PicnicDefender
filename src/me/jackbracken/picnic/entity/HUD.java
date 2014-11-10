package me.jackbracken.picnic.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import java.awt.Font;

public class HUD {

	private TrueTypeFont scoreFont;
	private int width, score;
	private Image basket;
	
	public HUD(GameContainer gc) throws SlickException {
		scoreFont = new TrueTypeFont(new Font("Verdana", Font.BOLD, 38), true);
		width = gc.getWidth();
		score = 0;
		basket = new Image("res/sprites/basket.png");
	}
	
	public void render(GameContainer gc, Graphics g) {
		basket.draw(40, 30);
		basket.draw(90, 30);
		basket.draw(140, 30);
		scoreFont.drawString(width - 80, 30, "" + score, Color.darkGray);
	}
	
	public void update(GameContainer gc, long delta, int score) {
		this.score = score;
	}
}
