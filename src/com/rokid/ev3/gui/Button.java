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
		if(ev.type == Event.KEY_PRESS && ev.intValue == lejos.hardware.Button.ID_ENTER) {
			return new Event(Event.GUI_PRESS, 0);
		}
		return ev;
	}
	
	protected void draw(GraphicsLCD g) {
		g.setColor(0, 0, 0);
		Size size = getSize();

		drawRoundRect(g, 0, 0, size.w-1, size.h-1, 8, 8);
		
		if(focused()) {
			g.setStrokeStyle(GraphicsLCD.DOTTED);
			drawRoundRect(g, 3, 3, size.w-7, size.h-7, 6, 6);
			g.setStrokeStyle(GraphicsLCD.SOLID);
		}
	}
	
	public Button() {
		alive = true;
	}
}
