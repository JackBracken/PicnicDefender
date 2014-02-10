package me.jackbracken.picnic.entity;

import me.jackbracken.picnic.states.Game;

import org.newdawn.slick.Animation;

public class WaspEntity extends Entity {

	private Game game;
	
	protected WaspEntity(Game game, Animation animation, int x, int y) {
		super(animation, x, y);
		this.game = game;
	}

	@Override
	public void collidedWith(Entity other) {
		if(other instanceof PlayerEntity) {
			game.destroyEntity(this);
		}
	}

}
