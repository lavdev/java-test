package br.com.lav.java.test.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 * Util singleton class
 * @author laverson
 *
 */
public class Util {
	
	private final ObjectMapper mapper = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);
	private static Util instance;
	
	protected Util() {}
	
	/***
	 * Get Util's instance
	 * @return
	 */
	public static Util getInstance() {
		if(instance == null) {
			instance = new Util();
		}
		return instance;
	}
	
	public ObjectMapper jsonMapper() {
		return mapper;
	}
	
    /***
     * Get json data from file
     * @param file
     * @return JsonNode
     */
    public JsonNode readJsonFile(String file) {    	
    	ClassPathResource res = new ClassPathResource(file);    
    	File f = new File(res.getPath());
    	if(f.exists()) {
    		try {
    			return jsonMapper().readTree(f);
    		}catch(IOException ex) {
    			LOGGER.warn("Error reading file {} {} ", file, ex.getLocalizedMessage());    		    
			}
    	}
    	return null;
    }	
}
