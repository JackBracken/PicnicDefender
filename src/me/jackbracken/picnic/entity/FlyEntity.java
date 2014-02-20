package me.jackbracken.picnic.entity;

import java.util.Random;

import me.jackbracken.picnic.states.Game;

import org.newdawn.slick.Animation;

public class FlyEntity extends Entity {
	
	private Game game;
	Random r = new Random();
	
	public FlyEntity(Game game, Animation animation) {
		super(animation, 0, 0);
		this.game = game;
	}

	public void collidedWith(Entity other) {
		if(other instanceof PlayerEntity) {
			game.destroyEntity(this);
		}
	}

}
