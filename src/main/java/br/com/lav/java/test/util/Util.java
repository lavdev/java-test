package br.com.lav.java.test.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 * Util singleton class
 * @author laverson
 *
 */
public class Util {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);
	private static Util instance;
	
	protected Util() {}

	static {
        // Json engine setup
        mapper.configure(Feature.IGNORE_UNDEFINED, true);
        mapper.configure(Feature.ALLOW_COMMENTS, true);
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(Feature.ALLOW_TRAILING_COMMA, true);

    }

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
    public JsonNode readJsonArrayFile(String file) {    	
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
	
    /***
     * Get json data from file
     * @param s
     * @return JsonNode
     */
    public JsonNode createJsonObjectFromString(String s) {
		try {
	    	JsonNode jsonNode = mapper.readTree(s);
	    	return jsonNode;
		}catch(IOException ex) {
			LOGGER.warn("Error ",ex.getLocalizedMessage());
		}
    	return null;
    }	
}
