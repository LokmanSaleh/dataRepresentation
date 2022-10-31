package com.automl.datarepresentation.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Label;

public class SelectionCriteria extends JFrame{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionCriteria window = new SelectionCriteria();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectionCriteria() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 886, 263);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox accuracy = new JComboBox();
		accuracy.setToolTipText("Select the needed accuracy level");
		accuracy.setBounds(10, 61, 79, 36);
		accuracy.addItem("Low");
		accuracy.addItem("Medium");
		accuracy.addItem("High");
		accuracy.addItem("Very-High");
		frame.getContentPane().add(accuracy);
		
		JLabel lblNewLabel = new JLabel("Accuracy");
		lblNewLabel.setBounds(10, 45, 70, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(107, 61, 70, 36);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Explainable");
		lblNewLabel_1.setBounds(107, 45, 70, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(197, 61, 70, 36);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblNewLabel_2 = new JLabel("Overfitted");
		lblNewLabel_2.setBounds(197, 45, 72, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(284, 61, 70, 36);
		frame.getContentPane().add(comboBox_2);
		
		JLabel lblNbrofhyperpar = new JLabel("NbrOfHyperpar");
		lblNbrofhyperpar.setBounds(282, 45, 85, 14);
		frame.getContentPane().add(lblNbrofhyperpar);
		
		JLabel lblMemory = new JLabel("Memory");
		lblMemory.setBounds(373, 45, 64, 14);
		frame.getContentPane().add(lblMemory);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(373, 61, 70, 36);
		frame.getContentPane().add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(453, 61, 70, 36);
		frame.getContentPane().add(comboBox_4);
		
		JLabel lblComplexity = new JLabel("Complexity");
		lblComplexity.setBounds(451, 45, 64, 14);
		frame.getContentPane().add(lblComplexity);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(535, 61, 70, 36);
		frame.getContentPane().add(comboBox_5);
		
		JLabel lblDeterministic = new JLabel("Deterministic");
		lblDeterministic.setBounds(533, 45, 70, 14);
		frame.getContentPane().add(lblDeterministic);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(617, 61, 70, 36);
		frame.getContentPane().add(comboBox_6);
		
		JLabel lblIncremental = new JLabel("Incremental");
		lblIncremental.setBounds(617, 45, 70, 14);
		frame.getContentPane().add(lblIncremental);
		
		JButton btnNewButton = new JButton("Select ML Algorithm");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(711, 108, 149, 41);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("AutoDetected Criteria");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(10, 121, 112, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblManualSelectionCriteria = new JLabel("ManualSelection Criteria");
		lblManualSelectionCriteria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblManualSelectionCriteria.setBounds(10, 11, 141, 14);
		frame.getContentPane().add(lblManualSelectionCriteria);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(10, 164, 70, 36);
		frame.getContentPane().add(comboBox_7);
		
		JLabel lblLinearModel = new JLabel("Linear model");
		lblLinearModel.setBounds(10, 148, 70, 14);
		frame.getContentPane().add(lblLinearModel);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setBounds(107, 164, 70, 36);
		frame.getContentPane().add(comboBox_8);
		
		JLabel lblDataSize = new JLabel("Data Size ");
		lblDataSize.setBounds(107, 148, 70, 14);
		frame.getContentPane().add(lblDataSize);
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setBounds(197, 164, 79, 36);
		frame.getContentPane().add(comboBox_9);
		
		JLabel lblDataDimension = new JLabel("Data Dimension");
		lblDataDimension.setBounds(197, 148, 85, 14);
		frame.getContentPane().add(lblDataDimension);
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setBounds(292, 164, 70, 36);
		frame.getContentPane().add(comboBox_10);
		
		JLabel lblHighlyCorrelatedAttr = new JLabel("Highly correlated Attr");
		lblHighlyCorrelatedAttr.setBounds(292, 148, 70, 14);
		frame.getContentPane().add(lblHighlyCorrelatedAttr);
		
		JComboBox comboBox_11 = new JComboBox();
		comboBox_11.setBounds(373, 164, 70, 36);
		frame.getContentPane().add(comboBox_11);
		
		JLabel lblMissingValue = new JLabel("Missing value");
		lblMissingValue.setBounds(373, 148, 70, 14);
		frame.getContentPane().add(lblMissingValue);
		
		JComboBox comboBox_12 = new JComboBox();
		comboBox_12.setBounds(453, 164, 70, 36);
		frame.getContentPane().add(comboBox_12);
		
		JLabel lblNoise = new JLabel("Noise");
		lblNoise.setBounds(453, 148, 70, 14);
		frame.getContentPane().add(lblNoise);
		
		JComboBox comboBox_13 = new JComboBox();
		comboBox_13.setBounds(535, 164, 70, 36);
		frame.getContentPane().add(comboBox_13);
		
		JLabel lblImbalancedData = new JLabel("Imbalanced Data");
		lblImbalancedData.setBounds(535, 148, 70, 14);
		frame.getContentPane().add(lblImbalancedData);
		
		frame.setVisible(true);
	}
}
