package com.lab.manus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.lab.manus.entity.FormEntity;
import com.lab.manus.entity.SubFormNames;
import com.lab.manus.service.FormService;

@Controller
public class FormController {
	
	@Autowired
	FormService formService;

	@RequestMapping(value="/dynamicform", method=RequestMethod.POST)
	public String createForm(@ModelAttribute FormEntity formEntitiy, Model model) {
		
		SubFormNames subFormNames = new SubFormNames();
		subFormNames.setFormName(formEntitiy.getSubFormNames().getFormName());
		SubFormNames createdNewSubFormName = formService.createFormName(subFormNames);

		formEntitiy.setSubFormNames(createdNewSubFormName);		
		model.addAttribute("formentity", formEntitiy);		
		formService.createForm(formEntitiy);
		
		return "Success";
	}
	
	@RequestMapping("/form")
	public ModelAndView firstPage(Model model) {
		SubFormNames subFormNames = new SubFormNames();
		FormEntity formEntity = new FormEntity();
		formEntity.setSubFormNames(subFormNames);
		model.addAttribute("formentity", formEntity);
		return new ModelAndView("DynamicForm");
	}
}
