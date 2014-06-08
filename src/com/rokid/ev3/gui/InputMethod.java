package com.rokid.ev3.gui;

import java.util.Vector;


/**
 * InputMethod class, Press down ESC and ENTER key same time to switch keyboard.
 */

class Keyboard {
	char[]keys = null;
	int cols = 0;
}

public class InputMethod extends VirtualKeyboard {
	Vector<Keyboard> keyboards = new Vector<Keyboard>();
	int index = 0;

	public void addKeyboard(char []keys, int cols) {
		if(keys == null || keys == null || cols == 0)
			return;
		
		Keyboard kb = new Keyboard();
		kb.keys = keys;
		kb.cols = cols;
		
		keyboards.add(kb);
		if(keyboards.size() == 1) {
			this.setKeys(keys, cols);
		}
	}
	
	protected Event onEvent(Event ev) {
		if(keyboards.size() == 0)
			return ev;
		
		ev = super.onEvent(ev);
		if(ev == null)
			return null;
		
		if(ev.type == Event.KEY_PRESS && 
				ev.intValue == (BrickButton.ESC | BrickButton.ENTER)) {
			int num = keyboards.size();
			
			index++;
			if(index >= num)
				index = 0;
			
			Keyboard kb = keyboards.get(index);
			this.setKeys(kb.keys, kb.cols);
			colIndex = rowIndex = 0;
			return null;
		}
		return ev;
	}

}
