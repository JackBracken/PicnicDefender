package me.jackbracken.picnic.entity;


import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;

public class PlayerEntity extends Entity {

	private int x, y, gameHeight;
	Random r = new Random();
	
	private boolean isSwatting = false;
	
	public PlayerEntity(Animation animation, int x, int y, int gameHeight) {
		super(animation, x, y);
		this.x = x;
		this.y = y;
		this.gameHeight = gameHeight;
	}
	
	public void update(long delta) {
		if(isSwatting) {
			animation.update(delta);
			
			if(animation.getFrameCount() - 1 == animation.getFrame()) {
				stopSwat();
			}
		}

		y = gameHeight - Mouse.getY();
	
		if(y > gameHeight - animation.getHeight()) {
			y = gameHeight - animation.getHeight();
			Mouse.setCursorPosition(x, animation.getHeight());
		}
	
//		setX(x);
		setY(y);
	}
	
	public void swat() {
		isSwatting = true;
	}
	
	public void stopSwat() {
		isSwatting = false;
	}
	
	public void render() {
		animation.draw(x, y);
	}
	
	public boolean isCollidable() {
		return animation.getFrameCount() / 2  == animation.getFrame();
	}

}
