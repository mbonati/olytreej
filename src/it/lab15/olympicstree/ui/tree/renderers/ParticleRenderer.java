package it.lab15.olympicstree.ui.tree.renderers;

import it.lab15.olympicstree.core.physics.OTParticle;
import it.lab15.olympicstree.ui.commons.BasicCanvas;

public interface ParticleRenderer {

	public void render(OTParticle particle, BasicCanvas canvas);
	
}
