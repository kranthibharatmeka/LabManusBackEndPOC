package com.lab.manus.entity;

import java.util.Map;
import java.util.HashMap;

public class FieldMapper {
	
	Map<String, String> fieldsMap = new HashMap<String, String>();
	
	public FieldMapper() {
		
		fieldsMap.put("r", "int");
		fieldsMap.put("c", "char");
		fieldsMap.put("t", "VARCHAR(50)");
		fieldsMap.put("dateType", "date");
		fieldsMap.put("decimalNumberType", "numeric");
	}
	 
	
	public String getDBField(String formFieldType) {
		
		if(fieldsMap.containsKey(formFieldType)) {
			return fieldsMap.get(formFieldType);
		}
		
		return null;
	}

}
