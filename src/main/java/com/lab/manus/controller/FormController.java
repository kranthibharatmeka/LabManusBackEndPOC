package com.lab.manus.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.lab.manus.entity.FormEntities;
import com.lab.manus.entity.SubFormNames;
import com.lab.manus.service.FormService;
import com.lab.manus.util.FormEntity;
import com.lab.manus.util.FormHandler;

@Controller
public class FormController {
	
	@Autowired
	FormService formService;
	
	@RequestMapping(value="/dynamicform", method=RequestMethod.POST)
	public String createForm(@ModelAttribute FormEntities formEntities, Model model) {
		FormEntities savedFormEntities = new FormEntities();
		List<FormEntity> formEntityList = new ArrayList<FormEntity>();
		SubFormNames subFormNames = new SubFormNames();
		String formTitle = formEntities.getFormEntityList().get(0).getSubFormNames().getFormName();
		formTitle = formTitle.replaceAll(" ", "_");
		subFormNames.setFormName(formTitle);
		SubFormNames createdNewSubFormName = formService.createFormName(subFormNames);
		for(FormEntity formEntity : formEntities.getFormEntityList()) {
			formEntity.setSubFormNames(createdNewSubFormName);
			FormEntity savedformEntity  = formService.createForm(formEntity);
			formEntityList.add(savedformEntity);
		}
		
		savedFormEntities.setFormEntityList(formEntityList);
		model.addAttribute("savedFormEntities", savedFormEntities);	
		
		
		System.out.println("----------------------------------------");
		for(FormEntity formEntity : formEntityList) {
			
			System.out.println(formEntity.getFieldName().replaceAll(" ", "_") +"  |  "+ formEntity.getFieldType());
		}
		System.out.println("----------------------------------------");
		
		FormHandler formHandler = new FormHandler();
		
		formHandler.createDynamicTable(formTitle, formEntityList);
		
		return "Success";
	}
	
    @GetMapping("/form")
    public String getFormEntity(@ModelAttribute FormEntities formEntities, FormEntity formEntity, SubFormNames subFormNames, Model model) {
		formEntity.setSubFormNames(subFormNames);
		formEntities.setFormEntityList(Arrays.asList(formEntity));
		model.addAttribute("formEntities", formEntities);
    	return "DynamicForm";
    }
    
    @PostMapping("/saveDynamicForm")
    public String saveFormEntity(HttpServletRequest request, Model model) {
    	
    	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    	String tabName = request.getParameter("form_name");
    	String query = "INSERT INTO "+ tabName + " "; 
    	String colNames = "( ";
    	String colVals = "( ";
    	
    	for(String paramName : request.getParameterMap().keySet()) {
    		
    		if( !paramName.equalsIgnoreCase("form_name") ) {
    			colNames+= paramName + ",";    			
    			String temp = request.getParameter(paramName);    					
    			if(temp.equalsIgnoreCase("on")) {
    				temp = "1";
    			}else {
    				temp = "'" + temp + "'";
    			}    					
        		colVals+= temp + ",";	
    		}
    	}
    	
    	colNames = colNames.substring(0, colNames.length() - 1) + " )";
    	colVals = colVals.substring(0, colVals.length() - 1) + " )";
    	
    	query+= colNames + "VALUES " + colVals;
    	
    	System.out.println(">>>insert Quey>>>>>  "+query);

    	FormHandler formHandler = new FormHandler();
		
		formHandler.insertToDynamicTable(query);
		
    	return "Success";
    }
}
