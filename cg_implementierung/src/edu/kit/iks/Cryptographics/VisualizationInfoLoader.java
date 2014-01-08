package edu.kit.iks.Cryptographics;

import java.util.ArrayList;
import java.util.List;

import edu.kit.iks.Cryptographics.Caesar.CaesarVisualizationInfo;
import edu.kit.iks.Cryptographics.DiffieHellman.DHVisualizationInfo;
import edu.kit.iks.Cryptographics.Example.VisualizationInfo;
import edu.kit.iks.Cryptographics.Vigenere.VigenereVisualizationInfo;
import edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo;

/**
 * This class is a registry where all {VisualisationInfo}-objects are 
 * accessible through the static {loadAllVisualizationInfo()}-method.
 * Objects of this class cannot be instantiated
 * 
 * @author Christian Dreher
 */
public class VisualizationInfoLoader {
	
	/**
	 * Private constructor to prevent this class from being instantiated
	 */
	private VisualizationInfoLoader() {
		
	}
	
	/**
	 * Returns a list of all {VisualizationInfo}-objects 
	 * 
	 * @return A list of all {VisualizationInfo}-objects 
	 */
	static public List<AbstractVisualizationInfo> loadAllVisualizationInfos() {
		List<AbstractVisualizationInfo> visualizationInfos = new ArrayList<AbstractVisualizationInfo>();
		visualizationInfos.add(new CaesarVisualizationInfo());
		visualizationInfos.add(new DHVisualizationInfo());
		visualizationInfos.add(new VigenereVisualizationInfo());
		visualizationInfos.add(new VisualizationInfo());
		return visualizationInfos;
	}
}
