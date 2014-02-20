package me.jackbracken.picnic.entity;

import me.jackbracken.picnic.states.Game;

import org.newdawn.slick.Animation;

public class WaspEntity extends Entity {

	private Game game;
	
	public WaspEntity(Game game, Animation animation) {
		super(animation, 0, 0);
		this.game = game;
	}

	@Override
	public void collidedWith(Entity other) {
		if(other instanceof PlayerEntity) {
			game.destroyEntity(this);
		}
	}

}
