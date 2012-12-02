package it.lab15.olympicstree.ui;

import it.lab15.olympicstree.AppDefines;
import it.lab15.olympicstree.ui.utils.ProcessingUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApplication {

	private static final Logger LOG = LoggerFactory.getLogger(MainApplication.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.info("Starting {}...", AppDefines.APPLICATION_NAME);
		ProcessingUtils.runSketch(MainCanvas.class);
	}

}
