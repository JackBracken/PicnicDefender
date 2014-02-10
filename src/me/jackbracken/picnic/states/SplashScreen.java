package me.jackbracken.picnic.states;

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashScreen extends BasicGameState {
	private int id, backgroundWidth, hOffset, titleHorizontalPosition, menuReadyTime;
	private String title = "Picnic Defender";
	private Image bg1, bg2;
	private Animation fly;
	private TrueTypeFont menuTitle, menuItems;
	private long pastTime = 0;
	private boolean menuVisible = false;
	
	private String[] menuOptions = {
			"Play Game",
			"High Scores",
			"Quit Game"	
	};
	
	public SplashScreen(State state) {
		id = state.getId();
	}
	
	public boolean menuReady(long delta) {
		if(pastTime < menuReadyTime) {
			pastTime += delta;
			return false;
		} else  {
			return true;
		}
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		// Set menu ready time in millis
		menuReadyTime = 3000;

		bg1 = new Image("res/menu/bg.png");
		bg2 = new Image("res/menu/bg.png");
		backgroundWidth = bg1.getWidth();
		hOffset = -backgroundWidth + gc.getWidth();
		
		Image[] flySprite = {
				new Image("res/fly_normal.png"), 
				new Image("res/fly_fly.png")
		};
		
		fly = new Animation(flySprite, 100, false);
	
		menuTitle = new TrueTypeFont(new Font("Verdana", Font.BOLD, 42), true);
		menuItems = new TrueTypeFont(new Font("Verdana", Font.BOLD, 26), true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		hOffset += 4;
		int menuItemOffset = 300;
		
		if(hOffset > gc.getWidth()) {
			hOffset = -backgroundWidth + gc.getWidth();
		}
		
		bg1.draw(hOffset, 0);
		bg2.draw(hOffset - backgroundWidth, 0);
		
		fly.draw(gc.getWidth() / 2 - fly.getWidth() / 2, 100);
		
		menuTitle.drawString(centerText(gc, menuTitle, title), 200, title, Color.darkGray);
		
		if(menuVisible) {
			menuItems.drawString(centerText(gc, menuItems, "Play Game"), 300, "Play Game", Color.darkGray);
			for(int i = 0; i < menuOptions.length; i++) {
				menuItems.drawString(centerText(gc, menuItems, menuOptions[i]), menuItemOffset + 40 * i , menuOptions[i], Color.darkGray);
			}
		}
	}
	
	private int centerText(GameContainer gc, TrueTypeFont tff, String text) {
		return gc.getWidth() / 2 - tff.getWidth(text) / 2;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		if(menuReady(delta) && !menuVisible) {
			menuVisible = true;
		}
		
		fly.update(16);
	}

	@Override
	public int getID() {
		return id;
	}

}
