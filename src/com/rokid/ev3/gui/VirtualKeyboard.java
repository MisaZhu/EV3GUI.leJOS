package com.rokid.ev3.gui;

import lejos.hardware.lcd.GraphicsLCD;

/**
 * VirtualKeyboard, press down LEFT&ENTER for delete; RIGHT&ENTER for enter
 * @author Misa.Z
 *
 */
public class VirtualKeyboard extends View {
	public static char ENTER = 10;
	public static char DEL = 8;
	public static char ESC = 27;

	protected char keys[] = null;
	protected int cols = 0;
	protected int rows = 0;
	protected int colIndex = 0;
	protected int rowIndex = 0;
	
	public void setKeys(char keys[], int cols) {
		this.keys = keys;
		if(keys == null || keys.length == 0 || cols == 0) {
			cols = rows = 0;
			return;
		}
		
		if(keys.length < cols)
			cols = keys.length;
		
		this.cols = cols;
		rows = (keys.length / cols) + ((keys.length % cols) == 0 ? 0 : 1);
	}
	
	protected Event onEvent(Event ev) {
		if(keys == null || cols == 0 || rows == 0)
			return ev;
		
		int i;
		if(ev.type == Event.KEY_PRESS) {
			if(ev.intValue == BrickButton.DOWN) {
				rowIndex++;
				i = (cols * rowIndex) + colIndex;
				if(rowIndex >= rows || 
						i >= keys.length) {
					rowIndex = 0;
				}
				return null;
			}
			else if(ev.intValue == BrickButton.UP) {
				rowIndex--;
				if(rowIndex  < 0) {
					rowIndex = rows > 0 ? (rows - 1) : 0;
				}

				return null;
			}
			else if(ev.intValue == BrickButton.RIGHT) {
				colIndex++;
				i = (cols * rowIndex) + colIndex;
				if(colIndex >= cols ||
						i >= keys.length) {
					colIndex = 0;
				}

				return null;
			}
			else if(ev.intValue == BrickButton.LEFT) {
				colIndex--;
				if(colIndex  < 0) {
					if(rowIndex == (rows - 1)) {
						colIndex = keys.length % cols;
						if(colIndex > 0)
							colIndex--;
					}
					else
						colIndex = cols > 0 ? (cols - 1) : 0;
				}

				return null;
			}
			else if(ev.intValue == BrickButton.ENTER) {
				i = (cols * rowIndex) + colIndex;
				if(i >= 0 && i < keys.length) {
					ev = new Event(Event.KEYIN, keys[i]);
				}
			}
			else if(ev.intValue == BrickButton.ESC) {
				ev = new Event(Event.KEYIN, VirtualKeyboard.ESC);
			}
			else if(ev.intValue == (BrickButton.LEFT | BrickButton.ENTER)) {
				ev = new Event(Event.KEYIN, VirtualKeyboard.DEL);
			}
			else if(ev.intValue == (BrickButton.RIGHT | BrickButton.ENTER)) {
				ev = new Event(Event.KEYIN, VirtualKeyboard.ENTER);
			}
		}
		return ev;
	}
	
	String  getKeySymbal(char key) {
		String t = "";
		if(key > 32) {
			t = String.valueOf(key);
		}
		if(key == ESC) {
			t = "ESC";
		}
		else if(key == ENTER) {
			t = "ENT";
		}
		else if(key == DEL) {
			t = "DEL";
		}
		return t;
	}
	
	protected void drawKey(GraphicsLCD g, int x, int y, int w, int h, char key, boolean on) {
		String t = getKeySymbal(key);

		g.setFont(font);
		if(on) {
			fillRect(g, x, y, w-1, h-1);
			drawString(g, t, x+w/2, y+h/2, GraphicsLCD.VCENTER | GraphicsLCD.HCENTER, true);
		}
		else {
			drawRect(g, x, y, w-1, h-1);
			drawString(g, t, x+w/2, y+h/2, GraphicsLCD.VCENTER | GraphicsLCD.HCENTER);
		}
	}
	
	protected void draw(GraphicsLCD g) {	
		if(cols == 0 || rows == 0 || keys == null)
			return;
		
		g.setColor(0);
		
		Size size = getSize();
		int kw = size.w / cols;
		int kh = size.h / rows;
		
		for(int y = 0; y < rows; ++y) {
			for(int x = 0; x < cols; ++x) {
				int i = (cols * y) + x;
				if(i >= keys.length)
					break;
				
				drawKey(g, x*kw, y*kh, kw, kh, keys[i],
						(colIndex == x && rowIndex == y));
			}
		}
	}
}
