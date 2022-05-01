import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This Class will be displayed by the JOptionPane and will allow users to edit the SnakeSetting values based on the inputs.
 * @author Michael Wendell
 *
 */
public class SnakeSettingsPanel extends JPanel{
	private SnakeSettings ss;
	private JTextField inWidth;
	private JTextField inHeight;
	private JComboBox inSpeed = new JComboBox();

/**
 * Default Constructor that sets up the default SnakeSettings and then calls the setup. 
 * @param ss is a SnakeSettings object to edit from the panel
 */
	public SnakeSettingsPanel(SnakeSettings ss) {
		this.ss = ss;
		setupGUI();
	}
	
	/**
	 * getSettings will set the current SnakeSettings object to the input settings and return a copy of those settings.
	 * @return SnakeSetings a SnakeSetings object with the vaules chaged to match the input values on the SnakeSettingsPanel.
	 */
	public SnakeSettings getSettings() {
		// Tries to set the Width, Height, and Speed of the SnakeSettings to match the input values if valid.
		try {
		ss.setHeight(Integer.parseInt(inHeight.getText()));
		ss.setWidth(Integer.parseInt(inWidth.getText()));
		
		if (inSpeed.getSelectedItem() == "Ludicrous Speed")
			ss.setSpeed(SnakeSettings.Speed.LudicrousSpeed);
		if (inSpeed.getSelectedItem() == "Fast")
			ss.setSpeed(SnakeSettings.Speed.Fast);
		else if (inSpeed.getSelectedItem() == "Medium")
			ss.setSpeed(SnakeSettings.Speed.Medium);
		else if (inSpeed.getSelectedItem() == "Slow")
			ss.setSpeed(SnakeSettings.Speed.Slow);
		}
		catch (Exception e) {
			System.out.println("Please enter a valid size.");
		}
		SnakeSettings copy = ss;
		return copy;
	}
	
/**
 *  setupGUI method will set the layout and the panels within the SnakeSettingsPanel.
 */
	private void setupGUI() {
		
		// Layout and Size settings.
		setLayout(new GridLayout(2, 1));
		setPreferredSize(new Dimension(300, 200));
		
		// Adds a new panel for all the text. 
		JPanel text = new JPanel();
		
		// Creates a label for width.
		JLabel width = new JLabel("Width");
		text.add(width);
		// Creates a text field for width.
		inWidth = new JTextField(5);
		inWidth.setText("40");
		text.add(inWidth);
		
		// Creates a label for height.
		JLabel height = new JLabel("Heigth");
		text.add(height);
		
		// Creates a text field for height.
		inHeight = new JTextField(5);
		inHeight.setText("40");
		text.add(inHeight);
		
		// Creates a label for speed.
		JLabel speed = new JLabel("Speed");
		text.add(speed);
		
		// Creates a JComboBox for the different speed settings.
		String[] speeds = {"Slow", "Medium", "Fast", "Ludicrous Speed"};
		inSpeed = new JComboBox(speeds);
		inSpeed.setSelectedIndex(0);
		text.add(inSpeed);
		
		// Adds the text panel to the SnakeSetings panel.
		text.setLayout(new GridLayout(3, 2));
		add(text);
	}
	


}
