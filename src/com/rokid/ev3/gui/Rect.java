package com.rokid.ev3.gui;

/**
 * 
 * @author Misa.Z
 * Rectangle with x, y (left top) and width, height.
 *
 */
public class Rect {
	public int x, y, width, height;
	
	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Rect() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}
}
