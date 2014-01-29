package me.jackbracken.picnic;

import java.awt.Rectangle;

public abstract class Entity {
	// X and Y coordinates and velocities of the entity
	protected float x, y, xVel, yVel;
	protected Sprite sprite;
	protected Rectangle thisEntity = new Rectangle();
	protected Rectangle thatEntity = new Rectangle();

	protected Entity(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	public void move(long delta) {
		x += (delta * xVel) / 1000;
		y += (delta * yVel) / 1000;
	}
	
	// Set and get coordinates and velocities
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public float getHorizontalVelocity() {
		return xVel;
	}
	
	public float getVerticalVelocity() {
		return yVel;
	}
	
	public void setHorizontalVelocity(float newVelocity) {
		xVel = newVelocity;
	}
	
	public void setVerticalVelocity(float newVelocity) {
		yVel = newVelocity;
	}
	
	// Draw the sprite
	public void draw() {
		sprite.draw((int) x, (int) y);
	}
	
	public boolean isCollidingWith(Entity other) {
		thisEntity.setBounds((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
		thatEntity.setBounds((int) other.getX(), (int) other.getY(), other.sprite.getWidth(), other.sprite.getHeight());
		return thisEntity.intersects(thatEntity);
	}
	
	public abstract void collidedWith(Entity other);
}
