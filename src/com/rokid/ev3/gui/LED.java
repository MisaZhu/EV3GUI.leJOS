package com.rokid.ev3.gui;

public class LED {
	/**
	 * Light the led
	 */
	public void light(int p) {
		lejos.hardware.Button.LEDPattern(p);
	}
}
