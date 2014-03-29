package me.jackbracken.picnic.states;

import java.util.Vector;

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
	private int id, xPos, yPos, height, width;
	private Image bg;
	private Animation flyAnimation, beeAnimation, playerAnimation;
	private PlayerEntity player;
	private Vector<Entity> mobs;
	
	public Game(State state) {
		id = state.getId();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		// Capture mouse
		Mouse.setGrabbed(true);
		
		mobs = new Vector<Entity>();
		bg = new Image("res/bg.png");
		height = gc.getHeight();
		width = gc.getWidth();
		
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
		
		// Initialize mouse 
		
		xPos = Mouse.getX();
		yPos = height - Mouse.getY();
		
		player = new PlayerEntity(playerAnimation, xPos, yPos, height);
		

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(bg, 0, 0);
		player.render();
		for(Entity mob: mobs) {
			mob.render();
		}
//		fly.render();
//		flyAnimation.draw(500, 300);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		player.update(delta);
		
		if(mobs.isEmpty()) {
			mobs.add(new FlyEntity(flyAnimation, width, 500));
		}
		
		for(Entity mob: mobs) {
			mob.update(delta);
			if(mob.isCollidingWith(player) || mob.getX() < 0) {
//				mob.kill();
				mobs.remove(mob);
				break;
			}
		}
	}

	@Override
	public int getID() {
		return id;
	}
	// are these spawn methods needed? Maybe cleaup update code usign them
	public void spawnFly(FlyEntity fly) {
		spawnEntity(fly);
	}
	
	public void spawnBee(BeeEntity bee) {
		spawnEntity(bee);
	}
	
	public void spawnEntity(Entity e) {
		mobs.add(e);
	}
	
}
