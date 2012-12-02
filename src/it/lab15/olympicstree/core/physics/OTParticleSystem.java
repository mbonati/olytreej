package it.lab15.olympicstree.core.physics;

import it.lab15.olympicstree.core.data.beans.Bean;

import java.util.ArrayList;

import traer.physics.Particle;
import traer.physics.ParticleSystem;

public class OTParticleSystem<T extends Bean> extends ParticleSystem {

	protected ArrayList particlesInt;
	
	
	public OTParticleSystem(float g, float somedrag) {
		super(g, somedrag);
		initOT();
	}
	
	public OTParticleSystem(){
		super();
		initOT();
	}
	
	private void initOT(){
		
//		//Make partivles fields accessible (in Traer Physics is private!)
//		Field[] fields = this.getClass().getDeclaredFields();
//		for (Field field:fields){
//			if (field.getName().equals("particles")){
//			}
//		}

	}
	
	public OTParticleSystem addParticle(OTParticle partilce){
		particles.add(partilce);
		return this;
	}

}
