package com.rokid.ev3.example;

import com.rokid.ev3.gui.*;

class EHandler implements EventHandler {
	Desktop desktop;
	
	EHandler(Desktop desktop) {
		this.desktop = desktop;
	}

	public Event handle(View view, Event ev) {
		if (ev.type == Event.GUI && ev.value == Event.PRESS) {
			if (view.getName() == "win") {
				Window w = new Window("sub");
				w.moveTo(40, 40, 100,  100);
				w.popup(Popup.CENTER);
				w.addChild(new Label("Sub Window"));
			} 
			else if (view.getName() == "light") {
				EventHandle.light++;
				if (EventHandle.light >= 10)
					EventHandle.light = 0;
				desktop.getLED().light(EventHandle.light);
			}
		}
		return ev;
	}
}

public class EventHandle {
	public static int light = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Desktop desktop = Desktop.getDefault();
		

		/*Set a horizontal layout as the root view of desktop*/
		LayoutH layout= new LayoutH();
		desktop.setRoot(layout);

		/*These two views will be layouted horizontally automatically*/
		layout.addChild(new ImageButton("head.lni"));
		LabelButton lb = new LabelButton("EV3 GUI");
		layout.addChild(lb);
		lb.focus();

		
		Window w = new Window("window");
		w.setWorkspace(new LayoutV());
		Container ws = w.getWorkspace();
		
		EHandler handler = new EHandler(desktop);

		LabelButton v2 = new LabelButton("Win");
		v2.setName("win");
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

		w.popup(Popup.FULLSCREEN);


		while (true) {
			v4.setLabel("light " + light);
			desktop.refresh();
			Event ev = desktop.eventHandle();
			if(ev != null && ev.is(Event.KEY, Keyboard.ESC))
				break;
		}
	}

}
