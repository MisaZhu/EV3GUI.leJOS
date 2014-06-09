package com.rokid.ev3.gui;

import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.Image;

public class ImageButton extends Button {
	Image image = null;
	int trans = GraphicsLCD.TRANS_NONE;
	
	public ImageButton() {	
	}
	
	public ImageButton(String imgFile) {
		setImage(ImageLoader.load(imgFile));
	}
	
	/**
	 * Set transform of the image;
	 */
	public void setTrans(int trans) {
		this.trans = trans;
	}
	
	/**
	 * Set image of the view;
	 */
	public void setImage(Image img) {
		this.image = img;
		
		if(img == null)
			resizeTo(0, 0);
		else
			resizeTo(image.getWidth(), image.getWidth());
	}
	
	/**
	 * Set Image of view with transform type
	 */
	public void setImage(Image img, int trans) {
		if(img == null)
			return;
		
		Image imgv= Image.createImage(img, 0, 0, image.getWidth(), image.getHeight(), trans);
		setImage(imgv);
	}
	
	/**
	 * Set Image of view with region and transform type
	 */
	public void setImage(Image img, int rx, int ry, int rw, int rh, int trans) {
		if(img == null)
			return;
		
		Image imgv= Image.createImage(img, rx, ry, rw, rh, trans);
		setImage(imgv);
	}
	
	
	protected void draw(GraphicsLCD g) {
		if(image == null)
			return;
		Size size = getSize();
		
		drawImage(g, image, size.w/2, size.h/2,
				GraphicsLCD.VCENTER | GraphicsLCD.HCENTER);
	}
}
