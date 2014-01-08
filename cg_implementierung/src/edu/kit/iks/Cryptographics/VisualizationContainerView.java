package edu.kit.iks.Cryptographics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * An instance of this class represents the view of container controller
 * beside the actual visualization
 * 
 * @author Christian Dreher
 */
public class VisualizationContainerView extends JPanel {
	
	/**
	 * Name of the current visualization
	 */
	private JLabel nameLabel;
	
	/**
	 * Button to exit the current visualization
	 */
	private JButton exitButton;
	
	/**
	 * Button to access interactive help for the current step inside the
	 * visualization
	 */
	private JButton helpButton;
	
	/**
	 * Constructor initializing a new instance of {VisualizationContainerView}
	 */
	public VisualizationContainerView() {
		this.add(new JLabel("CONTAINER"));
		this.validate();
	}
}
