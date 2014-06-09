package com.rokid.ev3.gui;

import lejos.hardware.lcd.GraphicsLCD;

/**
 * ButtonBoard a virtual button board for LCD
 * @author Misa.Z
 *
 */
public class ButtonBoard extends View {
	protected int buttonW = 0;
	protected int buttonH = 0;
	protected int space = 4;
	protected int status = -1;
	
	protected String leftLabel = "L";
	protected String rightLabel = "R";
	protected String upLabel = "U";
	protected String downLabel = "D";
	protected String enterLabel = "OK";
	protected String escLabel = "ESC";
	
	protected void onResize() {
		Size size = getSize();
		buttonW = (size.w - 2*space )/3;
		buttonH = (size.h - 2*space )/3;
	}
	
	protected void drawLeft(GraphicsLCD g) {
		boolean invert = false;
		int x = 0;
		int y = buttonH + space;

		if(status == BrickButton.LEFT) {
			fillRect(g, x, y, buttonW-1, buttonH-1);
			invert = true;
		}
		else
			drawRect(g, x, y, buttonW-1, buttonH-1);
		
		drawString(g, leftLabel, x+buttonW/2, y+buttonH/2, 
				GraphicsLCD.HCENTER | GraphicsLCD.VCENTER, invert);
	}
	
	protected void drawRight(GraphicsLCD g) {
		boolean invert = false;
		int x = (buttonW+space)*2;
		int y = buttonH + space;
		
		if(status == BrickButton.RIGHT) {
			fillRect(g, x, y, buttonW-1, buttonH-1);
			invert = true;
		}
		else
			drawRect(g, x, y, buttonW-1, buttonH-1);
		
		drawString(g, rightLabel, x+buttonW/2, y+buttonH/2, 
				GraphicsLCD.HCENTER | GraphicsLCD.VCENTER, invert);
	}
	
	protected void drawUp(GraphicsLCD g) {
		boolean invert = false;
		int x = buttonW + space;
		int y = 0;
		
		if(status == BrickButton.UP) {
			fillRect(g, x, y, buttonW-1, buttonH-1);
			invert = true;
		}
		else
			drawRect(g, x, y, buttonW-1, buttonH-1);
		
		drawString(g, upLabel, x+buttonW/2, y+buttonH/2, 
				GraphicsLCD.HCENTER | GraphicsLCD.VCENTER, invert);
	}
	
	protected void drawEnter(GraphicsLCD g) {
		boolean invert = false;
		int x = buttonW + space;
		int y = buttonH + space;
		
		if(status == BrickButton.ENTER) {
			fillRect(g, x, y, buttonW-1, buttonH-1);
			invert = true;
		}
		else
			drawRect(g, x, y, buttonW-1, buttonH-1);
		
		drawString(g, enterLabel, x+buttonW/2, y+buttonH/2, 
				GraphicsLCD.HCENTER | GraphicsLCD.VCENTER, invert);
	}
	
	protected void drawEsc(GraphicsLCD g) {
		boolean invert = false;
		int x = 0;
		int y = 0;
		
		if(status == BrickButton.ESC) {
			fillRect(g, x, y, buttonW-1, buttonH-1);
			invert = true;
		}
		else
			drawRect(g, x, y, buttonW-1, buttonH-1);
		
		drawString(g, escLabel, x+buttonW/2, y+buttonH/2, 
				GraphicsLCD.HCENTER | GraphicsLCD.VCENTER, invert);
	}
	
	protected void drawDown(GraphicsLCD g) {
		boolean invert = false;
		int x = buttonW + space ;
		int y = (buttonH+space) * 2;
		
		if(status == BrickButton.DOWN) {
			fillRect(g, x, y, buttonW-1, buttonH-1);
			invert = true;
		}
		else
			drawRect(g, x, y, buttonW-1, buttonH-1);
		
		drawString(g, downLabel, x+buttonW/2, y+buttonH/2, 
				GraphicsLCD.HCENTER | GraphicsLCD.VCENTER, invert);
	}
	
	protected void draw(GraphicsLCD g) {
		g.setColor(0, 0, 0);
		g.setFont(font);
		
		drawEsc(g);
		drawUp(g);
		drawLeft(g);
		drawEnter(g);
		drawRight(g);
		drawDown(g);
	}
	
	protected void onButtonPress(Event ev) {
		status = ev.intValue;
	}
	
	protected void onButtonRelease(Event ev) {	
		status = -1;
	}
	
	protected Event onEvent(Event ev) {
		if(ev.type == Event.KEY_PRESS) {
			onButtonPress(ev);
			return null;
		}
		else if(ev.type == Event.KEY_RELEASE) {
			onButtonRelease(ev);
			return null;
		}
		return ev;
	}
}
