package com.rokid.ev3.example;

import com.rokid.ev3.gui.*;

/**
 * Demo for Image view
 * @author misa
 *
 */
public class ImageDemo {
	public static void main(String[] args) {
		Desktop desktop = Desktop.getDefault();
		
		/*Set a horizontal layout as the root view of desktop*/
		LayoutH layout= new LayoutH();
		desktop.setRoot(layout);

		/*These two views will be layouted horizontally automatically*/
		layout.addChild(new ImageButton("head.lni"));
		layout.addChild(new Label("EV3 GUI"));
		
		desktop.refresh();
		desktop.eventHandle();
	}

}
