package edu.kit.iks.Cryptographics.Caesar.Experiment;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTextField;

import edu.kit.iks.Cryptographics.VisualizationContainerController;
import edu.kit.iks.Cryptographics.Caesar.CaesarVisualizationInfo;
import edu.kit.iks.CryptographicsLib.AbstractVisualizationController;
import edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo;

/**
 * Controller for the first and second step of the experiment phase. When user has to put input and
 * encrypt it and in the second step to decrypt a given cipher. Controls the needed elements from
 * CaesarUpperView, !!see CGeneralView for more details!!.
 * 
 * @author Wasilij Beskorovajnov.
 * 
 */
public class CryptoController extends AbstractVisualizationController {

	/**
	 * Model that is needed for computations.
	 */
	private CryptoModel model;

	/**
	 * Needed
	 */
	private int editableFields;

	private boolean decryptionPhase;

	/**
	 * Constructor.
	 * 
	 * @param visualizationInfo
	 */
	public CryptoController(AbstractVisualizationInfo visualizationInfo) {
		super(visualizationInfo);
		decryptionPhase = false;

	}

	@Override
	public void loadView() {
		this.view = new CryptoView();
		this.model = new CryptoModel();

		this.getView().getGenerator().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				char[] string = getModel().generatePlainText();
				setEditableFields(string.length);
				// TODO: String generator!
				getView().start(string);

				// generate ActionListener.
				generateListener(string);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		this.getView().getInput().addActionListener(new ActionListener() {

			/*
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event. ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = getView().getInput().getText();
				// TODO: check input for validity!
				if (getModel().handleInput(input)) {
					// refactor the input into an character array.
					char[] inputChars = new char[input.length()];
					input = input.toUpperCase();
					input.getChars(0, input.length(), inputChars, 0);
					setEditableFields(inputChars.length);
					// load the view!
					getView().start(inputChars);

					// Generate Listener for the userOutput JTextfield
					generateListener(inputChars);
				} else {
					// TODO: Input was invalid. Pls make another one.
					getView().getExplanations().setText(
							"Your input is invalid. Please try another one!");
				}

			}
		});
		this.getView().getBackButton().addActionListener(new ActionListener() {
			/*
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt .event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				VisualizationContainerController containerController = (VisualizationContainerController) getParentController();
				containerController.presentPreviousVisualizationController();
			}
		});
		this.getView().getNextButton().addActionListener(new ActionListener() {
			/*
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt .event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				if (!decryptionPhase) {
					//TODO: implement unloadView();
					unloadView();
					// start Decryption!
					decryptionPhase = true;
					// TODO: generate a random cipher.
					char[] string = getModel().generateCipher();
					setEditableFields(string.length);
					getView().start(string);

					// generate ActionListener.
					generateListener(string);

					// set the explanations.
					getView()
							.getExplanations()
							.setText(
									"The stage for decryption is not finished yet, to skip click next or exit!!");
					getView().getNextButton().setText("Go to histograms!");

				} else {
					VisualizationContainerController containerController = (VisualizationContainerController) getParentController();
					containerController.presentNextVisualizationController();
				}
			}
		});

	}

	/**
	 * Function for generating ActionListener for the needed input fields, after the user typed a
	 * string to encrypt.
	 * 
	 * @param inputChars
	 */
	public void generateListener(char[] inputChars) {
		// Generate Listener for the userOutput JTextfield
		for (int i = 0; i < inputChars.length; i++) {
			getView().getUserOutput()[i]
					.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO: let the Model check for validity.
							JTextField userOutput = (JTextField) e.getSource();

							if (getModel().checkValidChar(userOutput.getName(),
									userOutput.getText())) {
								// user encrypted the given char successful.
								userOutput.setBorder(BorderFactory
										.createLineBorder(Color.green));
								userOutput.setEditable(false);
								setEditableFields(getEditableFields() - 1);

								if (getEditableFields() == 0) {
									// User encrypted all characters valid.
									getView()
											.getExplanations()
											.setText(
													"<html><body>All done right!!! Great job comrade. Now you are one step more to destroying the capitalism!<br>"
															+ "Next step is to decrypt a given message!! When you accomplish it, then even the NSA and Kryptochef together<br>"
															+ "are superior to your power!");
								} else {
									getView().getExplanations().setText(
											"Great!!!! Go on!");
								}
							} else {
								// TODO: user encrypted invalid! Need another try.
								userOutput.setBorder(BorderFactory
										.createLineBorder(Color.red));
								getView()
										.getExplanations()
										.setText(
												"You picked the wrong letter!! Try another one!");
							}
						}

					});
		}
	}

	/**
	 * Method for delegating user's input to the model for the needed computations and checks.
	 * 
	 * @param input
	 */
	public void delegateAndHandleInput(String input) {
		if (this.model.handleInput(input))
			proceed();
	}

	@Override
	public void unloadView() {
	  	
	}
	@Override
	public String getHelp() {
		return "If you only see the textfield then put your string in it. Else you've already "
				+ "done it and now you need to encrypt/decrypt the given String.";
	}

	/**
	 * Gets the view
	 * 
	 * @return The view of this controller
	 */
	@Override
	public CryptoView getView() {
		return (CryptoView) this.view;
	}

	/**
	 * @return the model
	 */
	public CryptoModel getModel() {
		return model;
	}

	/**
	 * Method called when the input was correct.
	 */
	private void proceed() {

	}

	/**
	 * @return the editableFields
	 */
	public int getEditableFields() {
		return editableFields;
	}

	/**
	 * @param editableFields
	 *            the editableFields to set
	 */
	public void setEditableFields(int editableFields) {
		this.editableFields = editableFields;
	}

}
