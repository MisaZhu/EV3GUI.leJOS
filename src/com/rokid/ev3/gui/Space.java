package com.rokid.ev3.gui;

/**
 * Space view which draw nothing, usually used for layout
 * @author Misa.Z
 *
 */
public class Space extends View {
	public Space(int size) {
		this.fixedLayout();
		resizeTo(size, size);
	}
	
	public Space() {
	}
}
