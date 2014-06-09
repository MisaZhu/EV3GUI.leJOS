package com.rokid.ev3.gui;

import java.util.Stack;

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
	View root = null;
	BrickButton kerboard;
	LED led;
	Stack<View> popups = null;
	
	static Desktop desktop = null;

	Desktop(Brick brick) {
		this.g = brick.getGraphicsLCD();
		kerboard = new BrickButton();
		led = new LED();
		popups = new Stack<View>();
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
	public BrickButton getKeyboard() {
		return this.kerboard;
	}
	
	/**
	 * Set the root view of this desktop.
	 */
	public void setRoot(View v) {
		root = v;
		Size size = getSize();
		root.resizeTo(size.w, size.h);
		root.focus();
	}
	
	/**
	 * Refresh the desktop , will make all GUI views redraw.
	 */
	public void refresh() {
		g.clear();
		if(root != null) {
			root.refresh(g);
		}
		
		for(View popup: popups) {
			if(popup != null)
				popup.refresh(g);
		}
	}
	
	/**
	 * Get root view of this desktop.
	 */
	public View getRoot() {
		return root;
	}
	
	public Size getSize() {
		return new Size(g.getWidth(), g.getHeight());
	}
	
	/**
	 * Catch and handle the event, includes Keyboard and GUI.
	 */
	public Event eventHandle() {
		Event ev = desktop.getKeyboard().waitForAnyEvent(); 	
		if(ev == null)
			return null;
		
		View focused = null;
		
		if(popups.size() > 0) {
			Popup popup = (Popup)popups.lastElement();
			if(popup != null) {
				focused = popup.getFocused();
				if(focused != null)
					ev = focused.eventHandle(ev);
			}
		}
		if(ev != null && root != null) {
			if(root instanceof Container)
				focused = ((Container)root).getFocused();
			else 
				focused = root;
			
			if(focused != null) {
				ev = focused.eventHandle(ev);
			}
		}
		return ev;
	}
}
