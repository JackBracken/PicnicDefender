package me.jackbracken.picnic.states;

public enum State {
	SplashScreen(0),
	MainMenu(1),
	Game(2),
	PauseMenu(3),
	EndGame(4);
	
	State(int id) {
		this.id = id;
	}
	
	private int id;

	public int getId() {
		return id;
	}
	
}
