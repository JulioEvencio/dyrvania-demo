package dyrvania.scenes.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameUtil;
import dyrvania.resources.Spritesheet;
import dyrvania.scenes.Scene;

public class Skeleton {

	private final Scene scene;

	private final GameRectEntity rect;

	private int hp;
	private final int hpMax;

	private int damage;
	private boolean hasAShield;

	private double speedX;
	private double speedY;

	private boolean isDirRight;
	private GameSpriteAnimation currentSprite;

	private final GameSpriteAnimation spriteRunRight;
	private final GameSpriteAnimation spriteRunLeft;

	public Skeleton(Scene scene, int x, int y) {
		this.scene = scene;

		this.rect = new GameRectEntity(x, y, 20, 40);

		this.hpMax = 3;
		this.hp = this.hpMax;

		this.damage = 1;
		this.hasAShield = false;

		this.speedX = 0.5;
		this.speedY = 0;

		this.isDirRight = GameUtil.generateRandomNumber(0, 1) == 0;

		int spriteWidth = 44;
		int spriteHeight = 52;

		GameRect spriteRect = new GameRect(x, y, spriteWidth, spriteHeight);

		// Run Right
		BufferedImage[] runRight = new BufferedImage[8];

		for (int i = 0; i < 8; i++) {
			runRight[i] = Spritesheet.getSpriteSkeleton(spriteWidth * i, 104, spriteWidth, spriteHeight);
		}

		spriteRunRight = new GameSpriteAnimation(spriteRect, 5, runRight);

		// Run Left
		BufferedImage[] runLeft = new BufferedImage[8];

		for (int i = 0; i < 8; i++) {
			runLeft[i] = Spritesheet.getSpriteSkeleton(spriteWidth * i, 156, spriteWidth, spriteHeight);
		}

		spriteRunLeft = new GameSpriteAnimation(spriteRect, 5, runLeft);

		this.updateCurrentSprite(this.spriteRunRight);
		this.updateSpritePosition();
	}

	public GameRect getRect() {
		return this.rect.getRect();
	}

	public boolean isDead() {
		return this.hp <= 0;
	}

	public int dealDamage() {
		return this.damage;
	}

	public void takeDamage(int damage) {
		if (!this.hasAShield) {
			this.hp -= damage;
			this.hasAShield = true;
		}
	}

	public void resetShield() {
		this.hasAShield = false;
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);

		this.updateSpritePosition();
	}

	private void applyGravity() {
		this.speedY += this.scene.getGravity();

		if (this.speedY > 7) {
			this.speedY = 7;
		}

		for (double i = 0; i <= this.speedY; i += 0.5) {
			if (!this.isOnTheFloor()) {
				this.rect.setY(this.rect.getY() + 0.5);
			} else {
				this.speedY = 0;
				break;
			}
		}
	}

	private void toMove() {
		if (this.isOnTheFloor()) {
			double vel;
			GameRectEntity newRect = new GameRectEntity(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());

			if (this.isDirRight) {
				vel = 0.5;
				newRect.setX(newRect.getX() + newRect.getWidth());
			} else {
				vel = -0.5;
				newRect.setX(newRect.getX() - newRect.getWidth());
			}

			newRect.setY(newRect.getY() + 0.5);

			for (double i = 0; i <= this.speedX; i += 0.5) {
				if (this.scene.isFree(new GameRectEntity(this.rect.getX() + vel, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()).getRect())) {
					this.rect.setX(this.rect.getX() + vel);

					if (!this.isOnTheFloor()) {
						this.rect.setY(this.rect.getY() + 0.5);
					}
				} else {
					this.isDirRight = !this.isDirRight;
					break;
				}
			}

			if (this.scene.isFree(newRect.getRect())) {
				this.isDirRight = !this.isDirRight;
			}
		}
	}

	private boolean isOnTheFloor() {
		return !this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.5, this.rect.getWidth(), this.rect.getHeight()).getRect());
	}

	private void updateSpritePosition() {
		if (this.isDirRight) {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 11, this.rect.getRect().getY() - 11);
		} else {
			this.currentSprite.setPosition(this.rect.getRect().getX() - 14, this.rect.getRect().getY() - 11);
		}
	}

	private void updateCurrentSprite(GameSpriteAnimation newSprite) {
		if (this.currentSprite != newSprite) {
			if (this.currentSprite != null) {
				this.currentSprite.reset();
			}

			this.currentSprite = newSprite;
		}
	}

	public void tick() {
		this.applyGravity();
		this.toMove();
		this.updateSpritePosition();

		if (this.isDirRight) {
			this.updateCurrentSprite(this.spriteRunLeft);
		} else {
			this.updateCurrentSprite(this.spriteRunRight);
		}

		this.currentSprite.tick();
	}

	public void render(Graphics render) {
		this.currentSprite.render(render);
	}

}
