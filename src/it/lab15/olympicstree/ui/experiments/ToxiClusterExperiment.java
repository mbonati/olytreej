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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.behaviors.AttractionBehavior;
import toxi.physics2d.behaviors.GravityBehavior;

public class ToxiClusterExperiment extends MainCanvas {

	private static final Logger LOG = LoggerFactory.getLogger(ToxiClusterExperiment.class);

	OTPhysics2D physics;
	BasicParticleRenderer particleRenderer;
	private boolean displayed;
	
	public ToxiClusterExperiment() {
		super();
	}

	public void setup() {
		LOG.debug("setup called");
		super.setup();

		smooth();
		stroke(0);

		toxi.geom.Vec2D gravity = new toxi.geom.Vec2D (0.0f,0.3f);
		physics = new OTPhysics2D();
		physics.setWorldBounds(new Rect(0, 0, width, height));
		physics.addBehavior(new GravityBehavior(new Vec2D(0, 0.1f)));
		physics.setDrag(0.05f);
		physics.clear();

		particleRenderer = new BasicParticleRenderer();
		
		loadData();
		LOG.debug("setup done");
	}

	protected void loadData(){
		LOG.debug("loadData called");
		try {
			EditionFactory ef = new EditionFactory();
			List<Edition> allEditions = ef.loadAll();
			
			for (Edition edition:allEditions){
				EditionParticle editionParticle = new EditionParticle(random(0,600f),random(0,600f), edition);
				editionParticle.setWeight(0.9f);
				physics.addBehavior(new AttractionBehavior(editionParticle, 20, -1.2f, 0.01f));
				physics.addParticle(editionParticle);
			}
			
		} catch (Exception ex){
			LOG.error("loadData error: {}", ex.getMessage(), ex);
		}
		
	}
	
	public void drawContent() {
		LOG.trace("drawContent...");
		
		super.drawContent();

		physics.update();
		
		List<VerletParticle2D> particles = physics.particles;
		for (VerletParticle2D particle:particles){
			particleRenderer.render((OTParticle)particle, this);
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
