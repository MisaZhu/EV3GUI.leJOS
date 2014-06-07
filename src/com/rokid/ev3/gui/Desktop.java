package com.rokid.ev3.gui;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;

/**
 * Desktop is the base GUI for the whole LCD.
 * @author Misa.Z
 *
 */
public class Desktop {
	GraphicsLCD g;
	Brick brick;
	Container root = null;
	Keyboard kerboard;
	LED led;
	
	static Desktop desktop = null;
	static View focusedView = null;
	
	/**
	 * Set view as focused, so it can catch the keyboard event.
	 * @param v view will be focused.
	 */
	public static void focus(View v) {
		focusedView = v;
	}

	Desktop(Brick brick) {
		this.g = brick.getGraphicsLCD();
		kerboard = new Keyboard();
		led = new LED();
	}
	
	/**
	 * Get the default desktop instance of local brick.
	 */
	public static Desktop  getDefault() {
		if(desktop == null)
			desktop = new Desktop(BrickFinder.getDefault());
		
		return 	desktop;
	}
	
	/**
	 * Get the LED instance of local brick.
	 */
	public LED getLED() {
		return led;
	}
	
	/**
	 * Get the keyboard instance of local brick.
	 */
	public Keyboard getKeyboard() {
		return this.kerboard;
	}
	
	/**
	 * Set the root view of this desktop.
	 */
	public void setRoot(Container c) {
		root = c;
		root.resizeTo(getWidth(), getHeight());
		root.focus();
	}
	
	/**
	 * Refresh the desktop , will make all GUI views redraw.
	 */
	public void refresh() {
		g.clear();
		if(root != null)
			root.refresh(g);
	}
	
	/**
	 * Get root view of this desktop.
	 */
	public Container getRoot() {
		return root;
	}
	
	public int getWidth() {
		return g.getWidth();
	}
	
	public int getHeight() {
		return g.getHeight();
	}
	
	/**
	 * Catch and handle the event, includes Keyboard and GUI.
	 */
	public Event eventHandle() {
		int k = desktop.getKeyboard().waitForAnyEvent(); 	
		Event ev = new Event(Event.KEY, k);
		
		if(Desktop.focusedView != null) {
			ev = Desktop.focusedView.eventHandle(ev);
		}
		
		return ev;
	}
}
