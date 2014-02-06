package me.jackbracken.picnic;

import org.newdawn.slick.Animation;

public class FlyEntity extends Entity {
	
	private PicnicDefender game;
	
	protected FlyEntity(PicnicDefender game, Animation animation, int x, int y) {
		super(animation, x, y);
		this.game = game;
	}

	public void collidedWith(Entity other) {
		if(other instanceof PlayerEntity) {
			game.destroyEntity(this);
		}
	}

}
