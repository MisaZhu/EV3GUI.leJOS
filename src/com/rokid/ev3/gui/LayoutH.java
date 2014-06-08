package com.rokid.ev3.gui;

/**
 * LayoutH is a container layouts the sub views horizontally.
 * @author Misa.Z
 *
 */
public class LayoutH extends Layout {

	protected Event onEvent(Event ev) {
		if(ev.type == Event.KEY_PRESS) {	
			if(ev.intValue == lejos.hardware.Button.ID_RIGHT) {
				focusNext();
				return null;
			}
			else if(ev.intValue == lejos.hardware.Button.ID_LEFT) {
				focusPre();
				return null;
			}
		}
		return ev;
	}
	

	protected void layout() {
		int lnum = 0; // numbers of dynamic size kids
		
		int lw = rect.width;
		int h = rect.height;
		
		for(View v: children) {
			if(v != null && v.visable()) {
				if(v.fixedLayout) {
					lw -= v.getRect().width;
				}
				else {
					lnum++;
				}
			}
		}

		int aw = 0; //averaged width
		if(lnum > 0) {
			aw = lw / lnum;
		}
		
		int x = 0;

		for(View v: children) {
			if(v != null && v.visable()) {
				v.moveToLayout(x, 0);
				if(v.fixedLayout) {
					int ow = v.getRect().width;
					v.resizeToLayout(ow, h);
					x += ow;
				}
				else {
					v.resizeToLayout(aw, h);
					x += aw;
				}
			}
		}
	}
}
