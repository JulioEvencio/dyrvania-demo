package dyrvania.generics;

public class GameRectEntity {

	private double x;
	private double y;

	private int width;
	private int height;

	public GameRectEntity(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public GameRect getRect() {
		return new GameRect((int) x, (int) y, width, height);
	}

}