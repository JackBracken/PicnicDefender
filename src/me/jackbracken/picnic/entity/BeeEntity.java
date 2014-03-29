package me.jackbracken.picnic.entity;

import org.newdawn.slick.Animation;

public class BeeEntity extends Entity {

	
	public BeeEntity(Animation animation) {
		super(animation, 0, 0);
	}

	@Override
	public void collidedWith(Entity other) {
		if(other instanceof PlayerEntity) {
			// see flyentity
		}
	}

}
