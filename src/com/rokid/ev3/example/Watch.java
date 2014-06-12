package com.rokid.ev3.example;

import lejos.utility.Delay;
import lejos.utility.Timer;
import lejos.utility.TimerListener;

import com.rokid.ev3.gui.*;

class TimerL implements TimerListener {
	Dial m = null;
	Desktop desktop = null;
	int h = 0;
	public void timedOut() {
		if(m != null && desktop != null) {
			m.read(h * 6);
			desktop.refresh();
		}
		h++;
	}
}

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

		TimerL tl = new TimerL();
		tl.m = m;
		tl.desktop = desktop;
		
		Timer timer = new Timer(1000, tl);
		timer.start();
		
		desktop.eventHandle();
		timer.stop();
	}
}
