package com.rokid.ev3.gui;

/**
* LayoutV is a container layouts the sub views Vertically.
* @author Misa.Z
*/

public class LayoutV extends Layout {

	protected Event onEvent(Event ev) {
		if(ev.type == Event.KEY_PRESS) {	
			if(ev.intValue == lejos.hardware.Button.ID_DOWN) {
				focusNext();
				return null;
			}
			else if(ev.intValue == lejos.hardware.Button.ID_UP) {
				focusPre();
				return null;
			}
		}
		return ev;
	}
	

	protected void layout() {
		int lnum = 0; // numbers of dynamic size kids
		
		int w = rect.width;
		int lh = rect.height;

		for(View v: children) {
			if(v != null && v.visable()) {
				if(v.fixedLayout) {
					lh -= v.getRect().height;
				}
				else {
					lnum++;
				}
			}
		}

		int ah = 0; //averaged height
		if(lnum > 0) {
			ah = lh / lnum;
		}
		
		int y = 0;
		for(View v: children) {
			if(v != null && v.visable()) {
				v.moveToLayout(0, y);
				if(v.fixedLayout) {
					int oh = v.getRect().height;
					v.resizeToLayout(w, oh);
					y += oh;
				}
				else {
					v.resizeToLayout(w, ah);
					y += ah;
				}
			}
		}
	}
}
