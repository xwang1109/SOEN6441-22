package views.map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import controllers.map.*;
import java.awt.GridLayout;

public class NewContinentView extends JFrame {
	private JTextField continentNameTextField;
	private JTextField continentValueTextField;

	
	public NewContinentView() {
		
		this.setSize(450,220);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel continentNameLabel = new JLabel("Continent Name");
		JLabel continentValueLabel = new JLabel("Continent Value");
		JButton saveContinentButton = new JButton("Save");
		
		saveContinentButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		saveContinentButton.addActionListener(new ContinentController(this));
		
		continentNameTextField = new JTextField();
		continentNameTextField.setColumns(10);
		
		continentValueTextField = new JTextField();
		continentValueTextField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(62)
					.addComponent(continentNameLabel)
					.addGap(5)
					.addComponent(continentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(continentValueLabel))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(125)
					.addComponent(continentValueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(saveContinentButton))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(continentNameLabel))
						.addComponent(continentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(continentValueLabel)))
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(continentValueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(saveContinentButton)))
		);
		panel.setLayout(gl_panel);
		
	}


	public String getContinentName() {
		return this.continentNameTextField.getText();
	}
	
	public int getContinentValue() {
		int continentValue = Integer.parseInt(this.continentValueTextField.getText());
		return continentValue;
	}


}
