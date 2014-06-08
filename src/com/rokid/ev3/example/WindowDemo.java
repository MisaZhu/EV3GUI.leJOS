package com.rokid.ev3.example;

import com.rokid.ev3.gui.*;

/**
 * Window Demo
 * @author Misa.Z
 *
 */
public class WindowDemo {
	public static void main(String[] args) {
		Desktop desktop = Desktop.getDefault();
		
		Window w = new Window("window");
		w.setWorkspace(new LayoutCenter());
		
		Container ws = w.getWorkspace();
		ws.addChild(new Label("Window Content"));

		w.popup(Popup.FULLSCREEN);
		
		while (true) {
			desktop.refresh();
			Event ev = desktop.eventHandle();
			if(ev != null && ev.type == Event.KEY_PRESS && ev.intValue == BrickButton.ESC)
				break;
		}
	}

}
