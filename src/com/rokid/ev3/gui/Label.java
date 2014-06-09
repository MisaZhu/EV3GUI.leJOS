package com.rokid.ev3.gui;

import lejos.hardware.lcd.GraphicsLCD;

/**
 * @author Misa.Z
 *
 */
public class Label extends View {
	String label = "";
	
	public Label(String label) {
		setLabel(label);
	}
	
	public String getLabel() {
		return label;
	}
	
	/**
	 * Set the label string.
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Get the width of string width current font
	 */
	public int getStringW() {
		if(font == null || label == null)
			return 0;
		return font.stringWidth(label);
	}
	
	/**
	 * Get the width of string heigth current font
	 */
	public int getStringH() {
		if(font == null || label == null)
			return 0;
		return font.getHeight();
	}
	
	protected void draw(GraphicsLCD g) {
		if(font == null || label == null)
			return;
		Size size = getSize();
		
		g.setColor(0, 0, 0);
		
		g.setFont(font);
		drawString(g, label, size.w/2, size.h/2,
				GraphicsLCD.VCENTER | GraphicsLCD.HCENTER);
	}
}
