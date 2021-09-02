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

import com.automl.algorithmsRepresentation.bean.Algorithm;
import com.automl.algorithmsRepresentation.bean.Interval;
import com.automl.algorithmsRepresentation.bean.TypeOfAlgorithm;
import com.automl.algorithmsRepresentation.bean.parameter.CategorialParameter;
import com.automl.algorithmsRepresentation.bean.parameter.ContinueParameter;
import com.automl.algorithmsRepresentation.bean.parameter.Parameter;
import com.automl.algorithmsRepresentation.bean.selectionCriteria.NumberOfFeatures;
import com.automl.algorithmsRepresentation.bean.selectionCriteria.SelectionCriteria;
import com.automl.algorithmsRepresentation.bean.selectionCriteria.TypeOfData;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Choice;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * Les parametres sont precises à part, à cause de leur utilisation dans l'optimization
 *
 */
public class addAlgorithm {

	private JFrame frame;
	private JTextField algorithmName;
	private JTextField paramMax;
	private JTextField parameterName;
	private JTextField paramMin;
	private JTextField nbrOfAttributMax;
	private JTextField nbrOfAttributMin;
	
	// Algorithm params
	private String name;
	private TreeMap<String, Parameter> parameters = new TreeMap<>();
	private TreeMap<String, SelectionCriteria> selectionCriterias = new TreeMap<>();
	private String execute;
	private TypeOfAlgorithm typeOfAlgorithm ;
	
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
		frame.setBounds(100, 100, 476, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		algorithmName = new JTextField();
		algorithmName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				name = algorithmName.getText();
			}
		});
		algorithmName.setBounds(49, 8, 86, 20);
		frame.getContentPane().add(algorithmName);
		algorithmName.setColumns(10);
		
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
		
		parameterName = new JTextField();
		parameterName.setColumns(10);
		parameterName.setBounds(44, 42, 64, 20);
		panel_1.add(parameterName);
		
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
		
		paramMax = new JTextField();
		paramMax.setBounds(97, 14, 46, 20);
		panel_2.add(paramMax);
		paramMax.setColumns(10);
		
		paramMin = new JTextField();
		paramMin.setBounds(54, 14, 46, 20);
		paramMin.setColumns(10);
		panel_2.add(paramMin);
		
		
		Panel panel_3 = new Panel();
		panel_3.setBounds(10, 10, 152, 26);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JRadioButton rdbtnCateg = new JRadioButton("Categ");
		rdbtnCateg.setBounds(6, 0, 61, 23);
		panel_3.add(rdbtnCateg);
		
		JRadioButton rdbtnCont = new JRadioButton("Cont");
		rdbtnCont.setBounds(69, 0, 61, 23);
		panel_3.add(rdbtnCont);
		
		JButton append = new JButton("Append");
		append.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add the intervals to parameter
				if (rdbtnCateg.isSelected()) {
					if (parameters.get(parameterName.getText()) == null ) {
						Parameter param = new CategorialParameter(parameterName.getText());
						param.addInterval(new Interval(Integer.parseInt(paramMin.getText()), Integer.parseInt(paramMax.getText())));
						parameters.put(parameterName.getText(),param);
					} else {
						parameters.get(parameterName.getText()).addInterval(new Interval(Integer.parseInt(paramMin.getText()), Integer.parseInt(paramMax.getText())));
					}
				} else if (rdbtnCont.isSelected()) {
					if (parameters.get(parameterName.getText()) == null ) {
						Parameter param = new ContinueParameter(parameterName.getText());
						param.addInterval(new Interval(Integer.parseInt(paramMin.getText()), Integer.parseInt(paramMax.getText())));
						parameters.put(parameterName.getText(),param);
					} else {
						parameters.get(parameterName.getText()).addInterval(new Interval(Integer.parseInt(paramMin.getText()), Integer.parseInt(paramMax.getText())));
					}
				}	
			}
		});
		append.setBounds(54, 38, 89, 26);
		panel_2.add(append);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnCateg);
		buttonGroup.add(rdbtnCont);
		
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
		
		Choice lTypeOfAlgorithm = new Choice();
		lTypeOfAlgorithm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				typeOfAlgorithm = new TypeOfAlgorithm(TypeOfAlgorithm.TYPE.valueOf(lTypeOfAlgorithm.getItem(lTypeOfAlgorithm.getSelectedIndex())));
				System.out.println("it work");
			}
		});
		lTypeOfAlgorithm.setBounds(309, 8, 116, 20);
		for (TypeOfAlgorithm.TYPE type : TypeOfAlgorithm.TYPE.values()) 
			lTypeOfAlgorithm.add(type.toString());
		frame.getContentPane().add(lTypeOfAlgorithm);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(10, 280, 438, 109);
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
		
		Choice typeOfData = new Choice();
		typeOfData.setBounds(10, 29, 116, 20);
		for (TypeOfData.TYPE type : TypeOfData.TYPE.values()) 
			typeOfData.add(type.toString());
		panel_6.add(typeOfData);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(189, 21, 152, 71);
		panel_5.add(panel_7);
		
		JLabel label = new JLabel("Interval");
		label.setBounds(10, 34, 56, 26);
		panel_7.add(label);
		
		nbrOfAttributMax = new JTextField();
		nbrOfAttributMax.setColumns(10);
		nbrOfAttributMax.setBounds(97, 37, 46, 20);
		panel_7.add(nbrOfAttributMax);
		
		nbrOfAttributMin = new JTextField();
		nbrOfAttributMin.setColumns(10);
		nbrOfAttributMin.setBounds(54, 37, 46, 20);
		panel_7.add(nbrOfAttributMin);
		
		JCheckBox nbrOfAttribute = new JCheckBox("Nombre D'attributs");
		nbrOfAttribute.setBounds(10, 4, 122, 23);
		panel_7.add(nbrOfAttribute);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(49, 428, -33, 33);
		frame.getContentPane().add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(10, 400, 438, 239);
		frame.getContentPane().add(panel_9);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextArea lExecute = new JTextArea();
		panel_9.add(lExecute);
		
		JButton saveAlgorithm = new JButton("Enregistrer");
		saveAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add selection criteria
				selectionCriterias.put("typeOfData",new TypeOfData(TypeOfData.TYPE.valueOf(typeOfData.getItem(typeOfData.getSelectedIndex()))));
				if (nbrOfAttribute.isSelected()) {
					selectionCriterias.put("numberOfFeatures", new NumberOfFeatures(new Interval(Integer.parseInt(nbrOfAttributMin.getText()), 
																								 Integer.parseInt(nbrOfAttributMax.getText()))));
				}
				
				// Execute 
				execute = lExecute.getText();
				
				// create the algorithm
				Algorithm algorithm = new Algorithm(name, parameters, selectionCriterias, typeOfAlgorithm, execute);
				
				System.out.println(algorithm);
			}
		});
		saveAlgorithm.setBounds(354, 650, 89, 23);
		frame.getContentPane().add(saveAlgorithm);
	}
}
