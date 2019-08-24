package com.lab.manus.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab.manus.entity.SubFormNames;
import com.lab.manus.repository.FormRepo;
import com.lab.manus.repository.SubFormNamesRepo;
import com.lab.manus.util.FormEntity;
import com.lab.manus.util.FormHandler;

@Service
@Transactional
public class FormService {
    
	@Autowired
	FormRepo formRepo;
	
	@Autowired
	SubFormNamesRepo SubFormNamesRepo;
	
	public FormEntity createForm(FormEntity formEntitiy) {
		return formRepo.saveAndFlush(formEntitiy);
	}
	
	public SubFormNames createFormName(SubFormNames subFormNames) {
		return SubFormNamesRepo.save(subFormNames);
	}
	
	public List<SubFormNames> getAllFormNames() {
		return SubFormNamesRepo.findAll();
	}
	
	public List<FormEntity> getFormFieldsByFormId(Long formId) {
		return formRepo.getFormFieldsByFormId(formId);
	}
	
	public boolean saveFormEntity(HttpServletRequest request) {
		
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
    	
    	query+= colNames + " VALUES " + colVals;
    	
    	System.out.println(">>>insert Quey>>>>>  "+query);

    	FormHandler formHandler = new FormHandler();
		
		return formHandler.insertToDynamicTable(query);
	}
}
