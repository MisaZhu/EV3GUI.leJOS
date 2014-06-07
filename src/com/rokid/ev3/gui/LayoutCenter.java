package com.rokid.ev3.gui;

/**
 * LayoutC is a container layouts the sub views to the center.
 * @author Misa.Z
 *
 */
public class LayoutCenter extends Layout {
	protected void layout() {
		int num = children.size();
		for(int i=0; i<num; ++i) {
			View v = getChild(i);
			if(v != null && v.visable()) {
				Rect r = v.getRect();
				v.moveToLayout((rect.width - r.width) / 2,
						(rect.height - r.height) / 2);
			}
		}
	}

}
