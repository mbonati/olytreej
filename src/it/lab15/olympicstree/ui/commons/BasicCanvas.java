package it.lab15.olympicstree.ui.commons;

import it.lab15.olympicstree.commons.Vec2;
import it.lab15.olympicstree.resources.ResourcesLocator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class BasicCanvas extends PApplet {

	private static final Logger LOG = LoggerFactory.getLogger(BasicCanvas.class);

	private Map<String, PImage> cachedImages = new HashMap<String, PImage>();
	private boolean displayDebugIformations = true;
	private PFont debugInfoFont = loadFontResource("Monospaced-12.vlw");

	public BasicCanvas() {
		LOG.debug("Initializing basic canvas...");
	}

	public void setup() {
		LOG.debug("basic canvas setup...");
	}

	/**
	 * Load an image from cache if exists, esle load from file
	 * 
	 * @param filename
	 * @return
	 */
	public PImage loadImageResource(String filename) {
		return loadImageResource(filename, true);
	}

	/**
	 * Load an image fomr cahce if cached is true and if exists, else load from
	 * file Shared images cannot be modified, translated or any other
	 * transformation
	 * 
	 * @param filename
	 * @param shared
	 * @return
	 */
	public PImage loadImageResource(String filename, boolean shared) {
		LOG.debug("loadImage called for loadImage={} shared={}", filename, shared);
		if (shared) {
			if (cachedImages.containsKey(filename)) {
				return cachedImages.get(filename);
			} else {
				PImage newImage = loadImageResourceInternal(filename);
				cachedImages.put(filename, newImage);
				return newImage;
			}
		} else {
			return loadImageResourceInternal(filename);
		}
	}

	private PImage loadImageResourceInternal(String filename) {
		String fileURL = ResourcesLocator.getImageResourceURI(filename);
		LOG.debug("loadImageInternal called for {}: path={}", filename, fileURL);
		return loadImage(fileURL);
	}

	// Wraps a point in space from 0 to canvas edge, given a buffer
	public Vec2 wrap(Vec2 v, float buff) {
		if (v.x < 0 - buff)
			v = nv(width / 2, height / 2);
		if (v.x > width + buff)
			v = nv(width / 2, height / 2);
		if (v.y < 0 - buff)
			v = nv(width / 2, height / 2);
		if (v.y > height + buff)
			v = nv(width / 2, height / 2);
		return v;
	}

	public Vec2 newVec(Vec2 v, float ang, float magnitude) {
		Vec2 temp = new Vec2(v);
		temp.disp(ang, magnitude);
		return temp;
	}

	public Vec2 newVec(float ang, float magnitude) {
		ang = radians(ang);
		float tx = cos(ang) * magnitude;
		float ty = sin(ang) * magnitude * -1;
		Vec2 temp = new Vec2(tx, ty);
		return temp;
	}

	public Vec2 nv(float x, float y) {
		return new Vec2(x, y);
	}

	public Vec2 nv(Vec2 a, Vec2 b) {
		return new Vec2(a, b);
	}

	public Vec2 nv(Vec2 v) {
		return new Vec2(v.x, v.y);
	}

	// --------------USEFUL VECTOR TOOLS--------------
	// Finds the midpoint between two positions in space
	public Vec2 midPoint(Vec2 a, Vec2 b) {
		Vec2 d = new Vec2(a, b);
		d.mul(.5f);
		Vec2 dest = new Vec2(a);
		dest.add(d);
		// d.sub(b);
		// Vec newVec=new Vec(a);
		// newVec.add(d);
		return dest;
	}

	// Constrains a point in space from 0 to canvas edge, given a buffer
	public Vec2 constrain(Vec2 v, float buff) {
		Vec2 temp = new Vec2(v);
		temp.x = constrain(temp.x, -buff, width + buff);
		temp.y = constrain(temp.y, -buff, height + buff);
		return temp;
	}

	// Finds the average position in an array of points in space
	public Vec2 avg(Vec2 v[]) {
		Vec2 total = new Vec2();
		for (int i = 0; i < v.length; i++) {
			total.add(v[i]);
		}
		total.div(v.length);
		return total;
	}

	// Using point a as origin, this returns the angle between a and b.
	public float getAng(Vec2 a, Vec2 b) {
		float ang = atan2(-1 * (b.y - a.y), b.x - a.x);
		ang = degrees(ang);
		if (b.y > a.y)
			return ang = 360 + ang;
		if (b.y == a.y && b.x > a.x)
			return ang = 0;
		if (b.y == a.y && b.x < a.x)
			return ang = 180;
		return ang;
	}

	// Returns the distances between two points in space
	public float dist(Vec2 a, Vec2 b) {
		if (a == null || b == null)
			return 0;
		return dist(a.x, a.y, b.x, b.y);
	}

	public Vec2 lerp(Vec2 a, Vec2 b, float k) {
		Vec2 lerpN = new Vec2();
		lerpN.x = lerp(a.x, b.x, k);
		lerpN.y = lerp(a.y, b.y, k);
		return lerpN;
	}

	public void lerp(Vec2 v, Vec2 source, Vec2 target, float k) {
		v.x = lerp(source.x, target.x, k);
		v.y = lerp(source.y, target.y, k);
	}

	// Appends a Vector point at the end of a list of Vectors
	public Vec2[] append(Vec2 v[], Vec2 nv) {
		Vec2 temp[] = new Vec2[v.length + 1];
		System.arraycopy(v, 0, temp, 0, v.length);
		temp[v.length] = new Vec2(nv);
		return temp;
	}

	@Override
	public void draw() {
		// Background
		drawBackground();

		// Debug infos
		drawDebugInformations();

		// Draw the effective visualization content
		pushMatrix();
		drawContent();
		popMatrix();

		// Watermark logo
		drawWatermark();

	}

	protected void drawDebugInformations() {
		if (this.displayDebugIformations) {
			fill(200, 255, 230);
			textFont(debugInfoFont, 10);
			text((int) frameRate + " FPS", 10, 20);
		}
	}

	protected void drawWatermark() {
	}

	protected void drawContent() {
	}

	protected void drawBackground() {
		background(0);
	}

	public boolean isDisplayDebugIformations() {
		return displayDebugIformations;
	}

	public void setDisplayDebugIformations(boolean displayDebugIformations) {
		this.displayDebugIformations = displayDebugIformations;
	}

	public static PFont loadFontResource(String filename) {
		LOG.debug("loadFontResource called for {}" ,  filename);
		try {
			InputStream input = ResourcesLocator.getFontResourceURL(filename).openStream();
			return new PFont(input);
		} catch (Exception e) {
			LOG.error("Could not load font {}. Make sure that the font has been copied to the resources package.", filename, e);
			//die("Could not load font " + filename + ". " + "Make sure that the font has been copied " + "to the data folder of your sketch.", e);
		}
		return null;
	}

	public static InputStream createInputFormResource(String filename) throws IOException {
		InputStream input = ResourcesLocator.getResourceURL(filename).openStream();
		if ((input != null) && filename.toLowerCase().endsWith(".gz")) {
			try {
				return new GZIPInputStream(input);
			} catch (IOException e) {
				LOG.error("createInputFormResource error:{}", e.getMessage(), e);
				return null;
			}
		}
		return input;
	}

}
