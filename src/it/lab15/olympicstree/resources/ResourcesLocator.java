package it.lab15.olympicstree.resources;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourcesLocator {

	private static final Logger LOG = LoggerFactory.getLogger(ResourcesLocator.class);

	public static String getImageResourceURI(String filename){
		try {
			return getImageResourceURL(filename).toURI().toString();
//			URL url = ResourcesLocator.class.getResource("images/"+ filename);
//			return url.toURI().toString();
		} catch (Exception ex){
			LOG.error("getImageResourceURL error: {}", ex.getMessage(), ex);
			return filename;
		}
	}

	public static URL getImageResourceURL(String filename){
		try {
			URL url = ResourcesLocator.class.getResource("images/"+ filename);
			return url;
		} catch (Exception ex){
			LOG.error("getImageResourceURL error: {}", ex.getMessage(), ex);
			return null;
		}
	}

	public static URL getFontResourceURL(String fileName){
		try {
			URL url = ResourcesLocator.class.getResource("fonts/"+ fileName);
			return url;
		} catch (Exception ex){
			LOG.error("getFontResourceURL error: {}", ex.getMessage(), ex);
			return null;
		}
	}
	
	public static URL getResourceURL(String fileName){
		try {
			URL url = ResourcesLocator.class.getResource(fileName);
			return url;
		} catch (Exception ex){
			LOG.error("getResourceURL error: {}", ex.getMessage(), ex);
			return null;
		}
	}

}
