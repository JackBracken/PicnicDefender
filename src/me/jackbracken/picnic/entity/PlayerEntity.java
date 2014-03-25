package me.jackbracken.picnic.entity;


import java.util.Random;

import me.jackbracken.picnic.states.Game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;

public class PlayerEntity extends Entity {

	private Game game;
	Random r = new Random();
	
	public PlayerEntity(Game game, Animation animation) {
		super(animation, 0, 0);
		this.game = game;
	}
	
	public void update(long delta) {
		animation.update(delta);
	}
	
	public void render(int x, int y) {
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
