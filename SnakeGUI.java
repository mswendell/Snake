import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * This is the GUI for the application. It will create a settings window for the user to start a new game and then create a snake game. It also contains a main() method for 
 * the entry point of the application.
 * @author Michael Wendell
 *
 */

public class SnakeGUI extends JComponent implements KeyListener{
	private static final int DEFAULT_WINDOW_WIDTH = 1000;
	private static final int DEFAULT_WINDOW_HEIGHT = 1000;
	private Timer timer;
	private int timeDelay;
	private JLabel pointLabel;
	private JLabel levelLabel;
	private long points;
	private int level;
	private JButton reset;
	private SnakeTile[][] tiles;
	private Snake snake;
	private SnakeSettings ss;
	private SnakeSettingsPanel ssp;
	
	/**
	 * The default constructor for the GUI will first display a settings panel then once yes is hit it call the setupGUI() method.
	 */
	public SnakeGUI()  {
		ss = new SnakeSettings();
		ssp = new SnakeSettingsPanel(ss);
		int result = JOptionPane.showInternalConfirmDialog(null, ssp, "Play Snake? Choose your Options", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			ss = ssp.getSettings();
		}
		else {
			System.exit(1);
		}
		setupGUI();
	}
	
	/**
	 * The setupGUI() method will create a window with a default size and then populate it with the JComponents needed to create 
	 * the game of snake according to the desired settings.
	 */
	private void setupGUI() {
		//sets the main panels size and layout.
		setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
		this.setLayout(new BorderLayout());
		
		//adds a listener to detect when an arrow key is pressed.
		addKeyListener(this);
		
		//creates and adds a JPanel of snake tiles sized to the snakeSettings and adds it to the main JFrame.
		JPanel gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(ss.getHeight(), ss.getWidth()));
		gameBoard.setPreferredSize(new Dimension(1000, 1000));
		tiles = new SnakeTile[ss.getHeight()][ss.getWidth()];
		 for (int i = 0; i < ss.getHeight(); i++) {
			 for (int j = 0; j < ss.getWidth(); j++) {
				 tiles[i][j] = new SnakeTile();
				 gameBoard.add(tiles[i][j]);
			 }
		 }
		 this.add(gameBoard, BorderLayout.CENTER);
		 
		 // Creates a new panels to store the points and the level at the top of the JPanel.
		 JPanel page = new JPanel();
		 page.setLayout(new FlowLayout());
		 
		 // Panel to store the points Labels.
		 JPanel pointsPanel = new JPanel();
		 pointsPanel.setLayout(new BorderLayout());
		 pointLabel = new JLabel("Points: " + points);
		 pointsPanel.add(pointLabel, BorderLayout.CENTER);
		 page.add(pointsPanel, BorderLayout.WEST);
		 
		 // Panel to store the Level labels.
		 JPanel levels = new JPanel();
		 levelLabel = new JLabel("Level: " + level);
		 levels.add(levelLabel, BorderLayout.CENTER);
		 page.add(levels, BorderLayout.EAST);
		 
		 // Adding the panels to the main JPanel
		 this.add(page, BorderLayout.NORTH);
		 
		 // Creating a Snake object at tile 5,5 and then setting the start size of the snake to 3 tiles long.
		 snake = new Snake(5, 5);
		 tiles[snake.getSnake()[1].x][snake.getSnake()[1].y].setType();
		 tiles[snake.getSnake()[2].x][snake.getSnake()[2].y].setType();
		 tiles[snake.getSnake()[3].x][snake.getSnake()[3].y].setType();
		
		 // Creates a reset button at the bottom of the page and adds a separate listener to check if the button is pushed.
		 reset = new JButton("Reset");
		 this.add(reset, BorderLayout.SOUTH);
		 reset.addActionListener(new ResetListener());
		 
		// Sets the time delay of the game from the desired speed stored in SnakeSettings. Will default to slow.
		if (ss.getSpeed() == SnakeSettings.Speed.Fast) {
			timeDelay = 75;
			}
		else if (ss.getSpeed() == SnakeSettings.Speed.Medium) {
			timeDelay = 150;
		}
		else if (ss.getSpeed() == SnakeSettings.Speed.LudicrousSpeed) {
			timeDelay = 10;
	    }
		else 		
				timeDelay = 300;
		 
		// Creates the timer object and calls the ActionListener every time delay. Then, starts the timer and makes sure it is focused.
		 timer = new Timer(timeDelay, (ActionListener) new MyActionListener());
		 timer.start();
		 setFocusable(true);
		 this.requestFocus();	
	}
	
	/**
	 * MyActionListener class that will implement the action listener for the timer every time delay.
	 */
	private class MyActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// Checks if the game is over before executing other functions.
			if (snake.isGameOver(tiles.length, tiles[0].length)) {
				timer.stop();
				resetGame();
			}
			// Changes the type of snake tile at the head of the snake to make its color green.
			tiles[snake.getSnake()[0].x][snake.getSnake()[0].y].setType();
			
			// Moves the head point of the snake every time this is called.
			snake.move();
			
			// Adds points to the total points.
			updatePoints();
			
			// Increases the length of the snake based on the amount of points. Every 300 points the snake gets longer.
			if (points % 300 == 0)
				snake.increaseLength();
			
			// This will change the tile at the end of the snake back to a non-Snake tile. Will not happen if the snake gets longer.
			else
				tiles[snake.getSnake()[snake.getSnake().length - 1].x][snake.getSnake()[snake.getSnake().length - 1].y].setType();
			
			// Increases the level every 1000 points.
			if (points % 1000 == 0)
				updateLevel();
			
			// Repaints the screen after every change has been made.
			repaint();
		}
	}
	
	/**
	 * Action Listener class to check if the reset button has been pushed. Will reset game if it has.
	 */
	private class ResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			resetGame();
		}
		
	}
	
	/**
	 * resetGame method will reset all options of the SnakeGUI and then re-setup the game if the user wants to.
	 */
	private void resetGame() {
		timer.stop();
		// Displays the options pane to the user again.
		int result = JOptionPane.showInternalConfirmDialog(null, ssp, "Game Reset. Play again? Choose your Options", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (result == JOptionPane.NO_OPTION) {
			System.exit(1);
		}
		if(result == JOptionPane.YES_OPTION) {
			// Changes the SnakeSettings to match the new desired options.
			ss = ssp.getSettings();
			
			// Resets the rest of the values.
			points = 0;
			level = 0;
			
			//Removes all current items.
			removeAll();
			
			// Calls the setupGUI mehtod again to populate the JPanel and revalidates the JPanel.
			setupGUI();
			this.revalidate();
		}
	}
	
	/**
	 * updatePoints will add 100 points when called and then update the points label with the new score.
	 */
	private void updatePoints() {
		points += 100;
		pointLabel.setText("Points: "+ points);
	}
	
	/**
	 * updateLevel will add 1 level when called and then update the level label with the new score. 
	 * This method will also change the speed depending on the level.
	 */
	private void updateLevel() {
		level += 1;
		levelLabel.setText("Level: " + level);
		if (level % 5 == 0 && ss.getSpeed() == SnakeSettings.Speed.Slow) {
			ss.setSpeed(SnakeSettings.Speed.Medium);
			timer.setDelay(200);
		}
		else if (level % 5 == 0 && ss.getSpeed() == SnakeSettings.Speed.Medium) {
			ss.setSpeed(SnakeSettings.Speed.Fast);
			timer.setDelay(75);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			snake.changeDirection(SnakeInterface.Direction.Up);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			snake.changeDirection(SnakeInterface.Direction.Down);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			snake.changeDirection(SnakeInterface.Direction.Left);
		}	
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			snake.changeDirection(SnakeInterface.Direction.Right);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	/**
	 * Main method that will create the JFrame and SnakeGUI Object for the java entry point of the user.
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Snake");
		SnakeGUI snakeGame = new SnakeGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(snakeGame);
		frame.pack();
		frame.setVisible(true);
	}
	

}
