package fr.eni.clinique.config;

import java.io.IOException;
import java.util.Properties;

import fr.eni.clinique.common.AppConstants;
import fr.eni.clinique.common.exception.TechnicalException;

public class AppConfig {
	 private static final Properties PROPERTIES = new Properties();
	    
	    /**
	     * We Want to load at App startup, no LazyLoading.
	     */
	    static {

	        try {
	            PROPERTIES.load(AppConfig.class.getClassLoader()
	                    .getResourceAsStream(AppConstants.APP_PROPERTIES_NAME));
	            
	        } catch (IOException e) {
	            throw new TechnicalException("Could not load properties", e);
	        }
	    }
	    
	    /**
	     * Get the value of the application.properties correspondig to the key
	     * @param key The Key
	     * @return The searched value.
	     */
	    public static String get(String key) {
	        return PROPERTIES.getProperty(key);
	    }
}
