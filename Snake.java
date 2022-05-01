import java.awt.Point;
import java.util.ArrayList;
/**
 * This class creates and tracks the snake object that is stored in an ArrayList.
 * @author Michael Wendell
 */
public class Snake implements SnakeInterface {
	private int tailLength;
	private ArrayList<Point> path;
	private Direction direction;

	/**
	 * Default Constructor that will create a new snake object using two integers for a point. 
	 * with a length of 3 points and add those to the ArrayList.
	 * @param startingX an int representing the starting X coordinate for the Snake.
	 * @param startingY an int representing the starting Y coordinate for the Snake.
	 */
	public Snake(int startingX, int startingY) {
		path = new ArrayList<Point>();
		Point location = new Point(startingX, startingY);
		tailLength = 3;
		// This creates 4 more points after the starting point that will be added to the ArrayList.
		path.add(location);
		 for (int i = 1; i < tailLength + 2; i++) {
			 path.add(new Point(location.x, location.y - i));
		 }
		
		// Calls the get snake function to set the initial snake array.
		getSnake();
		
		// Starting direction will be right.
		direction = Direction.Right;
	}
	
	/**
	 * Default Constructor that will create a new snake object using a starting point.
	 * @param startingPoint a Point that will determine where the snake starts.
	 */
	public Snake(Point startingPoint) {
		path = new ArrayList<Point>();
		Point location = new Point(startingPoint);
		tailLength = 3;
		// This creates 4 more points after the starting point that will be added to the ArrayList.
		path.add(location);
		 for (int i = 1; i < tailLength + 2; i++) {
			 path.add(new Point(location.x, location.y - i));
		 }
		// Calls the get snake function to set the initial snake array.
		getSnake();
		
		// Starting direction will be right.
		direction = Direction.Right;
	}
	
	/**
	 * Default Constructor that will create a new snake object using a starting point and a tail length.
	 * @param startingPoint a Point that will determine where the snake starts.
	 * @param tailLength a length that will determine the starting size of the snake.
	 */
	public Snake(Point startingPoint, int tailLength) {
		path = new ArrayList<Point>();
		Point location = new Point(startingPoint);
		this.tailLength = tailLength;
		// This creates more points after the starting point that will be added to the ArrayList up to the tail length.
		path.add(location);
		 for (int i = 1; i < tailLength + 2; i++) {
			 path.add(new Point(location.x, location.y - i));
		 }
		// Calls the get snake function to set the initial snake array.
		getSnake();
		
		// Starting direction will be right.
		direction = Direction.Right;
	}
	
	@Override
	public Point getHead() {
		Point copy = path.get(0);
		return copy;
	}

	@Override
	public Point getPreviousEnd() {
		Point copy = path.get(path.size() - 1);
		return copy;
	}

	@Override
	public Point[] getSnake() {
		Point[] snake = new Point[path.size()];
		for (int i = 0; i < path.size(); i++) 
			snake[i] = path.get(i);
		return snake;
	}

	@Override
	public int getNumLocationsVisited() {
		int locations = path.size();
		return locations;
	}

	@Override
	public void changeDirection(SnakeInterface.Direction direction) {
		if (this.direction == Direction.Up && direction != Direction.Down) {
			this.direction = direction;
		}
		else if (this.direction == Direction.Down && direction != Direction.Up) {
			this.direction = direction;
		}
		else if (this.direction == Direction.Left && direction != Direction.Right) {
			this.direction = direction;
		}
		else if (this.direction == Direction.Right && direction != Direction.Left){
			this.direction = direction;
		}
	}

	@Override
	public Point move() {
		Point newHead = new Point(path.get(0).x, path.get(0).y);
		if (direction == Direction.Up) {
			newHead = new Point(path.get(0).x - 1, path.get(0).y);
		}
		else if (direction == Direction.Down) {
			newHead = new Point(path.get(0).x + 1, path.get(0).y);
		}
		else if (direction == Direction.Left) {
			newHead = new Point(path.get(0).x, path.get(0).y - 1);
		}
		else if (direction == Direction.Right){
			newHead = new Point(path.get(0).x, path.get(0).y + 1);
		}
		
		for(int i = path.size() - 1; i > 0; i--)
			path.set(i, path.get(i - 1));
		path.set(0, newHead);
		return newHead;
	}

	@Override
	public Point move(SnakeInterface.Direction direction) {
		Point newHead = new Point(path.get(0).x, path.get(0).y);
		changeDirection(direction);
		if (direction == Direction.Up) {
			newHead = new Point(path.get(0).x - 1, path.get(0).y);
		}
		else if (direction == Direction.Down) {
			newHead = new Point(path.get(0).x + 1, path.get(0).y);
		}
		else if (direction == Direction.Left) {
			newHead = new Point(path.get(0).x, path.get(0).y - 1);
		}
		else if (direction == Direction.Right){
			newHead = new Point(path.get(0).x, path.get(0).y + 1);
		}
		for(int i = path.size() - 1; i > 0; i--)
			path.set(i, path.get(i - 1));
		path.set(0, newHead);
		return newHead;
	}

	@Override
	public boolean collisionOccurred() {
		for (int i = 1; i < path.size() - 1; i++) {
			if (path.get(0).equals(path.get(i))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isGameOver(int width, int height) {
		if (collisionOccurred())
			return true;
		//if snake hits wall
		else if (path.get(0).x >= width || path.get(0).x < 0)
			return true;
		else if (path.get(0).y >= height || path.get(0).y < 0) 
			return true;
		return false;
	}

	@Override
	public void increaseLength() {
		Point newtail = new Point(path.get(tailLength));
		path.add(newtail);
		tailLength++;
		getSnake();
	}
	
	/**
	 * toString method to write all the points in the array to a string.
	 * @return String a String that represents all the points in the snake array.
	 */
	public String toString() {
		String str = "";
		for (Point snake : path)
			str += snake;
		return str;
	}

}
