package com.rokid.ev3.gui;

/**
 * Base class for all Layout classes which will layout the sub views automatically 
 * @author Misa.Z
 *
 */
public class Layout extends Container {
	protected void layout() {		
	}
	
	protected void onResize() {
		super.onResize();
		layout();
	}

	public void addChild(View v) {
		super.addChild(v);
		layout();
	}
	
	public void removeChild(View v) {
		super.removeChild(v);
		layout();
	}
}
