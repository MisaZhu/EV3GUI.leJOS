package com.rokid.ev3.gui;

import lejos.hardware.Button;

public class BrickButton {
	public static int LEFT = Button.ID_LEFT;
	public static int RIGHT = Button.ID_RIGHT;
	public static int UP = Button.ID_UP;
	public static int DOWN = Button.ID_DOWN;
	public static int ENTER = Button.ID_ENTER;
	public static int ESC = Button.ID_ESCAPE;
	
	/**
	 * Wait for any press. it will block the current thread.
	 * @return key value of the keyboard, can be multi-keys.
	 */
	public Event waitForAnyEvent() {
		int k = Button.waitForAnyEvent();
		if((k & 0xFF00) != 0) {
			return new Event(Event.KEY_RELEASE, (k>>8) & 0x0FF);	
		}
		
		return new Event(Event.KEY_PRESS, k);	
	}
}
