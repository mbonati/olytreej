package it.lab15.olympicstree.core.physics;

import it.lab15.olympicstree.core.data.beans.Bean;

import java.util.HashMap;
import java.util.Map;

import toxi.geom.ReadonlyVec2D;
import toxi.physics2d.VerletParticle2D;

public class OTParticle<T extends Bean> extends VerletParticle2D {

	public Map<String, Object> properties = new HashMap<String, Object>();
	public T bean;

	public OTParticle(float x, float y, float z, T bean) {
		super(x, y, z);
		this.bean = bean;
	}

	public OTParticle(float x, float y, T bean) {
		super(x, y);
		this.bean = bean;
	}

	public OTParticle(ReadonlyVec2D position, float weight, T bean) {
		super(position, weight);
		this.bean = bean;
	}

	public OTParticle(ReadonlyVec2D position, T bean) {
		super(position);
		this.bean = bean;
	}

	public OTParticle(VerletParticle2D copyFrom, T bean) {
		super(copyFrom);
		this.bean = bean;
	}

	
}
