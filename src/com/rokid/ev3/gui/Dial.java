package com.rokid.ev3.gui;

import lejos.hardware.lcd.GraphicsLCD;

public class Dial extends View {
	protected int graduations = 0;
	protected int read = 0;
	
	public void setGraduations(int num) {
		graduations = num;
	}
	
	/**
	 * @param h 0-360
	 */
	public void read(int v) {
		read = v;
	}
	
	
	protected void draw(GraphicsLCD g) {
		double p = read / 360.0;
		
		Size size = getSize();
		int r = size.w > size.h ? (size.h / 2) : (size.w / 2);
		int x = size.w/2 - 1;
		int y = size.h/2 - 1;
		
		this.drawArc(g, size.w/2 - r, size.h/2 - r, 2*r-1, 2*r-1, 0, 360);
		if(graduations > 0) {
			double av = 360.0 / graduations;
			for(int i=0; i<graduations; ++i) {
				this.drawRadius(g, i*av/360.0, x, y, r-6, r);
			}
		}
		
		this.fillArc(g, x-4, y-4, 8, 8, 0, 360);
		this.drawRadius(g, p, x, y, 0, r-6);
	}
}
