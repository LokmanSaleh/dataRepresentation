package com.automl.algorithmsRepresentation.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Choice;

/**
 * 
 * Les parametres sont precises à part, à cause de leur utilisation dans l'optimization
 *
 */
public class addAlgorithm {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addAlgorithm window = new addAlgorithm();
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
	public addAlgorithm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1031, 787);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("name :");
		lblNewLabel.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(49, 8, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 36, 438, 233);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 25, 222, 191);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(44, 42, 64, 20);
		panel_1.add(textField_2);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 45, 29, 14);
		panel_1.add(lblName);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 73, 152, 71);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Interval");
		lblNewLabel_2.setBounds(10, 11, 56, 26);
		panel_2.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(97, 14, 46, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(54, 14, 46, 20);
		textField_3.setColumns(10);
		panel_2.add(textField_3);
		
		JButton btnNewButton = new JButton("Append");
		btnNewButton.setBounds(54, 38, 89, 26);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBounds(141, 155, 71, 23);
		panel_1.add(btnNewButton_1);
		
		Panel panel_3 = new Panel();
		panel_3.setBounds(10, 10, 152, 26);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JRadioButton rdbtnCateg = new JRadioButton("Categ");
		rdbtnCateg.setBounds(6, 0, 61, 23);
		panel_3.add(rdbtnCateg);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Cont");
		rdbtnNewRadioButton.setBounds(69, 0, 61, 23);
		panel_3.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_1 = new JLabel("Parameters à optimiser");
		lblNewLabel_1.setBounds(10, 0, 185, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(242, 25, 185, 191);
		panel.add(panel_4);
		
		JLabel lblTypeDalgorithme = new JLabel("Type D'algorithme :");
		lblTypeDalgorithme.setBounds(209, 11, 99, 14);
		frame.getContentPane().add(lblTypeDalgorithme);
		
		Choice choice = new Choice();
		choice.setBounds(309, 8, 116, 20);
		frame.getContentPane().add(choice);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(10, 280, 438, 137);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblCriteresDeSelection = new JLabel("Criteres de selection");
		lblCriteresDeSelection.setBounds(10, 11, 110, 14);
		panel_5.add(lblCriteresDeSelection);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(10, 33, 169, 59);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Type de donnees");
		lblNewLabel_3.setBounds(10, 11, 89, 14);
		panel_6.add(lblNewLabel_3);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(10, 29, 116, 20);
		panel_6.add(choice_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(189, 21, 152, 71);
		panel_5.add(panel_7);
		
		JLabel label = new JLabel("Interval");
		label.setBounds(10, 34, 56, 26);
		panel_7.add(label);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(97, 37, 46, 20);
		panel_7.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(54, 37, 46, 20);
		panel_7.add(textField_5);
		
		JLabel lblNombreDattributs = new JLabel("Nombre D'attributs");
		lblNombreDattributs.setBounds(10, 11, 110, 14);
		panel_7.add(lblNombreDattributs);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(49, 428, -33, 33);
		frame.getContentPane().add(panel_8);
	}
}
