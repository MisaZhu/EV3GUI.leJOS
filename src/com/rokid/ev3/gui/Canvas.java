package com.rokid.ev3.gui;

import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.Image;

public abstract class Canvas {
	protected Rect rect = null;
	protected Font font = Font.getSmallFont();
	
	/**
	 * Set the font.
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	Canvas() {
		rect = new Rect();
	}

	/**
	 * Get the absolute x according to the LCD left/top
	 */
	protected abstract int getAX();

	/**
	 * Get the absolute y according to the LCD left/top
	 */
	protected abstract int getAY();
	
	/**
	 * Get the absolute rectangle according to the LCD
	 */
	protected Rect getARect() {
		int ax = getAX();
		int ay = getAY();
		return new Rect(ax, ay, rect.width, rect.height);
	}
	
	public Size getSize() {
		return new Size(rect.width, rect.height);
	}
	
	protected void onResize() {
	}
	
	protected void onMove() {
	}
	
	void resizeToLayout(int w, int h) {
		rect.width = w;
		rect.height = h;
		
		onResize();
	}
	
	void moveToLayout(int x, int y) {
		rect.x = x;
		rect.y = y;
		
		onMove();
	}
	
	public void resizeTo(int w, int h) {
		rect.width = w;
		rect.height = h;
		
		onResize();
	}
	
	public void moveTo(int x, int y) {
		rect.x = x;
		rect.y = y;
		
		onMove();
	}
	
	public void moveTo(int x, int y, int w, int h) {
		moveTo(x, y);
		resizeTo(w, h);
	}
	
	public abstract void refresh(GraphicsLCD g);
	
	/**
	 * Draw functions in inside coordinate system
	 */
	protected void drawString(GraphicsLCD g, String str, int x, int y, int anchor) {
		drawString(g, str, x, y, anchor, false);
	}
	

	protected void drawString(GraphicsLCD g, String str, int x, int y, int anchor, boolean invert) {
		/*something wrong with anchor VCENTER in this version of leJOS, so I do ugly way*/
		if((anchor & GraphicsLCD.VCENTER) != 0) {
			y -= g.getFont().getHeight() / 2;
		}
		g.drawString(str, x+getAX(), y+getAY(), anchor, invert);
	}
	protected void drawRoundRect(GraphicsLCD g, int x, int y, int w, int h, int arcWidth, int arcHeight) {
		g.drawRoundRect(x+getAX(), y+getAY(), w, h, arcWidth, arcHeight);
	}
	
	protected void drawRect(GraphicsLCD g, int x, int y, int w, int h) {
		g.drawRect(x+getAX(), y+getAY(), w, h);
	}

	protected void fillRect(GraphicsLCD g, int x, int y, int w, int h) {
		g.fillRect(x+getAX(), y+getAY(), w, h);
	}
	
	protected void drawArc(GraphicsLCD g, int x, int y, int w, int h, int startAngle, int arcAngle) {
		g.drawArc(x+getAX(), y+getAY(), w, h, startAngle, arcAngle);
	}
	
	protected void fillArc(GraphicsLCD g, int x, int y, int w, int h, int startAngle, int arcAngle) {
		g.fillArc(x+getAX(), y+getAY(), w, h, startAngle, arcAngle);
	}
	
	protected void drawImage(GraphicsLCD g, Image img, int x, int y, int anchor) {
		g.drawImage(img, x+getAX(), y+getAY(), anchor);
	}

	protected void drawLine(GraphicsLCD g, int x0, int y0, int x1, int y1) {
		int ax = getAX();
		int ay = getAY();
		
		g.drawLine(x0+ax, y0+ay, x1+ax, y1+ay);
	}
	
	protected void drawRadius(GraphicsLCD g, double percent, int x, int y,
            int minRadius, int maxRadius) {
		//... percent parameter is the fraction (0.0 - 1.0) of the way
		//    clockwise from 12.   Because the Graphics2D methods use radians
		//    counterclockwise from 3, a little conversion is necessary.
		//    It took a little experimentation to get this right.
		x += getAX();
		y += getAY();
		
		double radians = (0.5 - percent) * Math.PI * 2;
		double sine   = Math.sin(radians);
		double cosine = Math.cos(radians);
		
		int dxmin = x + (int)(minRadius * sine);
		int dymin = y + (int)(minRadius * cosine);
		
		int dxmax = x + (int)(maxRadius * sine);
		int dymax = y + (int)(maxRadius * cosine);
		g.drawLine(dxmin, dymin, dxmax, dymax);
	}
}
