package com.lab.manus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.manus.entity.FormEntitiy;
import com.lab.manus.repository.FormRepo;
@CrossOrigin
@RestController
public class FormController {
	
	@Autowired
	FormRepo formRepo;

	@PostMapping("/create")
	public FormEntitiy createForm(@  RequestBody FormEntitiy formEntitiy) {
		return formRepo.save(formEntitiy);
	}
}
