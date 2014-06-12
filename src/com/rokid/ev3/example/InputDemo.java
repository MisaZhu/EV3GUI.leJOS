package com.rokid.ev3.example;

import lejos.hardware.lcd.Font;

import com.rokid.ev3.gui.*;

class InputHandler implements EventHandler {
	Label label = null;
	public Event handle(View view, Event ev) {
		if (ev.type == Event.KEYIN && label != null) {
			String s = label.getLabel();
			int len = s.length();
			
			if(ev.intValue > 31)
				label.setLabel(s += (char)ev.intValue);
			else if(ev.intValue == VirtualKeyboard.DEL) {
				if(len >= 1)
					label.setLabel(s.substring(0, len - 1));
				else
					label.setLabel("");
			}
		}
		return ev;
	}
}

/**
 * InputMethod demo
 * @author Misa.Z
 */
public class InputDemo {
		public static void main(String[] args) {
			Desktop desktop = Desktop.getDefault();
			LayoutV layout = new LayoutV();
			desktop.setRoot(layout);
			Label label = new Label("");

			InputMethod keyboard = new InputMethod();
			//keyboard.addKeyboard("abcdefghijklmnopqrstuvwxyz@._()".toCharArray(), 8);
			keyboard.addKeyboard("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.@_-*?".toCharArray(), 10);
			keyboard.addKeyboard("123456789-0.".toCharArray(), 3);
			keyboard.addKeyboard("@.-=_+,'\"?/\\<>|(){}[]!#$%&*".toCharArray(), 8);
			InputHandler h = new InputHandler();
			h.label = label;
			keyboard.setHandler(h);

			label.setFont(Font.getDefaultFont());
			label.resizeTo(20,  40);
			label.fixedLayout();
			layout.addChild(label);
			layout.addChild(keyboard);
			keyboard.focus();
			
			while(true) {
				/*Flush all the view elements to the LCD*/
				desktop.refresh();
				
				/*Waiting for any button pressed event.*/
				desktop.eventHandle();
			}
	}
}
