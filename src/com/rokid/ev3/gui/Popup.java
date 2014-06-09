package com.rokid.ev3.gui;

import lejos.hardware.lcd.GraphicsLCD;

/**
 * Popup class for popup upon the desktop and catch all keyboard events.
 * @author Misa.Z
 *
 */
public class Popup extends Container {
	public static int NONE = 0;
	public static int FULLSCREEN = 1;
	public static int CENTER = 2;
	
	Desktop desktop = null;
	View lastFocused = null;
	
	/**
	 * Popup and show with fullscreen mode or not.
	 */
	public void popup(int mode) {
		popup(Desktop.getDefault(), mode);
	}
	
	/**
	 * Popup and show
	 */
	public void popup() {
		popup(Desktop.getDefault(), NONE);
	}
	
	/**
	 * Unpop and hide
	 */
	public void close() {
		this.hide();
		if(desktop == null || !desktop.popups.contains(this))
			return;
		
		if(desktop.popups.lastElement() != this)
			return;
		desktop.popups.pop();
	}
	
	/**
	 * Popup upon the specific desktop 
	 */
	public void popup(Desktop desktop, int mode) {
		if(desktop == null)
			return;
		
		this.desktop = desktop;
		if(desktop.popups.contains(this))
			return;
		
		desktop.popups.push(this);
		
		if(mode == FULLSCREEN) {
			this.fullscreen();
		}
		else if(mode == CENTER) {
			this.center();
		}
		
		this.show();
	}
	
	/**
	 * resize to the fullscreen.
	 */
	void fullscreen() {
		Desktop desk = desktop;
		if(desk == null) {
			desk = Desktop.getDefault();
		}
		Size size = desk.getSize();
		moveTo(0, 0, size.w, size.h);
	}
	
	/**
	 * move to the center of desktop.
	 */
	void center() {
		Desktop desk = desktop;
		if(desk == null) {
			desk = Desktop.getDefault();
		}
		
		Size dSize = desk.getSize();
		Size size = getSize();
		
		moveTo((dSize.w-size.w)/2, (dSize.h-size.h)/2);
	}

	protected Event onEvent(Event ev) {
		if(ev.type == Event.KEY_PRESS && ev.intValue == lejos.hardware.Button.ID_ESCAPE) {
			close();
			return null;
		}
		return ev;
	}

	protected void drawFrame(GraphicsLCD g) {
		Size size = getSize();
		int w = size.w;
		int h = size.h;
		
		/*background*/
		g.setColor(255, 255, 255);
		fillRect(g, 0, 0, w-1, h-1);
		
		/*frame*/
		g.setColor(0, 0, 0);
		drawRect(g, 0, 0, w-1, h-1);
		
		/*shadow*/
		g.setStrokeStyle(GraphicsLCD.DOTTED);
		drawLine(g, 3, h, w, h);
		drawLine(g, w, 3, w, h);
		drawLine(g, 4, h+1, w, h+1);
		drawLine(g, w+1, 4, w+1, h);
		g.setStrokeStyle(GraphicsLCD.SOLID);
	}
}
