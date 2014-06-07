package com.rokid.ev3.gui;

import java.io.File;
import java.io.FileInputStream;

import lejos.hardware.lcd.Image;

/**
 * Load image from EV3 image file
 * @author Misa.Z
 *
 */
public class ImageLoader {
	
	public static Image load(String file) {
		try {
			return Image.createImage(new FileInputStream(new File(file)));
		}catch(Exception e) {
			return null;
		}
	}
}
