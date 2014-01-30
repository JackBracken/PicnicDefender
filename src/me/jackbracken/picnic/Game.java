package me.jackbracken.picnic;

import java.awt.SplashScreen;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
	// Game title
	public static final String TITLE = "Picnic Defender";
	
	// Game states
    public static final int SPLASHSCREEN = 0;
    public static final int MAINMENU     = 1;
    public static final int GAME         = 2;
    
    // Game properties
    public static final int WIDTH   = 640;
    public static final int HEIGHT  = 480;
    public static final int TARGET_FPS = 60;
	
    public Game() {
    	super(TITLE);
    }
    
	public void initStatesList(GameContainer container) throws SlickException {
//		this.addState(new SplashScreen(SPLASHSCREEN));
//        this.addState(new MainMenu(MAINMENU));
//        this.addState(new Game(GAME));
	}//		this.addState(new SplashScreen(SPLASHSCREEN));
//  this.addState(new MainMenu(MAINMENU));
//  this.addState(new Game(GAME));
	
//	public static void main(String[] args) {
//		try {
//			AppGameContainer app = new AppGameContainer(new Game());
//			app.setDisplayMode(WIDTH, HEIGHT, false);
//			app.setTargetFrameRate(TARGET_FPS);
//			app.setShowFPS(false);
//			app.start();
//		} catch (SlickException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
