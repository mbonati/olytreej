package it.lab15.olympicstree.ui.experiments;

import it.lab15.olympicstree.AppDefines;
import it.lab15.olympicstree.core.data.beans.Edition;
import it.lab15.olympicstree.core.data.beans.EditionFactory;
import it.lab15.olympicstree.core.physics.OTParticle;
import it.lab15.olympicstree.core.physics.OTParticleSystem;
import it.lab15.olympicstree.core.physics.particleimpl.EditionParticle;
import it.lab15.olympicstree.ui.MainCanvas;
import it.lab15.olympicstree.ui.commons.BasicCanvas;
import it.lab15.olympicstree.ui.tree.renderers.impl.BasicParticleRenderer;
import it.lab15.olympicstree.ui.utils.ProcessingUtils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import traer.physics.Particle;

public class FirstClusterExperiment extends MainCanvas {

	private static final Logger LOG = LoggerFactory.getLogger(FirstClusterExperiment.class);

	OTParticleSystem physics;
	BasicParticleRenderer particleRenderer;
	
	public FirstClusterExperiment() {
		super();
	}

	public void setup() {
		super.setup();

		smooth();
		stroke(0);
		
		physics = new OTParticleSystem(0.0f, 0.05f);
	
		particleRenderer = new BasicParticleRenderer(){
			@Override
			public void draw(OTParticle particle, BasicCanvas canvas) {
				super.draw(particle, canvas);
			}
		};
		
		loadData();
	}

	protected void loadData(){
		LOG.error("loadData called");
		try {
			EditionFactory ef = new EditionFactory();
			List<Edition> allEditions = ef.loadAll();
			
			for (Edition edition:allEditions){
				EditionParticle editionParticle = new EditionParticle(0, edition);
				physics.addParticle(editionParticle);
			}
			
		} catch (Exception ex){
			LOG.error("loadData error: {}", ex.getMessage(), ex);
		}
		
	}
	
	public void drawContent() {
		super.drawContent();
		
		physics.tick();
		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.info("Starting Dendril {}...", AppDefines.APPLICATION_NAME);
		ProcessingUtils.runSketch(FirstClusterExperiment.class);
	}

}
