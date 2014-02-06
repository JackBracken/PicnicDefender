package me.jackbracken.picnic;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import states.*;

public class Picnic extends StateBasedGame {
	// Game title
	public static final String TITLE = "Picnic Defender";
    
	public static State currentState = State.SplashScreen;
    
    // Game properties
    public static final int WIDTH   = 800;
    public static final int HEIGHT  = 600;
    public static final int TARGET_FPS = 60;
	
    
    public Picnic() {
    	super(TITLE);
    }
	public void initStatesList(GameContainer container) throws SlickException {
    	addState(new SplashScreen(State.SplashScreen));
    	addState(new MainMenu(State.MainMenu));
    	addState(new Game(State.Game));
    	addState(new PauseMenu(State.MainMenu));
    	addState(new EndGame(State.EndGame));
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new Picnic());
			container.setDisplayMode(WIDTH, HEIGHT, false);
			container.setTargetFrameRate(TARGET_FPS);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}