package it.lab15.olympicstree.ui;

import it.lab15.olympicstree.ui.commons.VirtualCanvas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import processing.core.PImage;

public class MainCanvas extends VirtualCanvas {

	private static final Logger LOG = LoggerFactory.getLogger(MainCanvas.class);

	PImage backgroundImg;
	PImage watermarkImg;
	PImage logoImg;

	public MainCanvas() {
		super();
		LOG.debug("Creating main canvas...");
	}

	public void setup() {
		super.setup();

		backgroundImg = loadImageResource("bgLargeDark.jpg");
		watermarkImg = loadImageResource("watermark.png");
		logoImg = loadImageResource("logo.png");
		
		smooth();

	}
	

	@Override
	protected void drawBackground() {
		// super.drawBackground();
		// we gotta draw the bg image without smoothing to avoid a diagonal line
		// no point in doing so, anyway
		noSmooth();
		// Resize the background based on our app window
		pushMatrix();
		scale(width / 1024.0f, height / 768.0f);
		image(backgroundImg, 0, 0);
		popMatrix();
		smooth();
	}

	@Override
	protected void drawWatermark() {
		noSmooth();
		// custom shoutout
		textAlign(LEFT);
		// textFont(magnetFont, 12);
		fill(255, 255);
		// text("visualization mashup by Flux", width - 155, height - 16);
		text("www.meetthedata.com", 10, height - 10);
		// logo
		image(watermarkImg, width - 250, height - 68);
		
		image(logoImg, width - 110, height - 75);
		smooth();
	}

//	@Override
//	protected void drawVirtualContent() {
//		super.drawVirtualContent();
//		pushMatrix();
//		smooth();
//		fill(160);
//		noStroke();
//		ellipse(300, 300, 100, 100);
//		popMatrix();
//	}

}
