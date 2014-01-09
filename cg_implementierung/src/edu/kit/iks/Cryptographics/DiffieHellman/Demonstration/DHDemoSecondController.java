package edu.kit.iks.Cryptographics.DiffieHellman.Demonstration;

import edu.kit.iks.Cryptographics.DiffieHellman.Model;
import edu.kit.iks.CryptographicsLib.AbstractVisualizationController;
import edu.kit.iks.CryptographicsLib.AbstractVisualizationInfo;

public class DHDemoSecondController extends AbstractVisualizationController {
	private Model dhModel;
	private DHDemoSecondView view;
	public DHDemoSecondController(AbstractVisualizationInfo visualizationInfo) {
		super(visualizationInfo);
		dhModel = Model.getInstance();
		view = new DHDemoSecondView();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadView() {
		// TODO Auto-generated method stub

	}

}
