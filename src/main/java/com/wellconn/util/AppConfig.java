/**
 * 
 */
package com.wellconn.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;



/**
 * @author doil
 *
 */
public class AppConfig {
	private Properties properties = new Properties();

    public AppConfig(String resourcePath) {
    	 try (Reader reader = Resources.getResourceAsReader(resourcePath)) {
             properties.load(reader);
         } catch (IOException ex) {
             ex.printStackTrace();
         }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public String getProperty(String key, String defaultV) {
		String value = properties.getProperty(key);
		if(value == null) value = defaultV;
		
		return value;
	}

}
