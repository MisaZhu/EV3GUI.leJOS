package com.rokid.ev3.example;

import com.rokid.ev3.gui.*;

/**
 * HelloWorld demo
 * @author Misa.Z
 *
 */
public class HelloWorld {
	public static void main(String[] args) {
		/*Create a local brick desktop*/
		Desktop desktop = Desktop.getDefault();
		/*Set a LayoutCenter as the root view of desktop*/
		LayoutCenter layout= new LayoutCenter();
		desktop.setRoot(layout);
		
		/*Add a label view to the layout, it will be moved to the center position.*/
		layout.addChild(new Label("Hello, World!"));
		
		/*Flush all the view elements to the LCD*/
		desktop.refresh();
		
		/*Waiting for any button pressed event and quit.*/
		desktop.eventHandle();
	}

}
