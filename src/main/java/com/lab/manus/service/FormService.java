package com.lab.manus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab.manus.entity.SubFormNames;
import com.lab.manus.repository.FormRepo;
import com.lab.manus.repository.SubFormNamesRepo;
import com.lab.manus.util.FormEntity;

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
}
