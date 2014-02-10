package me.jackbracken.picnic.entity;

import me.jackbracken.picnic.states.Game;

import org.newdawn.slick.Animation;

public class FlyEntity extends Entity {
	
	private Game game;
	
	protected FlyEntity(Game game, Animation animation, int x, int y) {
		super(animation, x, y);
		this.game = game;
	}

	public void collidedWith(Entity other) {
		if(other instanceof PlayerEntity) {
			game.destroyEntity(this);
		}
	}

}
