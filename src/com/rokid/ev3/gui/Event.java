package com.rokid.ev3.gui;

/**
 * 
 * @author Misa.Z
 * Event includes keyboard events and high level GUI event like button press.
 *
 */

public class Event {
	public static int NONE = 0;
	public static int KEY = 1;
	public static int GUI = 2;
	
	public static int PRESS = 0;
	
	public int type;
	public int value;
	
	public Event(int type, int value) {
		set(type, value);
	}
	
	/**
	 * Set the type and value of the event.
	 * @param type Event type
	 * @param value Event value
	 */
	public void set(int type, int value) {
		this.type = type;
		this.value = value;
		
	}
	
	public boolean is(int type, int value) {
		return this.type == type && this.value == value;
	}
}
