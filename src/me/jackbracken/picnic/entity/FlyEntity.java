package me.jackbracken.picnic.entity;

import java.util.Random;

import me.jackbracken.picnic.states.Game;

import org.newdawn.slick.Animation;

public class FlyEntity extends Entity {
	
	private Game game;
	private int x, y;
	Random r = new Random();
	boolean alive = true;
	
	public FlyEntity(Game game, Animation animation, int x, int y) {
		super(animation, x, y);
		this.game = game;
		this.x = x;
		this.y = y;
		
		setHorizontalVelocity(-500);
	}
	
	public void update(long delta) {
		move(delta);
		setVerticalVelocity(r.nextInt(2000) - 1000);
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
		if(other instanceof PlayerEntity) {
			game.destroyEntity(this);
			alive = false;
		}
	}

}
