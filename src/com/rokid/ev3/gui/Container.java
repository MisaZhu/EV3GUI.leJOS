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
	Vector children = null;
	
	public Container() {
		alive = true;
		children = new Vector();
	}

	public void refresh(GraphicsLCD g) {
		if(!visable())
			return;
		
		super.refresh(g);
		
		for(int i= 0; i< children.size(); ++i) {
			View v = (View)children.get(i);
			if(v != null) {
				v.refresh(g);
			}
		}
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
		if(Desktop.focusedView == null || num == 0)
			return;
		
		int i = children.indexOf(Desktop.focusedView);
		if(i < 0)
			return;
		
		int start = i;
		i++;
		
		while(i != start) {
			if(i >= num)
				i = 0;
			View v = (View)children.get(i);
			if(v != null && v.alive && v.visable) {
				Desktop.focus(v);
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
		if(Desktop.focusedView == null || num == 0)
			return;
		
		int i = children.indexOf(Desktop.focusedView);
		if(i < 0)
			return;
		
		int start = i;
		i--;
		
		while(i != start) {
			if(i < 0)
				i = num - 1;
			View v = (View)children.get(i);
			if(v != null && v.alive && v.visable) {
				Desktop.focus(v);
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
		for(int i= 0; i< children.size(); ++i) {
			View v = (View)children.get(i);
			if(v != null && v.name.equals(name)) {
				return v;
			}
		}
		
		return null;
	}
}

