package it.lab15.olympicstree.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chrono {
	
	private static final Logger LOG = LoggerFactory.getLogger(Chrono.class);

	private long startMS;
	private long stopMS;
	private long startNS;
	private long stopNS;
	private String tag;
	
	public Chrono(){
		start();
	}

	private String buildTagFromStack(){
		if (LOG.isDebugEnabled()){
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement lastMethod = stackTraceElements[4];
			return 	lastMethod.getClassName() + "." + lastMethod .getMethodName() + "[line:" + lastMethod.getLineNumber() +"]";
		} else {
			return "undefined";
		}
	}
	
	public Chrono start(){
		this.tag = 	buildTagFromStack();
		this.startMS = System.currentTimeMillis();
		this.startNS = System.nanoTime();
		return this;
	}

	public Chrono stop(){
		this.stopMS = System.currentTimeMillis();
		this.stopNS = System.nanoTime();
		LOG.debug("{}", this);
		return this;
	}
	
	public long getTimeNS(){
		return this.stopNS - this.startNS;
	}

	public long getTimeMS(){
		return this.stopMS - this.startMS;
	}
	
	public String toString(){
		return "Performances time [tag="+this.tag+"]: " + getTimeMS() +"ms ["+ getTimeNS() + "ns]";
	}

}
