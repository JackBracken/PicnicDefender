package me.jackbracken.picnic;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class PicnicDefender extends BasicGame {
	
	// Window constants;
	private static int WIDTH = 800;
	private static int HEIGHT = 600;
	
	// Mouse coordinates
	private float x, y;
	
	// Swatter rotation
	private float rotation = 0.0f;
	private boolean clockwise = true;
	
	// Interval for swatting
	private int swatInterval = 1000;
	
	// Declare assets
	private Image bgImage;
	private Animation swatter, fly, wasp, ant;
	
	// Set entity properties
	private int numberOfFlies = 4;
	
	// Declare Entities
	private PlayerEntity pe;
	private FlyEntity[] fe = new FlyEntity[numberOfFlies];
	
	public PicnicDefender() {
		super("Picnic Defender");
	}
	
	public void destroyEntity(Entity entity) {
		
	}
	
	public static void main(String[] args) {
		try{
			AppGameContainer app = new AppGameContainer(new PicnicDefender());
			app.setDisplayMode(WIDTH, HEIGHT, false);
			app.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}
		
	}

	// Initialise assets
	public void init(GameContainer arg0) throws SlickException {
		// Initialise background image
		bgImage = new Image("res/bg.jpg");
		
		// Grab Mouse to hide cursor and steal focus
		Mouse.setGrabbed(true);

		// Initialize Image arrays for sprites
		Image[] swatterSprite = { 
				new Image("res/swatter.png") 
		};
		
		Image[] flySprite = {
				new Image("res/fly_normal.png"), 
				new Image("res/fly_fly.png") 
//				new Image("res/fly_dead.png")
		};
		
		// Initialise animations
		swatter = new Animation(swatterSprite, 1, false);
		fly = new Animation(flySprite, 100, false);
		
		// Initialize entities
		fe[0] = new FlyEntity(this, fly, WIDTH + fly.getWidth(), 100);
		fe[1] =	new FlyEntity(this, fly, WIDTH, 300);
		fe[2] =	new FlyEntity(this, fly, WIDTH - fly.getWidth(), 200);
		fe[3] =	new FlyEntity(this, fly, WIDTH + fly.getWidth() * 2, 350);
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		if(Mouse.isButtonDown(0)) {
			swingSwatter(delta);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			System.out.println("ESC PRESSED: QUITTING NOW");
			System.exit(0);
		}
		
		fly.update(10);
		
		for(int i = 0; i < fe.length; i++) {
//			fe[i].setHorizontalVelocity(5);
			fe[i].move(1);
			fe[i].draw();
		}
	}
	
	private void swingSwatter(int delta) {
		if(rotation <= 90.0f && clockwise) {
			rotation += .6f * delta;
		}
		if(rotation >= 89.9f && clockwise) {
			clockwise = false;
		}
		
		if(rotation >= 0.0f && !clockwise) {
			rotation -= .1f * delta;
		}
		if(rotation <= 0.1f && !clockwise) {
			clockwise = true;
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		Input input = container.getInput();
		
		x = input.getMouseX();
		y = input.getMouseY();
		
		g.drawImage(bgImage, 0, 0, null);
		
		// rotation of swatter
		g.pushTransform();
		g.rotate(x + swatter.getWidth(), y + swatter.getHeight(), rotation);
		g.drawAnimation(swatter, x, y);
		g.popTransform();
		
		// draw fly
		for(FlyEntity f: fe) {
			f.setHorizontalVelocity(-120);
			f.move(10);
			f.draw();
		}
	}
}
