package com.serviceorder.api.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class RemoveAccentsUtil {
	
	public static String removeAccents(String value) {
		
	    String normalizer = Normalizer.normalize(value, Normalizer.Form.NFD);
	    
	    Pattern pattern = Pattern.compile("[^\\p{ASCII}]");
	    
	    return pattern.matcher(normalizer).replaceAll("");
	}
}
