package me.jackbracken.picnic.states;

import java.util.Random;
import java.util.Vector;

import me.jackbracken.picnic.entity.BeeEntity;
import me.jackbracken.picnic.entity.Entity;
import me.jackbracken.picnic.entity.FlyEntity;
import me.jackbracken.picnic.entity.HUD;
import me.jackbracken.picnic.entity.PlayerEntity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	private int id, xPos, yPos, height, width, score, spawnHeight,
			randSpawnType, tmpY;
	private Image bg;
	private Animation flyAnimation, beeAnimation, playerAnimation;
	private PlayerEntity player;
	private Vector<Entity> mobs;
	private Random rand;
	private HUD hud;
	private boolean paused;

	public Game(State state) {
		id = state.getId();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		// Capture mouse
		Mouse.setGrabbed(true);

		mobs = new Vector<Entity>();
		bg = new Image("res/bggame.png");
		height = gc.getHeight();
		width = gc.getWidth();
		score = 0;
		rand = new Random();
		paused = false;

		// Initialize HUD
		hud = new HUD(gc);

		// Initialize sprites
		Image[] flySprite = { new Image("res/sprites/fly_1.png"),
				new Image("res/sprites/fly_2.png") };

		Image[] beeSprite = { new Image("res/sprites/bee_1.png"),
				new Image("res/sprites/bee_2.png") };

		Image[] playerSprite = { new Image("res/sprites/pc1.png"),
				new Image("res/sprites/pc2.png"),
				new Image("res/sprites/pc3.png"),
				new Image("res/sprites/pc4.png"),
				new Image("res/sprites/pc5.png"),
				new Image("res/sprites/pc6.png"),
				new Image("res/sprites/pc7.png"),
				new Image("res/sprites/pc6.png"),
				new Image("res/sprites/pc5.png"),
				new Image("res/sprites/pc4.png"),
				new Image("res/sprites/pc3.png"),
				new Image("res/sprites/pc2.png") };

		// Initialize animations
		flyAnimation = new Animation(flySprite, 100, false);
		beeAnimation = new Animation(beeSprite, 100, false);
		playerAnimation = new Animation(playerSprite, 50, false);

		// Initialize mouse
		xPos = 240;
		yPos = height - Mouse.getY();

		player = new PlayerEntity(playerAnimation, xPos, yPos, height);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// Render BG image
		g.drawImage(bg, 0, 0);

		// Render HUD
		hud.render(gc, g);

		// Render player entity
		player.render();

		// Render mobs
		for (Entity mob : mobs) {
			mob.render();
		}

		if (paused) {
			Color trans = new Color(0f, 0f, 0f, 0.5f);
			g.setColor(trans);
			g.fillRect(0, 0, width, height);
		}
	}
	
	public void pause() {
		if(paused) {
			paused = false;
		} else {
			paused = true;
		}
	}

	public void keyReleased(int key, char c) {
		if(key == Input.KEY_ESCAPE) {
			if(!paused) {
				tmpY = yPos;
			} else {
				yPos = tmpY;
			}
			
			pause();
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if(paused) {
			return;
		}
		player.update(delta);

		if (mobs.isEmpty()) {
			mobs.add(spawnMob());
		}

		for (Entity mob : mobs) {

			if (hud.isGameOver()) {
				hud.gameOver(gc);
				break;
			}

			mob.update(delta);

			if (mob.isCollidingWith(player)) {
				score += mob.getScore();
			}

			if (mob.getX() <= 0) {
				hud.hurt();
			}

			if (mob.isCollidingWith(player) || mob.getX() < 0) {
				mobs.remove(mob);
				break;
			}

		}

		if (Mouse.isButtonDown(0)) {
			player.swat();
		}

		hud.update(gc, delta, score);
	}

	public int getID() {
		return id;
	}

	// Spawn a random mob
	public Entity spawnMob() {
		randSpawnType = rand.nextInt(20);

		if (randSpawnType < 18) {
			return spawnFly();
		} else {
			return spawnBee();
		}
	}

	public Entity spawnFly() {
		spawnHeight = rand.nextInt(height - (flyAnimation.getHeight() * 2));
		return new FlyEntity(flyAnimation, width, spawnHeight);
	}

	public Entity spawnBee() {
		spawnHeight = rand.nextInt(height - (beeAnimation.getHeight() * 2));
		return new BeeEntity(beeAnimation, width, spawnHeight);
	}

	public int getScore() {
		return score;
	}
}
