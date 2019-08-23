package com.lab.manus.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.lab.manus.entity.FormEntity;
import com.lab.manus.entity.FormEntities;
import com.lab.manus.entity.SubFormNames;
import com.lab.manus.service.FormService;

@Controller
public class FormController {
	
	@Autowired
	FormService formService;

	@RequestMapping(value="/dynamicform", method=RequestMethod.POST)
	public String createForm(@ModelAttribute FormEntities formEntities, Model model) {
		FormEntities savedFormEntities = new FormEntities();
		List<FormEntity> formEntityList = new ArrayList<FormEntity>();
		SubFormNames subFormNames = new SubFormNames();
		subFormNames.setFormName(formEntities.getFormEntityList().get(0).getSubFormNames().getFormName());
		SubFormNames createdNewSubFormName = formService.createFormName(subFormNames);
		for(FormEntity formEntity : formEntities.getFormEntityList()) {
			formEntity.setSubFormNames(createdNewSubFormName);
			FormEntity savedformEntity  = formService.createForm(formEntity);
			formEntityList.add(savedformEntity);
		}
		
		savedFormEntities.setFormEntityList(formEntityList);
		model.addAttribute("savedFormEntities", savedFormEntities);		
		
		return "Success";
	}
	
    @GetMapping("/form")
    public String getFormEntity(@ModelAttribute FormEntities formEntities, FormEntity formEntity, SubFormNames subFormNames, Model model) {
		formEntity.setSubFormNames(subFormNames);
		formEntities.setFormEntityList(Arrays.asList(formEntity));
		model.addAttribute("formEntities", formEntities);
    	return "DynamicForm";
    }
}
