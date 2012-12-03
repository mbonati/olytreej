package it.lab15.olympicstree.core.physics;

import toxi.physics2d.VerletPhysics2D;

public class OTPhysics2D extends VerletPhysics2D {

	public OTPhysics2D addParticle(OTParticle partilce){
		super.addParticle(partilce);
		return this;
	}
	
}
