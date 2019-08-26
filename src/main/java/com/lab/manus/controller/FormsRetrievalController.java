package com.lab.manus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lab.manus.entity.FormEntities;
import com.lab.manus.entity.FormEntity;
import com.lab.manus.entity.SubFormNames;
import com.lab.manus.service.FormService;

@Controller
public class FormsRetrievalController {

	@Autowired
	FormService formService;

	@GetMapping(value="/getAllFormNames")
	public String getAllForms(Model model) {
		List<SubFormNames> allFormNames = formService.getAllFormNames();
		model.addAttribute("allFormNames", allFormNames);		
		return "ShowAllFormNames";
	}
	
	@GetMapping(value="/getAllFormForEdit")
	public String getAllFormsForEdit(Model model) {
		List<SubFormNames> allFormNames = formService.getAllFormNames();
		model.addAttribute("allFormNames", allFormNames);		
		return "ShowAllFormsForEdit";
	}
	
	@GetMapping(path="/getForm/{form_id}")
	public String getRequestedForm(Model model, @PathVariable("form_id") Long formId) {
		List<FormEntity> requestedFormFieldsByFormId = formService.getFormFieldsByFormId(formId);
		model.addAttribute("requestedFormFields", requestedFormFieldsByFormId);		
		return "ShowRequestedForm";
	}
	
	@GetMapping(path="/getFormInEditMode/{form_id}")
	public String getFormInEditMode(Model model, @PathVariable("form_id") Long formId, FormEntities formEntities, FormEntity formEntity, SubFormNames subFormNames) {
		List<FormEntity> requestedFormFieldsByFormId = formService.getFormFieldsByFormId(formId);
		formEntities.setFormEntityList(requestedFormFieldsByFormId);
		model.addAttribute("formEntities", formEntities);
		return "DynamicForm";
	}
	
}
