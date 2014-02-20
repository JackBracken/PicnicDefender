package me.jackbracken.picnic.states;

import java.util.ArrayList;

import me.jackbracken.picnic.entity.Entity;
import me.jackbracken.picnic.entity.FlyEntity;
import me.jackbracken.picnic.entity.WaspEntity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	private int id;
	private Image bg;
	private Animation flyAnimation, waspAnimation;
	private Entity entity;
	private FlyEntity fly;
	private WaspEntity wasp;
	
	private ArrayList<Entity> mobs;
	
	public Game(State state) {
		id = state.getId();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		mobs = new ArrayList<Entity>();
		bg = new Image("res/bg.png");
		
		// Initialize sprites
		
		Image[] flySprite = { 
			new Image("res/sprites/fly_normal.png"),
			new Image("res/sprites/fly_fly.png") 
		};
		
		Image[] waspSprite = {
			new Image("res/sprites/wasp_normal.png"),
			new Image("res/sprites/wasp_fly.png")
		};
		
		// Initialize animations
		
		flyAnimation = new Animation(flySprite, 100, false);
		waspAnimation = new Animation(waspSprite, 100, false);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(bg, 0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		return id;
	}
	
	public void spawnFly(FlyEntity fly) {
		this.fly = fly;
		fly = new FlyEntity(this, flyAnimation);
		spawnEntity(fly);
	}
	
	public void spawnWasp(WaspEntity wasp) {
		this.wasp = wasp;
		wasp = new WaspEntity(this, waspAnimation);
		spawnEntity(wasp);
	}
	
	public void spawnEntity(Entity entity) {
		this.entity = entity;
		mobs.add(entity);
	}
	
	public void destroyEntity(Entity entity) {
		this.entity = entity;
		mobs.remove(entity);
	}
	
}
