package dyrvania.screens;

import java.awt.Color;
import java.awt.Graphics;

import dyrvania.Game;
import dyrvania.generics.GameStatus;

public class Transition {

	private final Game game;

	private int frames;

	private GameStatus nextGameStatus;

	public Transition(Game game, GameStatus gameStatus) {
		this.game = game;

		this.frames = 0;

		this.nextGameStatus = gameStatus;
	}

	public void setNextGameStatus(GameStatus nextGameStatus) {
		this.frames = 0;
		this.nextGameStatus = nextGameStatus;
	}

	public void tick() {
		this.frames++;

		if (this.frames > 60) {
			this.frames = 0;
			this.game.updateGameStatus(this.nextGameStatus);
		}
	}

	public void render(Graphics render) {
		render.setColor(Color.BLACK);
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getHeight());
	}

}
