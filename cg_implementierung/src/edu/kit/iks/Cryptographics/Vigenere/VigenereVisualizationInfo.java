package edu.kit.iks.Cryptographics.Vigenere;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import edu.kit.iks.Cryptographics.Vigenere.Demonstration.*;
import edu.kit.iks.Cryptographics.Vigenere.Experiment.FirstExperimentController;
import edu.kit.iks.Cryptographics.Vigenere.Experiment.SecondExperimentController;
import edu.kit.iks.Cryptographics.Vigenere.Explanation.FirstExplanationController;
import edu.kit.iks.Cryptographics.Vigenere.Explanation.SecondExplanationController;
import edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo;
import edu.kit.iks.CryptographicsLib.InformationController;
import edu.kit.iks.CryptographicsLib.VisualizationDifficulty;

public class VigenereVisualizationInfo extends AbstractVisualizationInfo {
	private Element vigenereResources;
	
	public VigenereVisualizationInfo() {
		SAXBuilder saxBuilder = new SAXBuilder();

		// obtain file object
		File file = new File(
				"./resources/vigenere/VigenereResources.xml");

		try {
			// converted file to document object
			Document document = saxBuilder.build(file);

			// get root node from xml
			this.vigenereResources = document.getRootElement().getChild("vigenere");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getId() {
		return "vigenere";
	}
	
	public String getName() {
		return "Vigenère";
	}

	public String getDescription() {
		return "";
	}
	
	public String getQRCodeContent() {
		return "";
	}

	public float getTimelineOffset() {
		return 0.33f;
	}

	public VisualizationDifficulty getDifficulty() {
		return VisualizationDifficulty.MEDIUM;
	}
	
	
	/* gets the year of the cryptographic algorithm
	 * @see edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo#getYear()
	 */
	public int getYear() {
		return 1600; //inaccurate
	}
	
	public List<Class> getControllerClasses() {
		List<Class> controllerClasses = new ArrayList<Class>();
		controllerClasses.add(FirstDemonstrationController.class);
		controllerClasses.add(SecondDemonstrationController.class);
		controllerClasses.add(ThirdDemonstrationController.class);
		controllerClasses.add(FirstExperimentController.class);
		controllerClasses.add(SecondExperimentController.class);
		controllerClasses.add(FirstExplanationController.class);
		controllerClasses.add(SecondExplanationController.class);
		controllerClasses.add(InformationController.class);
		return controllerClasses;
	}
	
	public Element getResources() {
		return vigenereResources;
	}
}
