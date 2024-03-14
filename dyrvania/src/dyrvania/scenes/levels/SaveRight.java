package dyrvania.scenes.levels;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dyrvania.Game;
import dyrvania.Main;
import dyrvania.scenes.objects.Teleport;
import dyrvania.strings.StringError;

public class SaveRight extends Save {

	public SaveRight(Game game, Teleport teleport, String lastScene) {
		super(game, teleport, lastScene);
	}

	@Override
	protected boolean isSceneRight() {
		return true;
	}

	@Override
	protected String currentLevelString() {
		return "save-right";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/save-02.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

}
