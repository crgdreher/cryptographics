package edu.kit.iks.Cryptographics.DiffieHellman.Demonstration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import javax.swing.JLabel;
import edu.kit.iks.CryptographicsLib.VisualizationView;

public class ExplainAimView extends VisualizationView {
	
	private JLabel aimExplain;

	private ColorChannel cc;
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 5986978581223106407L;

	public ExplainAimView() {
		// TODO move some stuff out of the constructor into a start method
		super();
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		layout.setConstraints(this.getNextButton(), gbc);
		
		this.aimExplain = new JLabel();
		this.aimExplain.setText("<html><div style=\"width:200px\">Our aim is to exchange a secret on a public channel, " +
				"without Eve getting the secret too. Therefore we can't simply send the secret, as shown" +
				"in the figure above as Eve would get the secret too. Thus we need" +
				"to exchange the secret in a different way</div></html>");
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		this.add(aimExplain, gbc);
		
		this.cc = new ColorChannel(new Dimension(1100, 350), 70);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
//		gbc.fill = GridBagConstraints.BOTH;
		this.add(this.cc, gbc);
		//TODO try not to do work in constructor
		this.cc.setColorNextToSend(Color.BLUE);
		this.cc.setKeepColor(false);
		this.cc.setRepeat(true);
		this.cc.sendToBob(null, false);
	}

	
	public ColorChannel getColorChannel() {
		return this.cc;
	}
	


}
