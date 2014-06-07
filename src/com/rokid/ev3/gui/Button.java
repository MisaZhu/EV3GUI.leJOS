package com.rokid.ev3.gui;

import lejos.hardware.lcd.GraphicsLCD;

/**
 * 
 * @author Misa.Z
 * Class Button for GUI button. when it's on focus and get a Enter key pressed, 
 * it will send a GUI PRESS event.
 *
 */

public class Button extends View {
	protected Event onEvent(Event ev) {
		if(ev.type == Event.KEY && ev.value == lejos.hardware.Button.ID_ENTER) {
			return new Event(Event.GUI, Event.PRESS);
		}
		return ev;
	}
	
	protected void draw(GraphicsLCD g) {
		/*Rect ar = getARect();
		g.setColor(0, 0, 0);

		g.drawRoundRect(ar.x, ar.y, ar.width, ar.height, 8, 8);
		
		if(focused()) {
			g.drawRoundRect(ar.x+3, ar.y+3, ar.width-6, ar.height-6, 6, 6);
		}*/
		
		g.setColor(0, 0, 0);
		int w = getWidth();
		int h = getHeight();

		drawRoundRect(g, 0, 0, w, h, 8, 8);
		
		if(focused()) {
			drawRoundRect(g, 3, 3, w-6, h-6, 6, 6);
		}
	}
	
	public Button() {
		alive = true;
	}
}
