package it.lab15.olympicstree.ui.experiments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.lab15.olympicstree.core.physics.OTParticle;
import it.lab15.olympicstree.core.physics.particleimpl.EditionParticle;
import it.lab15.olympicstree.ui.commons.BasicCanvas;
import it.lab15.olympicstree.ui.tree.renderers.ParticleRenderer;
import it.lab15.olympicstree.ui.tree.renderers.impl.BasicParticleRenderer;

public class SimpleEditionParticleRenderer implements ParticleRenderer {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleEditionParticleRenderer.class);

	@Override	
	public void render(OTParticle particle, BasicCanvas canvas) {
		LOG.trace("rendering ({},{},{})...", new Object[] { particle.x, particle.y, particle.bean });
		
		EditionParticle editionParticle = (EditionParticle)particle;
		canvas.fill(100, 120, 255);
		canvas.stroke(particle.selected ? 0xff00ffff : 0xff000000);
		canvas.ellipse(editionParticle.x(), editionParticle.y(), editionParticle.getSizeX(), editionParticle.getSizeY());
		
		String hostCity = editionParticle.bean.getHostCity();
		canvas.fill(200,255,230);
		canvas.textFont(editionParticle.font);
		canvas.text(hostCity, editionParticle.x(), editionParticle.y()-13);

		
		LOG.trace("rendered.");
	}

}
