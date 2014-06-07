package com.rokid.ev3.example;

import com.rokid.ev3.gui.*;


public class ImageDemo {
	public static void main(String[] args) {
		Desktop desktop = Desktop.getDefault();
		LayoutH layout= new LayoutH();
		desktop.setRoot(layout);

		layout.addChild(new ImageButton("head.lni"));
		layout.addChild(new Label("EV3 GUI"));
		
		desktop.refresh();
		desktop.eventHandle();
	}

}
