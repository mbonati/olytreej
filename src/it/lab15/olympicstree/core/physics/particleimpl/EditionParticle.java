package it.lab15.olympicstree.core.physics.particleimpl;

import it.lab15.olympicstree.core.data.beans.Edition;
import it.lab15.olympicstree.core.physics.OTParticle;

public class EditionParticle extends OTParticle<Edition> {

	public EditionParticle(float mass, Edition bean) {
		super(mass, bean);
	}

	public EditionParticle(float mass, float x, float y, float z, Edition bean) {
		super(mass, bean);
		this.position.set(x, y, z);
	}
	
	

}
