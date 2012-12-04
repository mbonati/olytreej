package it.lab15.olympicstree.core.physics.particleimpl;

import it.lab15.olympicstree.core.data.beans.Edition;
import it.lab15.olympicstree.core.physics.OTParticle;
import processing.core.PFont;

public class EditionParticle extends OTParticle<Edition> {
	
	private float radius;
	public PFont font;
	
	public EditionParticle(float x, float y, Edition bean) {
		super(x, y, bean);
		this.radius = (float) (15 + (Math.random() * 55));
	}

	@Override
	public float getSizeX() {
		return radius;
	}

	@Override
	public float getSizeY() {
		return radius;
	}

}
