package com.lab.manus.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.lab.manus.entity.FormEntities;
import com.lab.manus.entity.FormEntity;
import com.lab.manus.entity.SubFormNames;
import com.lab.manus.service.FormService;
import com.lab.manus.util.FormHandler;

@Controller
public class FormController {
	
	@Autowired
	FormService formService;
	
	@PostMapping("/dynamicform")
	public String createForm(@ModelAttribute FormEntities formEntities) {
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
		
		for(FormEntity formEntity : formEntityList) {
			System.out.println(formEntity.getFieldName().replaceAll(" ", "_") +"  |  "+ formEntity.getFieldType());
		}
		
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
    	formService.saveFormEntity(request);
    	return "Success";
    }
}
