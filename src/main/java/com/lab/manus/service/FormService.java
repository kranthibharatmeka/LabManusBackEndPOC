package com.lab.manus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab.manus.entity.FormEntity;
import com.lab.manus.entity.SubFormNames;
import com.lab.manus.repository.FormRepo;
import com.lab.manus.repository.SubFormNamesRepo;

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
}
