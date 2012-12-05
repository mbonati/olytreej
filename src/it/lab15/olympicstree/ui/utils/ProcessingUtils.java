package it.lab15.olympicstree.ui.utils;

import it.lab15.olympicstree.ui.experiments.ToxiClusterExperiment;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import processing.core.PApplet;

public class ProcessingUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProcessingUtils.class);
	
	public static final String FULL_SCREEN_SWITCH = "--present";
//	public static final String FULL_SCREEN_SWITCH = "--full-screen";
	
	public static void runSketch(String sketchName, boolean fullScreen){
		LOG.debug("runSketch called sketchName={} fullScreen={}", sketchName, fullScreen);
	    List<String> params = new ArrayList<String>();
		if (fullScreen){
			params.add(FULL_SCREEN_SWITCH);
	    }
		params.add(sketchName);
		PApplet.main(params.toArray(new String[params.size()]));
		LOG.debug("runSketch done");
	}

	public static void runSketch(Class mainCanvasClass){
		runSketch(mainCanvasClass, false);
	}

	public static void runSketch(Class mainCanvasClass, boolean fullScreen){
		runSketch(mainCanvasClass.getName(), fullScreen);
	}
	
}
