package it.lab15.olympicstree.resources;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourcesLocator {

	private static final Logger LOG = LoggerFactory.getLogger(ResourcesLocator.class);

	public static String getImageResourceURL(String filename){
		try {
			URL url = ResourcesLocator.class.getResource("images/"+ filename);
			return url.toURI().toString();
		} catch (Exception ex){
			LOG.error("buildImageURL error: {}", ex.getMessage(), ex);
			return filename;
		}
	}

}
