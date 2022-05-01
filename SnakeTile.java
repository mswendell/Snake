import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;


/**
 * This class is a JButton that the game board will consist of and have different properties based on the snakes location.
 * @author Michael Wendell
 */
public class SnakeTile extends JButton{
	private int numVisited;
	private enum Type {notSnake, Snake};
	private Type tileType;
	
	/**
	 * Default Constructor that will set the created tiles to notSnake.
	 */
	public SnakeTile() {
		numVisited = 0;
		tileType = Type.notSnake;
	}
	
	/**
	 * Will keep track of the times the button has been visited.
	 */
	public void visit() {
		numVisited++;
	}
	
	/**
	 *  Returns the values of the times that the tile has been visited.
	 * @return
	 */
	public int getNumVisited() {
		int copy = numVisited;
		return copy;
	}
	
	/**
	 * Changes the type of the tile to show the loaction of the snake.
	 */
	public void setType() {
		if (tileType == Type.notSnake)
			tileType = Type.Snake;
		else if (tileType == Type.Snake)
			tileType = Type.notSnake;
	}

	/**
	 * This method will paint the tile differently based on the tile type.
	 */
	public void paintComponent(Graphics g) {
		if (tileType == Type.notSnake)
			g.setColor(Color.GRAY);
		if (tileType == Type.Snake)
			g.setColor(Color.GREEN);
		
		// Draws a rectangle the size of the tile.
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
