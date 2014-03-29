package me.jackbracken.picnic.entity;

import java.util.Random;

import org.newdawn.slick.Animation;

public class FlyEntity extends Entity {
	
//	private Game game;
	private int x, y;
	Random r = new Random();
	boolean alive = true;
	
	public FlyEntity(Animation animation, int x, int y) {
		super(animation, x, y);
		this.x = x;
		this.y = y;
		
		setHorizontalVelocity(-500);
	}
	
	public void update(long delta) {
		move(delta / 2);
		setVerticalVelocity(getVerticalVelocity() + r.nextInt(50) - 25);
		
		x = getX();
		y = getY(); 
		
		animation.update(delta);
	}
	
	
	public void render() {
		if(alive) {
			animation.draw(x, y);
		}
	}

}
