package com.lab.manus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lab.manus.entity.FormEntity;

@Repository
public interface FormRepo extends JpaRepository<FormEntity ,Long> {
	
	@Query("SELECT fe FROM FormEntity fe where fe.subFormNames.formId = :form_id") 
	List<FormEntity> getFormFieldsByFormId(@Param("form_id") Long id);
}
