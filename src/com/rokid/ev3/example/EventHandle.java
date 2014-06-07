package com.rokid.ev3.example;

import lejos.hardware.lcd.Font;

import com.rokid.ev3.gui.*;

class EHandler implements EventHandler {
	Desktop desktop;
	
	EHandler(Desktop desktop) {
		this.desktop = desktop;
	}

	public Event handle(View view, Event ev) {
		if (ev.type == Event.GUI && ev.value == Event.PRESS) {
			if (view.getName() == "quit") {
				Demo.stopped = true;
			} else if (view.getName() == "light") {
				Demo.light++;
				if (Demo.light >= 10)
					Demo.light = 0;
				desktop.getLED().light(Demo.light);
			}
		}
		return ev;
	}
}

public class EventHandle {
	public static int light = 0;
	public static boolean stopped = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Desktop desktop = Desktop.getDefault();
		Window w = new Window("window");
		desktop.setRoot(w);
		w.setWorkspace(new LayoutV());
		Container ws = w.getWorkspace();

		EHandler handler = new EHandler(desktop);

		LabelButton v2 = new LabelButton("Quit");
		v2.setFont(Font.getSmallFont());
		v2.setName("quit");
		ws.addChild(v2);
		v2.setHandler(handler);

		ws.addChild(new Space(10));

		Button v3 = new LabelButton("Light");
		v3.setName("light");
		ws.addChild(v3);
		v3.focus();
		v3.setHandler(handler);

		Label v4 = new Label("Light 0");
		ws.addChild(v4);

		while (!stopped) {
			v4.setLabel("light " + light);
			desktop.refresh();
			Event ev = desktop.eventHandle();
			if(ev != null && ev.is(Event.KEY, Keyboard.ESC))
				break;
		}
	}

}
