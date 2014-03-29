package me.jackbracken.picnic.entity;

import java.awt.Rectangle;

import org.newdawn.slick.Animation;

public abstract class Entity {
	// X and Y coordinates and velocities of the entity
	protected float x, y, xVel, yVel;
	protected Animation animation;
	protected Rectangle thisEntity = new Rectangle();
	protected Rectangle thatEntity = new Rectangle();

	protected Entity(Animation animation, int x, int y) {
		this.animation = animation;
		this.x = x;
		this.y = y;
	}
	
	public void move(long delta) {
		x += (delta * xVel) / 1000;
		y += (delta * yVel) / 1000;
	}
	
	public void render() {
		
	}
	
	public void kill() {
		
	}
	
	public void update(long delta) {
		
	}
	
	// Set and get coordinates and velocities
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public void setX(int i) {
		x = i;
	}
	
	public void setY(int i) {
		y = i;
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
		animation.draw((int) x, (int) y);
	}
	
	public boolean isCollidingWith(Entity other) {
		thisEntity.setBounds((int) x, (int) y, animation.getWidth(), animation.getHeight());
		thatEntity.setBounds((int) other.getX(), (int) other.getY(), other.animation.getWidth(), other.animation.getHeight());
		return thisEntity.intersects(thatEntity);
	}
	
	public abstract void collidedWith(Entity other);
}
