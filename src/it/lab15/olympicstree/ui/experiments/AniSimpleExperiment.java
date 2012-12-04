package it.lab15.olympicstree.ui.experiments;

import it.lab15.olympicstree.AppDefines;
import it.lab15.olympicstree.ui.MainCanvas;
import it.lab15.olympicstree.ui.utils.ProcessingUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.looksgood.ani.Ani;

public class AniSimpleExperiment extends MainCanvas {

	private static final Logger LOG = LoggerFactory
			.getLogger(AniSimpleExperiment.class);

	public AniSimpleExperiment() {
		super();
	}

	float x = 256;
	float y = 256;

	float ellipseSize = 120;
	
	public void setup() {
		super.setup();

		setPanEnabled(false);
		
		smooth();
		noStroke();

		// you have to call always Ani.init() first!
		Ani.init(this);
	}

	protected void setupSizeAndRender(){
		size(1024, 768, OPENGL);
	}

	protected void drawContent() {
		super.drawContent();
		fill(250);
		ellipse(x, y, ellipseSize, ellipseSize);
	}

	public void mousePressed(){
		super.mousePressed();
		Ani.to(this, 1.5f, "ellipseSize", 0);
	}
	
	public void mouseReleased() {
		super.mouseReleased();
		// animate the variables x and y in 1.5 sec to mouse click position
		Ani.to(this, 1.5f, "x", mouseX);
		Ani.to(this, 1.5f, "y", mouseY);
		
		ellipseSize = 0;
		Ani.to(this, 1.5f, "ellipseSize", 120);
	}
	
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.info("Starting AniSimpleExperiment {}...",
				AppDefines.APPLICATION_NAME);
		ProcessingUtils.runSketch(AniSimpleExperiment.class);
	}

}
