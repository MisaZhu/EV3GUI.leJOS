package com.rokid.ev3.gui;

import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.Image;

public abstract class Canvas {
	protected Rect rect = null;

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
	
	public int getWidth() {
		return rect.width;
	}
	
	public int getHeight() {
		return rect.height;
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
		g.drawString(str, x+getAX(), y+getAY(), anchor);
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
}
