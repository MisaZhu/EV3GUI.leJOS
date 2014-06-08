package com.rokid.ev3.example;

import com.rokid.ev3.gui.*;

class EHandler implements EventHandler {
	Desktop desktop;
	Window win;

	public Event handle(View view, Event ev) {
		if (ev.type == Event.GUI_PRESS) {
			if (view.getName() == "small") {
				Window w = new Window("Sub");
				w.moveTo(40, 40, 100,  100);
				w.popup(Popup.CENTER);
				w.getWorkspace().addChild(new Label("Sub Window"));
			} 
			else if (view.getName() == "win") {
				win.popup(Popup.FULLSCREEN);
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
		EHandler handler = new EHandler();
		handler.desktop = desktop;

		LayoutCenter layout= new LayoutCenter();
		desktop.setRoot(layout);

		/*These two views will be layouted horizontally automatically*/
		LabelButton lb = new LabelButton("Open Window");
		layout.addChild(lb);
		lb.setName("win");
		lb.setHandler(handler);
		lb.focus();

		
		Window w = new Window("window");
		handler.win = w;
		w.setWorkspace(new LayoutV());
		Container ws = w.getWorkspace();
		

		LabelButton v2 = new LabelButton("SmallWin");
		v2.setName("small");
		ws.addChild(v2);
		v2.setHandler(handler);

		Button v3 = new LabelButton("Light");
		v3.setName("light");
		ws.addChild(v3);
		v3.focus();
		v3.setHandler(handler);

		Label v4 = new Label("Light 0");
		ws.addChild(v4);

		//w.popup(Popup.FULLSCREEN);


		while (true) {
			v4.setLabel("light " + light);
			desktop.refresh();
			Event ev = desktop.eventHandle();
			if(ev != null && ev.type == Event.KEY_PRESS && ev.intValue == BrickButton.ESC)
				break;
		}
	}

}
