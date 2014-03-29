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
//		System.out.println(r.nextInt(100));
		
		x = getX();
		y = getY(); 
		
		animation.update(delta);
	}
	
	public void kill() {
		alive = false;
	}
	
	public void render() {
		if(alive) {
			animation.draw(x, y);
		}
	}

	public void collidedWith(Entity other) {
		// move to superclass
		if(other instanceof PlayerEntity) {
			alive = false;
		}
	}

}
