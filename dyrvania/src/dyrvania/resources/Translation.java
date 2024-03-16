package dyrvania.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import dyrvania.Main;
import dyrvania.strings.GameString;
import dyrvania.strings.StringError;
import dyrvania.strings.StringLevel;
import dyrvania.strings.StringScreen;

public class Translation {

	private static String language = "english";

	public enum Language {
		ENGLISH, PORTUGUESE, SPANISH;
	}

	public static String getLanguage() {
		return Translation.language;
	}

	public static void changeTheLanguage(Language language) {
		if (language == Translation.Language.PORTUGUESE) {
			Translation.language = "portuguese";
		} else if (language == Translation.Language.SPANISH) {
			Translation.language = "spanish";
		} else {
			Translation.language = "english";
		}

		try {
			Translation.toTranslation("screen", new GameString[] {
					StringScreen.TUTORIAL_FULL_SCREEN,
					StringScreen.NEW_GAME,
					StringScreen.LOAD_GAME,
					StringScreen.CREDITS,
					StringScreen.TUTORIAL,
					StringScreen.EXIT,
					StringScreen.EXIT_GAME,
					StringScreen.YES,
					StringScreen.NO,
					StringScreen.NO_DATA,
					StringScreen.BACK,
					StringScreen.CONFIRM_NEW_GAME_LINE_1,
					StringScreen.CONFIRM_NEW_GAME_LINE_2,
					StringScreen.CONFIRM_MAIN_MENU,
					StringScreen.CONTINUE,
					StringScreen.MENU
			});

			Translation.toTranslation("level", new GameString[] {
					StringLevel.TUTORIAL_FULL_SCREEN,
					StringLevel.TUTORIAL_FPS,
					StringLevel.TUTORIAL_MUSIC,
					StringLevel.TUTORIAL_PAUSE,
					StringLevel.TUTORIAL_MOVE,
					StringLevel.TUTORIAL_JUMP,
					StringLevel.TUTORIAL_ATTACK,
					StringLevel.TUTORIAL_OBJECT_SWORD,
					StringLevel.TUTORIAL_OBJECT_LIFE,
					StringLevel.INFO_SAVE,
					StringLevel.INFO_GAME_SAVED,
					StringLevel.INFO_LIFE_RESTORED,
					StringLevel.INFO_NEGATIVE_EFFECTS_REMOVED
			});
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}
	}

	private static void toTranslation(String file, GameString[] messageString) throws IOException {
		String fileName = String.format("/languages/%s/%s.txt", Translation.language, file);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Translation.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			for (int i = 0; i < messageString.length; i++) {
				messageString[i].setValue(Translation.readLine(reader));
			}
		}
	}

	private static String readLine(BufferedReader reader) throws IOException {
		String content = reader.readLine();

		if (content == null) {
			throw new IOException();
		}

		return content;
	}

}