package it.lab15.olympicstree.core.physics;

import it.lab15.olympicstree.core.data.beans.Bean;

import java.util.HashMap;
import java.util.Map;

import traer.physics.Particle;

public class OTParticle<T extends Bean> extends Particle {

	public Map<String,Object> properties = new HashMap<String,Object>();
	public T bean;
	
	public OTParticle(float mass, T bean) {
		super(mass);
		this.bean = bean;
	}

	public OTParticle(float mass) {
		super(mass);
	}

}
