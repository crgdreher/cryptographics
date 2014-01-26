package edu.kit.iks.Cryptographics.Caesar.Experiment;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

import edu.kit.iks.Cryptographics.VisualizationContainerController;
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

	private boolean decryptionPhase = false;

	/**
	 * Constructor.
	 * 
	 * @param visualizationInfo
	 */
	public CryptoController(AbstractVisualizationInfo visualizationInfo) {
		super(visualizationInfo);

	}

	@Override
	public void loadView() {
		this.view = new CryptoView();
		this.model = new CryptoModel();
		this.editableFields = 2;

		// Create all needed ActionListener.
		this.getView().getGenerator().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				char[] string = getModel().generatePlainText();
				int key = getModel().generateKey();
				setEditableFields(string.length);

				// TODO: String and Key generator!
				getView().start(string, key);

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
		this.getView().getKey().addFocusListener(new FocusListener() {
			// TODO: make the strings to set dynamically. Avoid hard code.
			@Override
			public void focusLost(FocusEvent e) {
				if (getView().getKey().isEditable()) {
					getView().getKey().setText("Key");
					getView().remove(getView().getKeyboard());
				}

			}

			// TODO: make the strings to set dynamically. Avoid hard code.
			@Override
			public void focusGained(FocusEvent e) {
				if (getView().getKey().isEditable()) {
					getView().getKey().setText("");
					popupKeyboard();
				}
			}
		});
		this.getView().getKey().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// TODO: Need to take the explanation from other resources. At the moment it
					// leads to invalid behaviour.
					String explanationContent = getView().getExplanations()
							.getText();
					int key = Integer.parseInt(getView().getKey().getText());
					if (getModel().handleKeyInput(key)
							&& (getEditableFields() - 1) != 0) {
						getView()
								.getExplanations()
								.setText(
										explanationContent
												+ "<br>"
												+ "<br>This key is ok. Now put your name into the bigger box to the left.");
						getView().getKey().setBorder(
								BorderFactory.createLineBorder(Color.green));
						getView().getKey().setEditable(false);
						setEditableFields((getEditableFields() - 1));
					} else if (getModel().handleKeyInput(key)
							&& (getEditableFields() - 1) == 0) {
						try {
							String input = getView().getInput().getText();
							char[] inputChars = new char[input.length()];
							input = input.toUpperCase();
							input.getChars(0, input.length(), inputChars, 0);
							setEditableFields(inputChars.length);
							getView().getKey().setBorder(null);

							// load the view!
							getView().start(inputChars, key);

							// Generate Listener for the userOutput JTextfield
							generateListener(inputChars);
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {
						getView()
								.getExplanations()
								.setText(
										explanationContent
												+ "<br>"
												+ "<br>This key is not valid. Please put a number between 1 and 25.<br>"
												+ "For demonstration purposes the keys between -1 and -25 are not necessary<br>"
												+ "therefore not possible, but could be used as keys too. And 0 as key has no<br>"
												+ " sense, if you dont understand why, then go back to Introduction.");
						getView().getKey().setBorder(
								BorderFactory.createLineBorder(Color.red));
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		this.getView().getInput().addFocusListener(new FocusListener() {
			// TODO: make the strings to set dynamically. Avoid hard code.
			@Override
			public void focusGained(FocusEvent e) {
				if (getView().getInput().isEditable()) {
					getView().getInput().setText("");
					popupKeyboard();
				}

			}

			// TODO: make the strings to set dynamically. Avoid hard code.
			@Override
			public void focusLost(FocusEvent e) {
				if (getView().getInput().isEditable()) {
					getView().getInput().setText("Put your name here.");
					getView().remove(getView().getKeyboard());
				}
			}

		});
		this.getView().getInput().addActionListener(new ActionListener() {

			/*
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event. ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: At the moment the user has to put first the key and then his name. It is
				// invalid behaviour. There must be no order what to put in first. Need some more
				// checks.
				String explanationContent = getView().getExplanations()
						.getText();
				String input = getView().getInput().getText();

				// TODO: check input for validity!
				if (getModel().handleInput(input) && (getEditableFields() - 1) != 0) {
					getView()
							.getExplanations()
							.setText(
									explanationContent
											+ "<br>"
											+ "<br>This input is ok. Now only the key is left.");
					getView().getInput().setBorder(
							BorderFactory.createLineBorder(Color.green));
					getView().getInput().setEditable(false);
					setEditableFields((getEditableFields() - 1));
				} else if (getModel().handleInput(input)
						&& (getEditableFields() - 1) == 0) {

					// refactor the input into an character array.
					try {
						int key = Integer
								.parseInt(getView().getKey().getText());
						char[] inputChars = new char[input.length()];
						input = input.toUpperCase();
						input.getChars(0, input.length(), inputChars, 0);
						setEditableFields(inputChars.length);
						getView().getKey().setBorder(null);

						// load the view!
						getView().start(inputChars, key);

						// Generate Listener for the userOutput JTextfield
						generateListener(inputChars);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
				// When switching back from demonstration to
				// experiment in the encryption phase, the variable will remain set to true when not
				// reset.
				if (decryptionPhase) {
					decryptionPhase = false;
				}
				// load next state.
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
					int key = -(getModel().generateKey());
					// TODO: implement unloadView();
					unloadInOut();
					// start Decryption!
					decryptionPhase = true;
					// TODO: generate a random cipher.
					char[] string = getModel().generateCipher();
					setEditableFields(string.length);
					getView().setupInOutElements(string, key);
					// generate ActionListener.
					generateListener(string);

					// set the explanations.
					getView()
							.getExplanations()
							.setText(
									"The stage for decryption is not finished yet, to skip click next or exit!!");
					getView().getNextButton().setText("Go to histograms!");

				} else {
					// load the previous state.
					VisualizationContainerController containerController = (VisualizationContainerController) getParentController();
					containerController.presentNextVisualizationController();
				}
			}

		});

	}

	/**
	 * 
	 */
	public void popupKeyboard() {
		getView().createKeyboard();
		getView().getKeyboard().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JButton source = (JButton) e.getSource();
				getView().getInput().setText(
						getView().getInput().getText() + source.getText());

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
	}

	/**
	 * 
	 */
	public void unloadInOut() {
		this.getView().remove(this.getView().getInOutPanel());
		this.getView().setInOutPanel(null);
		this.getView().revalidate();
		this.getView().repaint();

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
							// standart key for the caesar cipher. +3 when encrypting. -3 when
							// decrypting.
							int key = 0;
							try {
								key = Integer.parseInt(getView().getKey()
										.getText());
								if (getModel().checkValidChar(key,
										userOutput.getName(),
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
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
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

	/**
	 * @return the decryptionPhase
	 */
	public boolean isDecryptionPhase() {
		return decryptionPhase;
	}

}
