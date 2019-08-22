package com.lab.manus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lab.manus.entity.FormEntity;
import com.lab.manus.entity.SubFormNames;
import com.lab.manus.service.FormService;

import net.bytebuddy.implementation.bind.annotation.BindingPriority;

@Controller
public class FormsRetrievalController {

	@Autowired
	FormService formService;

	@RequestMapping(value="/getAllFormNames", method=RequestMethod.GET)
	public String getAllForms(Model model) {
		List<SubFormNames> allFormNames = formService.getAllFormNames();
		model.addAttribute("allFormNames", allFormNames);		
		return "ShowAllFormNames";
	}
	
	@RequestMapping(path="/getForm/{form_id}", method=RequestMethod.GET)
	public String getRequestedForm(Model model, @PathVariable("form_id") Long formId) {
		List<FormEntity> requestedFormFieldsByFormId = formService.getFormFieldsByFormId(formId);
		model.addAttribute("requestedFormFields", requestedFormFieldsByFormId);		
		return "ShowRequestedForm";
	}
	
}
