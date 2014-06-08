package com.rokid.ev3.gui;

/**
 * 
 * @author Misa.Z
 * Event includes keyboard events and high level GUI event like button press.
 *
 */

public class Event {
	public static int NONE = 0;
	public static int KEY_PRESS = 1;
	public static int KEY_RELEASE = 2;
	
	public static int GUI_PRESS;
	public static int KEYIN = 4;
	public static int INPUT = 5;
	
	public int type;
	public int intValue;
	public Object objValue = null;
	
	public Event(int type, int value) {
		set(type, value);
	}
	
	public Event(int type, Object value) {
		set(type, value);
	}
	
	/**
	 * Set the type and value of the event.
	 * @param type Event type
	 * @param value Event value
	 */
	public void set(int type, int value) {
		this.type = type;
		this.intValue = value;
		
	}
	
	/**
	 * Set the type and value of the event.
	 * @param type Event type
	 * @param value Event value
	 */
	public void set(int type, Object value) {
		this.type = type;
		this.objValue = value;
		
	}

	public boolean is(int type, int value) {
		return this.type == type && this.intValue == value;
	}
}
