package com.rokid.ev3.gui;

import lejos.hardware.Button;

public class Keyboard {
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
	public int waitForAnyEvent() {
		return Button.waitForAnyEvent();
	}
}
