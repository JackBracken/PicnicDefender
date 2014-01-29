package me.jackbracken.picnic;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PicnicDefender extends BasicGame {
	
	// Window constants;
	private static int WIDTH = 800;
	private static int HEIGHT = 600;
	
	// Mouse coordinates
	private float x, y;
	
	// Interval for swatting
	private int swatInterval = 1000;
	
	// Declare assets
	private Image bgImage;
	private Animation playerHand;
	
	public PicnicDefender() {
		super("Picnic Defender");
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
		
		// Initialise player's hand
		Image[] playerSprite = { new Image("res/fly_normal.png"), new Image("res/fly_fly.png") };
		int[] duration = {300, 300};
		playerHand = new Animation(playerSprite, duration, false);
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// Animate player sprite
		playerHand.update(20);
		
		if(Mouse.isButtonDown(0)) {
			playerHand.update(100);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			System.out.println("ESC PRESSED: QUITTING NOW");
			System.exit(0);
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(bgImage, 0, 0, null);
		playerHand.draw(Mouse.getX() - (playerHand.getWidth() / 2), HEIGHT - Mouse.getY() - (playerHand.getHeight() / 2));
	}
}
