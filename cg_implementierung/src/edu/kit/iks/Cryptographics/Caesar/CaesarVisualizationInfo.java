package edu.kit.iks.Cryptographics.Caesar;

import java.util.ArrayList;
import java.util.List;

import org.xnap.commons.i18n.I18n;

import edu.kit.iks.Cryptographics.Caesar.Demonstration.CryptoDemonstrationController;
import edu.kit.iks.Cryptographics.Caesar.Demonstration.IntroductionController;
import edu.kit.iks.Cryptographics.Caesar.Experiment.CryptoExperimentController;
import edu.kit.iks.Cryptographics.Caesar.Experiment.HistogramController;
import edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo;
import edu.kit.iks.CryptographicsLib.Configuration;
import edu.kit.iks.CryptographicsLib.InformationController;
import edu.kit.iks.CryptographicsLib.VisualizationDifficulty;

/**
 * @author Wasilij Beskorovajnov.
 * 
 */
public class CaesarVisualizationInfo extends AbstractVisualizationInfo {

	/**
	 * Localization instance
	 */
	private static I18n i18n = Configuration.getInstance().getI18n(
			CaesarVisualizationInfo.class);

	/**
	 * Constructor for the visualizationInfo.
	 */
	public CaesarVisualizationInfo() {
	
	}

	/*
	 * @see edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo#getControllerClasses ()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getControllerClasses() {
		List<Class> controllerClasses = new ArrayList<Class>();

		controllerClasses.add(IntroductionController.class);
		controllerClasses.add(CryptoDemonstrationController.class);
		controllerClasses.add(CryptoExperimentController.class);
		controllerClasses.add(HistogramController.class);
		controllerClasses.add(InformationController.class);

		return controllerClasses;
	}

	/*
	 * @see edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo#getDescription()
	 */
	@Override
	public String getDescription() {
		String description = CaesarVisualizationInfo.i18n
				.tr("Get to know how Ceasar fooled his enemys that "
						+ "intercepted his orders and see one of the oldest "
						+ "attempts to make confidential material unreadable "
						+ "for undesireable readers");

		return description;
	}

	/*
	 * @see edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo#getDifficulty()
	 */
	@Override
	public VisualizationDifficulty getDifficulty() {
		return VisualizationDifficulty.EASY;
	}

	/*
	 * @see edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo#getId()
	 */
	@Override
	public String getId() {
		return "caesar";
	}

	/*
	 * @see edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo#getName()
	 */
	@Override
	public String getName() {
		return CaesarVisualizationInfo.i18n.tr("Caesar");
	}

	/*
	 * @see edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo#getQRCodeContent ()
	 */
	@Override
	public String getQRCodeContent() {
		return i18n.tr("http://en.wikipedia.org/wiki/Caesar_cipher");
	}

	/*
	 * @see edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo#getTimelineOffset ()
	 */
	@Override
	public float getTimelineOffset() {
		return 0.05f;
	}

	/*
	 * @see edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo#getYear()
	 */
	@Override
	public int getYear() {
		// when getYear() negative then it's the year B.C.
		// The year is an estimation!!!
		return -70;
	}

}