package dyrvania.managers;

import dyrvania.resources.GameAudio;

public class GameManagerAudio {

	private static final GameAudio audioMenu;
	private static final GameAudio audioObject;

	private static final GameAudio audioPlayerJump;
	private static final GameAudio audioPlayerHit;
	private static final GameAudio audioPlayerAttack;

	private static final GameAudio audioEnemyHit;

	static {
		audioMenu = new GameAudio("/audios/menu.wav");
		audioObject = new GameAudio("/audios/objects.wav");

		audioPlayerJump = new GameAudio("/audios/jump.wav");
		audioPlayerHit = new GameAudio("/audios/hit_player.wav");
		audioPlayerAttack = new GameAudio("/audios/attack.wav");

		audioEnemyHit = new GameAudio("/audios/hit.wav", -15f);
	}

	public static GameAudio getAudioMenu() {
		return GameManagerAudio.audioMenu;
	}

	public static GameAudio getAudioObject() {
		return GameManagerAudio.audioObject;
	}

	public static GameAudio getAudioPlayerJump() {
		return GameManagerAudio.audioPlayerJump;
	}

	public static GameAudio getAudioPlayerHit() {
		return GameManagerAudio.audioPlayerHit;
	}

	public static GameAudio getAudioPlayerAttack() {
		return GameManagerAudio.audioPlayerAttack;
	}

	public static GameAudio getAudioEnemyHit() {
		return GameManagerAudio.audioEnemyHit;
	}

}