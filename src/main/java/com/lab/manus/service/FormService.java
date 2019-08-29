package com.lab.manus.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab.manus.entity.FieldOptions;
import com.lab.manus.entity.FormEntity;
import com.lab.manus.entity.SubFormNames;
import com.lab.manus.repository.FieldOptionsRepo;
import com.lab.manus.repository.FormRepo;
import com.lab.manus.repository.SubFormNamesRepo;
import com.lab.manus.util.FormHandler;

@Service
@Transactional
public class FormService {
    
	@Autowired
	FormRepo formRepo;
	
	@Autowired
	SubFormNamesRepo SubFormNamesRepo;
	
	@Autowired
	FieldOptionsRepo fieldOptionsRepo;
	
	public FormEntity createForm(FormEntity formEntity) {
		if(formEntity.getArchive()==null) {
			formEntity.setArchive("0");
		}
		return formRepo.saveAndFlush(formEntity);
	}
	
	public void createOptionsForFields(FormEntity formEntity) {
		String[]  options = formEntity.getOptions().split(",");
		for(String option : options) {
			FieldOptions fieldOption = new FieldOptions();
			fieldOption.setFormEntity(formEntity);
			fieldOption.setSubFormNames(formEntity.getSubFormNames());
			fieldOption.setFieldType(formEntity.getFieldType());
			fieldOption.setOptions(option);
			fieldOptionsRepo.saveAndFlush(fieldOption);
		}
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
    	String colNames = "(";
    	String colVals = "";
    	
    	for(String paramName : request.getParameterMap().keySet()) {
    		
    		if( !paramName.equalsIgnoreCase("form_name") ) {
    			colNames+= paramName + ",";    			
//    			String temp = request.getParameter(paramName);    					
    			String[] temp1 = request.getParameterValues(paramName);
    			
    			if(temp1.length > 1) {
    				String optionVals = "";
    				
        			for(String val : request.getParameterValues(paramName)) {
        				optionVals+=val+"-";
        			}    				
        			colVals+= optionVals + ",";
    			}else {
    				colVals+= temp1[0] + ",";	
    			}	
    		}
    	}
    	
    	colNames = colNames.substring(0, colNames.length() - 1) + ")";
//    	colVals = colVals.substring(0, colVals.length() - 1) + ")";
    	String forattedColVals = "(";
    	for(String str : colVals.split(",")) {
    		forattedColVals+= "'" + str + "',";
    		
    	}
    	forattedColVals = forattedColVals.substring(0, forattedColVals.length() - 1) + ")";
    	
    	query+= colNames + " VALUES " + forattedColVals;
    	
    	System.out.println(">>>insert Quey>>>>>  "+query);

    	FormHandler formHandler = new FormHandler();
		
		return formHandler.insertToDynamicTable(query);
	}
}
