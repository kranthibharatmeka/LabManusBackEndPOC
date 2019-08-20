package com.lab.manus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.lab.manus.entity.FormEntitiy;
import com.lab.manus.repository.FormRepo;

@Controller
public class FormController {
	
	@Autowired
	FormRepo formRepo;

	@RequestMapping(value="/dynamicform", method=RequestMethod.POST)
	public String createForm(@ModelAttribute FormEntitiy formEntitiy,Model model) {
		model.addAttribute("formentitiy", new FormEntitiy());
		formRepo.save(formEntitiy);
		return "Success";
	}
	
	@RequestMapping("/form")
	public ModelAndView firstPage(Model model) {
		model.addAttribute("formentitiy", new FormEntitiy());
		return new ModelAndView("DynamicForm");
	}
}
