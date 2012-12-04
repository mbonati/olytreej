package it.lab15.olympicstree.ui.experiments;

import it.lab15.olympicstree.AppDefines;
import it.lab15.olympicstree.core.data.beans.Edition;
import it.lab15.olympicstree.core.data.beans.EditionFactory;
import it.lab15.olympicstree.core.physics.OTParticle;
import it.lab15.olympicstree.core.physics.OTPhysics2D;
import it.lab15.olympicstree.core.physics.particleimpl.EditionParticle;
import it.lab15.olympicstree.ui.MainCanvas;
import it.lab15.olympicstree.ui.tree.renderers.impl.BasicParticleRenderer;
import it.lab15.olympicstree.ui.utils.ProcessingUtils;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import processing.core.PFont;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.behaviors.AttractionBehavior;
import toxi.physics2d.behaviors.GravityBehavior;

public class ToxiClusterExperiment extends MainCanvas {

	private static final Logger LOG = LoggerFactory
			.getLogger(ToxiClusterExperiment.class);

	OTPhysics2D physics;
	BasicParticleRenderer particleRenderer;
	SimpleEditionParticleRenderer editionParticleRenderer;
	
	// squared snap distance for picking particles
	float snapDist=20*20;

	VerletParticle2D selected = null;

	PFont particleFont = loadFontResource("AppleGothic-18.vlw");
	
	public ToxiClusterExperiment() {
		super();
	}

	public void setup() {
		LOG.debug("setup called");
		
		super.setup();

		smooth();
		stroke(0);
		
		toxi.geom.Vec2D gravity = new toxi.geom.Vec2D(0.0f, 0.1f);
		physics = new OTPhysics2D();
		physics.setWorldBounds(new Rect(0, 0, width, height));
		physics.addBehavior(new GravityBehavior(gravity));
		physics.setDrag(0.3f);
		physics.clear();

		particleRenderer = new BasicParticleRenderer();
		editionParticleRenderer = new SimpleEditionParticleRenderer();
		
		loadData();
		LOG.debug("setup done");
	}

	@Override
	protected void setupSizeAndRender(){
		frameRate(60);
		size(1024, 768, OPENGL);
	}
	
	protected void loadData() {
		LOG.debug("loadData called");
		try {
			EditionFactory ef = new EditionFactory();
			List<Edition> allEditions = ef.loadAll();

			for (Edition edition : allEditions) {
				EditionParticle editionParticle = new EditionParticle(random(0,
						600f), random(0, 600f), edition);
				editionParticle.setWeight(2.9f);
				editionParticle.font = this.particleFont;
				physics.addBehavior(new AttractionBehavior(editionParticle, 20,
						-1.2f, 0.01f));
				physics.addParticle(editionParticle);
			}

		} catch (Exception ex) {
			LOG.error("loadData error: {}", ex.getMessage(), ex);
		}

	}

	public void drawContent() {
		LOG.trace("drawContent...");

		super.drawContent();

		physics.update();
		
		smooth();
		List<VerletParticle2D> particles = physics.particles;
		for (VerletParticle2D particle : particles) {
			if (particle instanceof EditionParticle){
				editionParticleRenderer.render((OTParticle) particle, this);
			} else {
				particleRenderer.render((OTParticle) particle, this);
			}
		}
		noSmooth();
	}

	// check all particles if mouse pos is less than snap distance
	public void mousePressed() {
		selected = null;
		//Vec2D mousePos = new Vec2D(mouseX, mouseY);
		Vec2D mousePos = new Vec2D(mouseX+camPos.x, mouseY+camPos.y);
		for (Iterator i = physics.particles.iterator(); i.hasNext();) {
			VerletParticle2D p = (VerletParticle2D) i.next();
			// if mouse is close enough, keep a reference to
			// the selected particle and lock it (becomes unmovable by physics)
			if (p.distanceToSquared(mousePos) < snapDist) {
				this.lockPan();
				selected = p;
				selected.lock();
				((OTParticle)p).selected = true;
				break;
			}
		}
		if (selected==null){
			this.unlockPan();
			super.mousePressed();
		}
	}

	// only react to mouse dragging events if we have a selected particle
	@Override
	public void mouseDragged() {
		if (selected != null) {
			selected.set(mouseX+camPos.x, mouseY+camPos.y);
		} else {
			super.mouseDragged();
		}
	}

	// if we had a selected particle unlock it again and kill reference
	public void mouseReleased() {
		if (selected != null) {
			this.unlockPan();
			((OTParticle)selected).selected = false;
			selected.unlock();
			selected = null;
		} else {
			super.mouseReleased();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.info("Starting Dendril {}...", AppDefines.APPLICATION_NAME);
		ProcessingUtils.runSketch(ToxiClusterExperiment.class);
	}

}
