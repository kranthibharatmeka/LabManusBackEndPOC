package com.lab.manus.entity;

import java.util.Map;
import java.util.HashMap;

public class FieldMapper {
	
	Map<String, String> fieldsMap = new HashMap<String, String>();
	
	public FieldMapper() {
		
		fieldsMap.put("r", "VARCHAR(50)");
		fieldsMap.put("c", "VARCHAR(50)");
		fieldsMap.put("t", "VARCHAR(50)");
		fieldsMap.put("d", "date");
		fieldsMap.put("e", "VARCHAR(50)");
		fieldsMap.put("ta", "VARCHAR(100)");
		fieldsMap.put("n", "numeric");
	}
	 
	
	public String getDBField(String formFieldType) {
		
		if(fieldsMap.containsKey(formFieldType)) {
			return fieldsMap.get(formFieldType);
		}
		
		return null;
	}

}
