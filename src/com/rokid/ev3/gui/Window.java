package com.rokid.ev3.gui;

import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;

public class Window extends Popup {
	String title;
	Font fontTitle;
	Container workspace;
	
	public Container getWorkspace() {
		return workspace;
	}
	
	public Window(String title) {
		this.title = title;
		fontTitle = Font.getSmallFont();
		workspace = new Container();
		this.addChild(workspace);
	}
	
	public void setWorkspace(Container ws) {
		removeChild(workspace);
		addChild(ws);
		workspace = ws;
		setWSSize();
	}
	
	void setWSSize() {
		Rect r = getRect();
		workspace.moveTo(0, fontTitle.height);
		workspace.resizeTo(r.width, r.height - fontTitle.height);
	}
	
	public void setTitleFont(Font fnt) {
		fontTitle = fnt;
	}
	
	protected void onResize() {
		setWSSize();
	}
	
	protected void drawFrame(GraphicsLCD g) {
		g.setColor(255, 255, 255);
		int w = getWidth();
		int h = getHeight();
		
		fillRect(g, 0, 0, w-1, h-1);
		g.setColor(0, 0, 0);
		drawRect(g, 0, 0, w-1, h-1);
		g.setStrokeStyle(GraphicsLCD.DOTTED);
		drawLine(g, 3, h, w, h);
		drawLine(g, w, 3, w, h);
		g.setStrokeStyle(GraphicsLCD.SOLID);
	}

	protected void drawTitle(GraphicsLCD g) {
		g.setColor(0, 0, 0);
		int w = getWidth();
		fillRect(g, 0, 0, w-1, fontTitle.height);
		g.setFont(fontTitle);
		g.setColor(255, 255, 255);
		drawString(g, title, 0, 0, GraphicsLCD.TOP | GraphicsLCD.LEFT);
	}
	
	protected void draw(GraphicsLCD g) {
		drawFrame(g);
		drawTitle(g);
	}
}
