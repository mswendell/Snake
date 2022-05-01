/**
 * This class stores all the settings for the SnakeGUI.
 * @author Michael Wendell
 *
 */
public class SnakeSettings {
	private static final int MINIMUM_WIDTH = 10; 	
	private static final int MINIMUM_HEIGHT = 10;
	private static final int DEFAULT_WIDTH = 40;
	private static final int DEFAULT_HEIGHT = 40;
	public static enum Speed {Slow, Medium, Fast, LudicrousSpeed};
	private static Speed speed = Speed.Slow;
	private int width;
	private int height;
	
	/**
	 * Default Constructor will set the default width and height of the game.
	 */
	public SnakeSettings() {
		width = DEFAULT_WIDTH;
		height =  DEFAULT_HEIGHT;
	}
	
	/**
	 * getSpeed will return a copy of the speed enumerator.
	 * @return Speed a copy of the Speed of the game.
	 */
	public Speed getSpeed() {
		Speed copySpeed = speed;
		return copySpeed;
	}
	
	/**
	 * setSpeed will change the speed enumerator to match the parameter.
	 * @param inputSpeed the Speed enumerator value to change to.
	 */
	public void setSpeed(Speed inputSpeed) {
		speed = inputSpeed;
	}
	
	/**
	 * getWidth will return a copy of the width setting.
	 * @return int an int value of the width.
	 */
	public int getWidth() {
		int copy = width;
		return copy;
	}
	
	/**
	 * setWidth will change the width setting of the game. Will not change if below the minimum value.
	 * @param newWidth an int value to change the width to.
	 */
	public void setWidth(int newWidth) {
		if(newWidth > MINIMUM_WIDTH) {
			width = newWidth;
		}
	}
	
	/**
	 * getHeight will return a copy of the height value.
	 * @return int an int value of the height.
	 */
	public int getHeight() {
		int copy = height;
		return copy;
	}
	
	/**
	 * setHeigth will change the Height setting of the game. Will not change if below the minimum value.
	 * @param newHeight an int value to change the height to.
	 */
	public void setHeight(int newHeight) {
		if (newHeight > MINIMUM_HEIGHT) {
			height = newHeight;
		}
	}
}
