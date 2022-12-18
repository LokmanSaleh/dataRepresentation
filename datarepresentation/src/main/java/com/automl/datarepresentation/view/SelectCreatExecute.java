package com.automl.datarepresentation.view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import com.automl.datarepresentation.bean.ComboItem;
import com.automl.datarepresentation.bean.StaticPaths; 

import javax.swing.JComboBox; 

public class SelectCreatExecute extends JFrame {

	private JPanel contentPane;
	private JButton jButtonSave;
	private JButton jButtonExecute;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectCreatExecute frame = new SelectCreatExecute();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectCreatExecute() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 287, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelectionCriteria();
			}
		});
		btnNewButton.setBounds(10, 10, 99, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().open(new File(StaticPaths.PATH_OF_BPMN_FILE));
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_1.setBounds(10, 73, 99, 37);
		contentPane.add(btnNewButton_1);
		
		jButtonExecute = new JButton("Execute");
		jButtonExecute.setBounds(64, 222, 99, 37);
		contentPane.add(jButtonExecute);
		
		jButtonSave= new JButton("Save");		
		jButtonSave.setBounds(128, 73, 99, 37);
		contentPane.add(jButtonSave);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 65, 281, 14);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 120, 281, 14);
		contentPane.add(separator_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 129, 171, 37);
		
		comboBox.addItem(new ComboItem("Visible String 1", "Value 1"));
		comboBox.addItem(new ComboItem("Visible String 2", "Value 2"));
		comboBox.addItem(new ComboItem("Visible String 3", "Value 3"));
		
		contentPane.add(comboBox);
		
	}

	public JButton getjButtonSave() {
		return jButtonSave;
	}

	public JButton getjButtonExecute() {
		return jButtonExecute;
	}
}
