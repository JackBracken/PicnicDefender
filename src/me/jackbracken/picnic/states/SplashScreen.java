package me.jackbracken.picnic.states;

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashScreen extends BasicGameState {
	// The game
	private StateBasedGame sbg;
	
	// The game container
	private GameContainer gc; 
	
	// Game title 
	private String title = "Picnic Defender";
	
	// Assets
	private Image backgroundImage;
	private Animation bee;
	private TrueTypeFont menuTitle, menuItems;
	private int backgroundWidth, hOffset;
	
	// Menu
	private int id, currentOption = 0;
	private boolean menuVisible = false;
	private String[] menuLabels = { "Play Game", "High Scores", "Quit Game" };
	
	// Counter
	private int menuReadyTime = 3000;
	private long pastTime = 0;

	public SplashScreen(State state) {
		id = state.getId();
	}

	public boolean menuReady(long delta) {
		if (pastTime < menuReadyTime) {
			pastTime += delta;
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		// Our game and container
		this.sbg = sbg;
		this.gc = gc;
		
		// Set menu ready time in millis
		menuReadyTime = 3000;

		// Initialize background image (need 2 for horizontal repeat)
		backgroundImage = new Image("res/bgmenu.png");

		backgroundWidth = backgroundImage.getWidth();
		hOffset = -backgroundWidth + gc.getWidth();

		Image[] beeSprite = { 
				new Image("res/sprites/bee_1.png"),
				new Image("res/sprites/bee_2.png") 
		};
		
		

		bee = new Animation(beeSprite, 100, false);
		
		menuTitle = new TrueTypeFont(new Font("Verdana", Font.BOLD, 42), true);
		menuItems = new TrueTypeFont(new Font("Verdana", Font.BOLD, 26), true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		// Scroll speed (4 pixels per frame [60 fps], 240 pixels per second?)
		hOffset += 4;
		int menuItemOffset = 300;

		if (hOffset > gc.getWidth()) {
			hOffset = -backgroundWidth + gc.getWidth();
		}

		backgroundImage.draw(hOffset, 0);
		backgroundImage.draw(hOffset - backgroundWidth, 0);

		menuTitle.drawString(centerText(gc, menuTitle, title), 200, title,
				Color.darkGray);

		if (menuVisible) {
			
			for (int i = 0; i < menuLabels.length; i++) {
				menuItems.drawString(centerText(gc, menuItems, menuLabels[i]), 
						menuItemOffset + 40 * i, menuLabels[i], Color.darkGray);
			}

			
			
			bee.draw(	
				centerText(gc, menuItems, menuLabels[currentOption])
					- bee.getWidth() - 20, (menuItemOffset - 20) + (42 * currentOption) 
			);

		}
	}

	private int centerText(GameContainer gc, TrueTypeFont tff, String text) {
		return gc.getWidth() / 2 - tff.getWidth(text) / 2;
	}

	public void keyReleased(int key, char c) {

		// detect up and down key releases for menu
		if(menuVisible){
			switch(key) {
			case Input.KEY_DOWN:
				if(currentOption < 2) {
					currentOption++;
				}
				break;
			
			case Input.KEY_UP:
				if(currentOption > 0) {
					currentOption--;
				}
				break;
				
			// detect key repease on space and enter	
			case Input.KEY_ENTER: case Input.KEY_SPACE:
				switch(currentOption){
				case 0:
					System.out.println("Enter game state");
					sbg.enterState(State.Game.getId());
					break;
				case 1:
					System.out.println("Display scores");
					sbg.enterState(State.Scoreboard.getId());
					break;
				case 2:
					System.out.println("Quit game");
					gc.exit();
				default:
					break;
				}
			
				break;
				
			default:
				break;
			}
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		if (menuReady(delta) && !menuVisible) {
			menuVisible = true;
		}
		bee.update(12);
	}

	@Override
	public int getID() {
		return id;
	}

}
