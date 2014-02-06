package states;

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
	private int id, backgroundWidth, hOffset, titleHorizontalPosition;
	private String title = "Picnic Defender";
	private Image bg1, bg2;
	private Animation fly;
	private TrueTypeFont menuTitle;
	
	
	public SplashScreen(State state) {
		id = state.getId();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
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
		titleHorizontalPosition = (gc.getWidth() / 2) - (menuTitle.getWidth(title) / 2);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		hOffset += 2;
		
		if(hOffset > gc.getWidth()) {
			hOffset = -backgroundWidth + gc.getWidth();
		}
		
		bg1.draw(hOffset, 0);
		bg2.draw(hOffset - backgroundWidth, 0);
		
		fly.draw(gc.getWidth() / 2 - fly.getWidth() / 2, 100);
		
		menuTitle.drawString(titleHorizontalPosition, 200, title, Color.darkGray);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		fly.update(8);
	}

	@Override
	public int getID() {
		return id;
	}

}
