package me.jackbracken.picnic.entity;


import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;

public class PlayerEntity extends Entity {

	private int x, y, gameHeight;
	Random r = new Random();
	
	public PlayerEntity(Animation animation, int x, int y, int gameHeight) {
		super(animation, x, y);
		this.x = x;
		this.y = y;
		this.gameHeight = gameHeight;
	}
	
	public void update(long delta) {
		animation.update(delta);
		
		x = Mouse.getX();
		y = gameHeight - Mouse.getY();
	
		setX(x);
		setY(y);
	}
	
	public void render() {
		animation.draw(x, y);
	}

	
//	protected PlayerEntity(Animation animation, int x, int y) {
//		super(animation, x, y);
//	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub
		
	}

}
