package it.lab15.olympicstree.ui.utils;

import processing.core.PApplet;

public class ProcessingUtils {

	public static void runSketch(String sketchName){
	    PApplet.main(new String[] { "--present", sketchName });
	}

	public static void runSketch(Class mainCanvasClass){
		PApplet.main(new String[] { mainCanvasClass.getName() });
	}

	
}
