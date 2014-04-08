package me.jackbracken.picnic.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import java.awt.Font;

public class HUD {

	private TrueTypeFont scoreFont;
	private int width, score;
	
	public HUD(GameContainer gc) {
		scoreFont = new TrueTypeFont(new Font("Verdana", Font.BOLD, 38), true);
		width = gc.getWidth();
		score = 0;
	}
	
	public void render(GameContainer gc, Graphics g) {
		scoreFont.drawString(width - 80, 30, "" + score, Color.darkGray);
	}
	
	public void update(GameContainer gc, long delta, int score) {
		this.score = score;
	}
}
