package com.rokid.ev3.gui;

import java.util.Iterator;
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
	Container root = null;
	Keyboard kerboard;
	LED led;
	Stack popups = null;
	
	static Desktop desktop = null;

	Desktop(Brick brick) {
		this.g = brick.getGraphicsLCD();
		kerboard = new Keyboard();
		led = new LED();
		popups = new Stack();
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
		if(root != null) {
			root.refresh(g);
		}
		
		Iterator it = popups.iterator();
		while(it.hasNext()) {
			Popup popup = (Popup)it.next();
			if(popup != null)
				popup.refresh(g);
		}
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
			focused = root.getFocused();
			if(focused != null)
				ev = focused.eventHandle(ev);
		}
		return ev;
	}
}
