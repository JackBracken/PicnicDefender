package me.jackbracken.picnic.states;

public enum State {
	SplashScreen(0),
	Game(1),
	PauseMenu(2),
	EndGame(3),
	Scoreboard(4);
	
	State(int id) {
		this.id = id;
	}
	
	private int id;

	public int getId() {
		return id;
	}
	
}
