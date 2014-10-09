package me.jackbracken.picnic.entity;

import java.util.Random;

import org.newdawn.slick.Animation;

public class FlyEntity extends Entity {
	
	private static int SCORE = 1;
	
	private int x, y;
	boolean alive = true;
	
	Random r = new Random();
	
	public FlyEntity(Animation animation, int x, int y) {
		super(animation, x, y);
		this.x = x;
		this.y = y;
		setHorizontalVelocity(-500);
	}
	
	public void update(long delta) {
		move(delta / 2);
		
		// sine-wave like motion for flies
		setVerticalVelocity(getVerticalVelocity() + (float) Math.cos(getX() / 60) * 15);

		x = getX();
		y = getY();
		
		animation.update(delta);
	}
	
	
	public void render() {
		if(alive) {
			animation.draw(x, y);
		}
	}
	
	public int getScore() {
		return SCORE;
	}

}
