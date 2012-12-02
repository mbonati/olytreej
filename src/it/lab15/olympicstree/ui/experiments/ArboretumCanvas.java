package it.lab15.olympicstree.ui.experiments;

import it.lab15.olympicstree.AppDefines;
import it.lab15.olympicstree.ui.MainCanvas;
import it.lab15.olympicstree.ui.utils.ProcessingUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import traer.physics.Particle;
import traer.physics.ParticleSystem;
import traer.physics.Spring;

public class ArboretumCanvas extends MainCanvas {

	private static final Logger LOG = LoggerFactory.getLogger(ArboretumCanvas.class);

	final float NODE_SIZE = 10;
	final float EDGE_LENGTH = 20;
	final float EDGE_STRENGTH = 0.2f;
	final float SPACER_STRENGTH = 1000;

	ParticleSystem physics;
	float scale = 1;
	float centroidX = 0;
	float centroidY = 0;

	public ArboretumCanvas() {
		super();
	}

	public void setup() {
		super.setup();

		frameRate(60);
		smooth();
		strokeWeight(2);
		ellipseMode(CENTER);

		physics = new ParticleSystem(0f, 0.1f);

		// Runge-Kutta, the default integrator is stable and snappy,
		// but slows down quickly as you add particles.
		// 500 particles = 7 fps on my machine

		// Try this to see how Euler is faster, but borderline unstable.
		// 500 particles = 24 fps on my machine
		// physics.setIntegrator( ParticleSystem.MODIFIED_EULER );

		// Now try this to see make it more damped, but stable.
		// physics.setDrag( 0.2 );

		//textFont(loadFont("AkzidenzGroteskBQ-Regular-14.vlw"));

		noSmooth();
		
		initialize();

	}

	@Override
	public void drawContent() {
		super.drawContent();

		physics.tick();
		if (physics.numberOfParticles() > 1)
			updateCentroid();
		fill(100,200,100);
		text("" + physics.numberOfParticles() + " PARTICLES\n"
				+ (int) frameRate + " FPS", 10, 30);
		translate(width / 2, height / 2);
		scale(scale);
		translate(-centroidX, -centroidY);

		drawNetwork();
		
	}

	// really basic collision strategy:
	// sides of the window are walls
	// if it hits a wall pull it outside the wall and flip the direction of the
	// velocity
	// the collisions aren't perfect so we take them down a notch too
	void handleBoundaryCollisions(Particle p) {
		if (p.position().x() < 0 || p.position().x() > width)
			p.velocity().set(-0.9f * p.velocity().x(), p.velocity().y(), 0);
		if (p.position().y() < 0 || p.position().y() > height)
			p.velocity().set(p.velocity().x(), -0.9f * p.velocity().y(), 0);
		p.position().set(constrain(p.position().x(), 0, width),
				constrain(p.position().y(), 0, height), 0);
	}

	void drawNetwork() {
		// draw vertices
		fill(160);
		noStroke();
		for (int i = 0; i < physics.numberOfParticles(); ++i) {
			Particle v = physics.getParticle(i);
			ellipse(v.position().x(), v.position().y(), NODE_SIZE, NODE_SIZE);
		}

		// draw edges
		stroke(0);
		beginShape(LINES);
		for (int i = 0; i < physics.numberOfSprings(); ++i) {
			Spring e = physics.getSpring(i);
			Particle a = e.getOneEnd();
			Particle b = e.getTheOtherEnd();
			vertex(a.position().x(), a.position().y());
			vertex(b.position().x(), b.position().y());
		}
		endShape();
	}

	public void mousePressed() {
		//LOG.debug("mousepressed");
		if (mouseButton == RIGHT){
			addNode();
		}
	}

	public void mouseDragged() {
		//LOG.debug("mousedragged");
		if (mousePressed && (mouseButton == RIGHT)) 
			addNode();
	}

	public void keyPressed() {
		if (key == 'c') {
			initialize();
			return;
		}

		if (key == ' ') {
			addNode();
			return;
		}
	}

	// ME ////////////////////////////////////////////

	void updateCentroid() {
		float xMax = Float.NEGATIVE_INFINITY, xMin = Float.POSITIVE_INFINITY, yMin = Float.POSITIVE_INFINITY, yMax = Float.NEGATIVE_INFINITY;

		for (int i = 0; i < physics.numberOfParticles(); ++i) {
			Particle p = physics.getParticle(i);
			xMax = max(xMax, p.position().x());
			xMin = min(xMin, p.position().x());
			yMin = min(yMin, p.position().y());
			yMax = max(yMax, p.position().y());
		}
		float deltaX = xMax - xMin;
		float deltaY = yMax - yMin;

		centroidX = xMin + 0.5f * deltaX;
		centroidY = yMin + 0.5f * deltaY;

		if (deltaY > deltaX)
			scale = height / (deltaY + 50);
		else
			scale = width / (deltaX + 50);
	}

	void addSpacersToNode(Particle p, Particle r) {
		for (int i = 0; i < physics.numberOfParticles(); ++i) {
			Particle q = physics.getParticle(i);
			if (p != q && p != r)
				physics.makeAttraction(p, q, -SPACER_STRENGTH, 20);
		}
	}

	void makeEdgeBetween(Particle a, Particle b) {
		physics.makeSpring(a, b, EDGE_STRENGTH, EDGE_STRENGTH, EDGE_LENGTH);
	}

	void initialize() {
		physics.clear();
		physics.makeParticle();
	}

	void addNode() {
		Particle p = physics.makeParticle();
		Particle q = physics.getParticle((int) random(0,
				physics.numberOfParticles() - 1));
		while (q == p)
			q = physics.getParticle((int) random(0,
					physics.numberOfParticles() - 1));
		addSpacersToNode(p, q);
		makeEdgeBetween(p, q);
		p.position().set(q.position().x() + random(-1, 1),
				q.position().y() + random(-1, 1), 0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.info("Starting Experimental {}...", AppDefines.APPLICATION_NAME);
		ProcessingUtils.runSketch(ArboretumCanvas.class);
	}

}
