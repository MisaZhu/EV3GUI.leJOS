package com.rokid.ev3.gui;

import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;

/**
 * @author Misa.Z
 *
 */
public class Label extends View {
	String label = "";
	Font font = null;
	
	public Label(String label) {
		font = Font.getDefaultFont();
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
		if(font != null && label != null) {
			resizeTo(font.stringWidth(label), font.getHeight());
		}
	}
	
	/**
	 * Set the font.
	 */
	public void setFont(Font font) {
		this.font = font;
		if(font != null && label != null) {
			resizeTo(font.stringWidth(label), font.getHeight());
		}
	}
	
	protected void draw(GraphicsLCD g) {
		if(font == null || label == null)
			return;
		
		g.setColor(0, 0, 0);
		
		g.setFont(font);
		drawString(g, label, getWidth()/2, getHeight()/2,
				GraphicsLCD.VCENTER | GraphicsLCD.HCENTER);
	}
}