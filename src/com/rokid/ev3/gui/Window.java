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
		workspace.moveTo(0, fontTitle.height+2);
		workspace.resizeTo(r.width, r.height - fontTitle.height -2);
	}
	
	public void setTitleFont(Font fnt) {
		fontTitle = fnt;
	}
	
	protected void onResize() {
		setWSSize();
	}

	protected void drawTitle(GraphicsLCD g) {
		g.setColor(0, 0, 0);
		Size size = getSize();
		fillRect(g, 1, 1, size.w-2, fontTitle.height+1);
		g.setFont(fontTitle);
		g.setColor(255, 255, 255);
		drawString(g, title, 1, 1, GraphicsLCD.TOP | GraphicsLCD.LEFT);
	}
	
	protected void draw(GraphicsLCD g) {
		drawFrame(g);
		drawTitle(g);
	}
}
