package com.rokid.ev3.example;

import lejos.utility.Delay;

import com.rokid.ev3.gui.*;

public class Watch {

	public static void main(String[] args) {
		/*Create a local brick desktop*/
		Desktop desktop = Desktop.getDefault();
		/*Set a LayoutCenter as the root view of desktop*/
		LayoutH layout= new LayoutH();
		desktop.setRoot(layout);
		
		Dial m = new Dial();
		m.setGraduations(12);
		
		layout.addChild(m);
		//layout.addChild(new Label("Clock"));
		int h = 0;
		while(true) {
			desktop.refresh();
			m.read(h++ * 6);
			Delay.msDelay(1000);
		}
	}
}
