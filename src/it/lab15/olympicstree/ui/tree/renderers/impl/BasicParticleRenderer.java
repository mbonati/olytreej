package it.lab15.olympicstree.ui.tree.renderers.impl;

import it.lab15.olympicstree.core.physics.OTParticle;
import it.lab15.olympicstree.ui.commons.BasicCanvas;
import it.lab15.olympicstree.ui.tree.renderers.ParticleRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicParticleRenderer implements ParticleRenderer {

	private static final Logger LOG = LoggerFactory
			.getLogger(BasicParticleRenderer.class);

	@Override
	public void render(OTParticle particle, BasicCanvas canvas) {
		LOG.trace("rendering ({},{},{})...", new Object[]{particle.position().x(), particle.position().y(), particle.bean});
		canvas.smooth();
		canvas.fill(100, 120, 255);
		canvas.noStroke();
		canvas.ellipse(particle.position().x(), particle.position().y(), 20, 20);
		canvas.noSmooth();
		LOG.trace("rendered.");
	}

}
