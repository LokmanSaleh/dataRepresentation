package com.automl.visualizationTest;

import java.awt.Checkbox;

import javax.swing.JCheckBox;

public class test {
	private String a ="fada";
	private String b = "dasA";
	private JCheckBox checkBox = new JCheckBox("hello");
	/**
	 * @return the a
	 */
	public String getA() {
		return a;
	}
	/**
	 * @param a the a to set
	 */
	public void setA(String a) {
		this.a = a;
	}
	/**
	 * @return the b
	 */
	public String getB() {
		return b;
	}
	/**
	 * @param b the b to set
	 */
	public void setB(String b) {
		this.b = b;
	}
	@Override
	public String toString() {
		return "a=" + a + ", \n b="  + checkBox.toString();
	}
	
	
}
