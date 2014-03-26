package me.jackbracken.picnic.states;

import java.util.ArrayList;

import me.jackbracken.picnic.entity.BeeEntity;
import me.jackbracken.picnic.entity.Entity;
import me.jackbracken.picnic.entity.FlyEntity;
import me.jackbracken.picnic.entity.PlayerEntity;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	private int id, xPos, yPos, height;
	private Image bg, swatter;
	private Animation flyAnimation, beeAnimation, playerAnimation;
	private Entity entity;
	private FlyEntity fly;
	private BeeEntity bee;
	private PlayerEntity player;
	
	private ArrayList<Entity> mobs;
	
	public Game(State state) {
		id = state.getId();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		mobs = new ArrayList<Entity>();
		bg = new Image("res/bg.png");
		height = gc.getHeight();
		
		// Initialize sprites
		
		Image[] flySprite = { 
			new Image("res/sprites/fly_1.png"),
			new Image("res/sprites/fly_2.png") 
		};
		
		Image[] beeSprite = {
			new Image("res/sprites/bee_1.png"),
			new Image("res/sprites/bee_2.png")
		};
		
		Image[] playerSprite = {
			new Image("res/sprites/swatter.png")
		};
		
		// Initialize animations
		
		flyAnimation = new Animation(flySprite, 100, false);
		beeAnimation = new Animation(beeSprite, 100, false);
		playerAnimation = new Animation(playerSprite, 100, false);
		
		fly = new FlyEntity(this, flyAnimation, 300, 300);
		
		// Initialize mouse 
		
		xPos = Mouse.getX();
		yPos = height - Mouse.getY();
		
		player = new PlayerEntity(this, playerAnimation, xPos, yPos, height);
		

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(bg, 0, 0);
		player.render();
		
		fly.render();
//		flyAnimation.draw(500, 300);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		if(fly.isCollidingWith(player)) {
			fly.kill();
		}
		player.update(delta);
		fly.update(6);
	}

	@Override
	public int getID() {
		return id;
	}
	
	public void spawnFly(FlyEntity fly) {
//		fly = new FlyEntity(this, flyAnimation);
		spawnEntity(fly);
	}
	
	public void spawnBee(BeeEntity bee) {
		bee = new BeeEntity(this, beeAnimation);
		spawnEntity(bee);
	}
	
	public void spawnEntity(Entity e) {
		mobs.add(e);
	}
	
	public void destroyEntity(Entity e) {
		mobs.remove(e);
	}
	
}
