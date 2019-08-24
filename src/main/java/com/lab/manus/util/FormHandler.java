package com.lab.manus.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lab.manus.entity.FieldMapper;



public class FormHandler {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	FieldMapper fieldMapper = new FieldMapper();
	 
	public boolean  createDynamicTable(String formName, List<FormEntity> formEntityList) {
		
		String fieldsDefPart = "";
		
		for(FormEntity entitiy : formEntityList) {
			
			String dbFieldType = fieldMapper.getDBField(entitiy.getFieldType());
			String fieldName = entitiy.getFieldName().replaceAll(" ", "_");
			
			if(dbFieldType != null) {
				fieldsDefPart+= fieldName +" "+ dbFieldType +" ";	
			}
			fieldsDefPart+= ",";
		}
		fieldsDefPart = fieldsDefPart.substring(0, fieldsDefPart.length()-1);
		
		String sql = "CREATE TABLE " + formName +" ("+ fieldsDefPart +")"; 
		
		System.out.println("-----QUERY----\n");
		System.out.println(sql);
		System.out.println("\n-----QUERY----");


		   ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		   
		   JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
		
		jdbcTemplate.execute(sql);
		
		return false;		
	}
	
	public boolean  insertToDynamicTable(String query) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		   
		JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
		
		jdbcTemplate.execute(query);
		
		return true;		
	}
	
	  
}
