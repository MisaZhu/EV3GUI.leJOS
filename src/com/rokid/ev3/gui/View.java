package com.rokid.ev3.gui;

import lejos.hardware.lcd.GraphicsLCD;

public class View extends Canvas {
	Container father = null;
	protected boolean visable = true;
	protected boolean alive = false;
	EventHandler eventHandler = null;
	protected String name;
	boolean fixedLayout = false;
	
	public void setHandler(EventHandler h) {
		eventHandler = h;
	}

	protected View(String name) {
		this.name = name;
	}
	
	public void fixedLayout() {
		fixedLayout = true;
	}
	
	public View() {
		rect = new Rect();
	}
	
	protected void setFather(Container father) {
		this.father = father;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean visable() {
		return visable;
	}
	
	public void show() {
		if(visable())
			return;
		
		this.visable = true;
		if(father != null && (father instanceof Layout))
			((Layout)father).layout();
	}
	
	public void hide() {
		if(!visable())
			return;

		if(focused())
			unfocus();
		
		this.visable = false;
		if(father != null && (father instanceof Layout))
			((Layout)father).layout();
		
	}
	
	protected void unfocus() {
		if(!visable() || !focused() || father == null)
			return;
		
		father.focus(null);
	}
	
	public Container getFather() {
		return father;
	}
	
	protected void onDraw(GraphicsLCD g) {
	
	}
	
	protected void draw(GraphicsLCD g) {
	}
	
	public void refresh(GraphicsLCD g) {
		if(!visable())
			return;

		onDraw(g);
		this.draw(g);
	}
	
	public Rect getRect() {
		return rect;
	}

	protected Rect getARect() {
		int ax = getAX();
		int ay = getAY();
		return new Rect(ax, ay, rect.width, rect.height);
	}
	
	public void focus() {
		if(!visable())
			return;
		
		Container f = father;
		View v = this;
		
		while(f != null){
			f.focus(v);
			v = f;
			f = f.father;
		}
	}
	
	public boolean focused() {
		if(!visable())
			return false;
		
		Container f = father;
		View v = this;
		
		while(f != null){
			if(f.focusedView != v)
				return false;
			
			v = f;
			f = f.father;
			
		}
		return true;
	}
	
	public void resizeTo(int w, int h) {
		super.resizeTo(w, h);
		
		if(father != null && (father instanceof Layout))
			((Layout)father).layout();
	}
	
	public void moveTo(int x, int y) {
		if(father != null && (father instanceof Layout))
			return;
		
		super.moveTo(x, y);
	}
	
	public void moveTo(int x, int y, int w, int h) {
		moveTo(x, y);
		resizeTo(w, h);
	}

	protected int getAX() {
		int ax = rect.x;
		View f = father;
		while(f != null) {
			ax += f.rect.x;
			f = f.father;
		}
		return ax;
	}
	
	protected int getAY() {
		int ay = rect.y;
		View f = father;
		while(f != null) {
			ay += f.rect.y;
			f = f.father;
		}
		return ay;
	}
	
	protected Event onEvent(Event ev) {
		return ev;
	}
	
	protected Event eventHandle(Event ev) {
		ev = onEvent(ev);
		
		if(ev != null && eventHandler != null) {
			ev = eventHandler.handle(this, ev);
		}
		
		if(ev != null && father != null) {
			return father.eventHandle(ev);
		}
		return ev;
	}
	
	protected GraphicsLCD getGraph() {
		GraphicsLCD g = null;
		return g;
	}
}
