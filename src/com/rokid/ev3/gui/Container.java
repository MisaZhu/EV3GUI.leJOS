package com.rokid.ev3.gui;

import java.util.Vector;

import lejos.hardware.lcd.GraphicsLCD;

/**
 * 
 * @author Misa.Z
 * Container is the base class for all view classes which can has sub views.
 *
 */
public class Container extends View {
	Vector<View> children = null;
	View focusedView = null;
	
	public Container() {
		alive = true;
		children = new Vector<View>();
	}

	public void refresh(GraphicsLCD g) {
		if(!visable())
			return;
		
		super.refresh(g);
		
		for(View v : children) {
			if(v != null) {
				v.refresh(g);
			}
		}
	}
	
	/**
	 * Get current focused view within this container.
	 */
	public View getFocused() {
		View v = this;
		while(true) {
			if(v instanceof Container) {
				if(((Container) v).focusedView != null)
					v = ((Container) v).focusedView;
				else 
					return v;
			}
			else {
				return v;
			}
		}
	}
	
	/**
	 * Set current focused view
	 */
	void focus(View v) {
		focusedView = v;
	}

	/**
	 * 
	 * @param v View will be added. If its already been added will do nothing.
	 */
	public void addChild(View v) {
		if(indexOf(v) >= 0)
			return;
		
		v.setFather(this);
		children.add(v);
	}
	
	/**
	 * 
	 * @param v View will be removed. It will lose the focus first
	 */
	public void removeChild(View v) {
		v.unfocus();
		children.remove(v);
	}
	
	/**
	 * Set focus from current sub view to the next sub view.
	 */
	void focusNext() {
		int num = children.size();
		View focused = this.focusedView;
		if(focused == null || num == 0)
			return;
		
		int i = children.indexOf(focused);
		if(i < 0)
			return;
		
		int start = i;
		i++;
		
		while(i != start) {
			if(i >= num)
				i = 0;
			View v = (View)children.get(i);
			if(v != null && v.alive && v.visable) {
				focus(v);
				return;
			}
			i++;
		}
	}
	
	/**
	 * Set focus from current sub view to the previous sub view.
	 */
	void focusPre() {
		int num = children.size();
		View focused = this.focusedView;
		if(focused == null || num == 0)
			return;
		
		int i = children.indexOf(focused);
		if(i < 0)
			return;
		
		int start = i;
		i--;
		
		while(i != start) {
			if(i < 0)
				i = num - 1;
			View v = (View)children.get(i);
			if(v != null && v.alive && v.visable) {
				focus(v);
				return;
			}
			i--;
		}
	}
	
	/**
	 * Get the index of subview
	 * @param v subview 
	 * @return index of the subview or -1 if there is no such view.
	 */
	public int indexOf(View v) {
		return children.indexOf(v);
	}
	
	/**
	 * Get the subview
	 * @param i index of the subview
	 * @return null if there is no such subview.
	 */
	public View getChild(int i) {
		if(i >= children.size()) {
			return null;
		}
		
		return (View)children.get(i);
	}
	
	/**
	 * Get subview by name
	 * @param name subview's name
	 * @return null if there is no such subview.
	 */
	public View getChild(String name) {
		for(View v: children) {
			if(v != null && v.name.equals(name)) {
				return v;
			}
		}
		
		return null;
	}
}

