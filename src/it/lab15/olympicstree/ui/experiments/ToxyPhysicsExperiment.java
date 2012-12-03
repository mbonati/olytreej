package it.lab15.olympicstree.ui.experiments;

import it.lab15.olympicstree.AppDefines;
import it.lab15.olympicstree.ui.MainCanvas;
import it.lab15.olympicstree.ui.utils.ProcessingUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.AttractionBehavior;
import toxi.physics2d.behaviors.GravityBehavior;

public class ToxyPhysicsExperiment extends MainCanvas {

	private static final Logger LOG = LoggerFactory
			.getLogger(ToxyPhysicsExperiment.class);

	int NUM_PARTICLES = 750;

	VerletPhysics2D physics;
	AttractionBehavior mouseAttractor;

	Vec2D mousePos;

	public ToxyPhysicsExperiment() {
		super();
	}

	public void setup() {
		super.setup();

		size(1024, 768, P3D);
		// setup physics with 10% drag
		physics = new VerletPhysics2D();
		physics.setDrag(0.05f);
		physics.setWorldBounds(new Rect(0, 0, width, height));
		// the NEW way to add gravity to the simulation, using behaviors
		physics.addBehavior(new GravityBehavior(new Vec2D(0, 0.15f)));

	}

	void addParticle() {
		VerletParticle2D p = new VerletParticle2D(Vec2D.randomVector().scale(5)
				.addSelf(width / 2, 0));
		physics.addParticle(p);
		// add a negative attraction force field around the new particle
		physics.addBehavior(new AttractionBehavior(p, 20, -1.2f, 0.01f));
	}

	@Override
	public void drawContent() {
		super.drawContent();

		noStroke();
		fill(255);
		if (physics.particles.size() < NUM_PARTICLES) {
			addParticle();
		}
		physics.update();
		for (VerletParticle2D p : physics.particles) {
			ellipse(p.x, p.y, 5, 5);
		}

	}

	public void mousePressed() {
		//super.mousePressed();
		mousePos = new Vec2D(mouseX, mouseY);
		// create a new positive attraction force field around the mouse
		// position (radius=250px)
		mouseAttractor = new AttractionBehavior(mousePos, 250, 0.9f);
		physics.addBehavior(mouseAttractor);
	}

	public void mouseDragged() {
		// update mouse attraction focal point
		//super.mouseDragged();
		mousePos.set(mouseX, mouseY);
	}

	public void mouseReleased() {
		// remove the mouse attraction when button has been released
		//super.mouseReleased();
		physics.removeBehavior(mouseAttractor);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.info("Starting Experimental {}...", AppDefines.APPLICATION_NAME);
		ProcessingUtils.runSketch(ToxyPhysicsExperiment.class);
	}

}
