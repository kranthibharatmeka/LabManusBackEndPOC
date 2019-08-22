package com.lab.manus.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sub_form_names")
public class SubFormNames {
	
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "form_id", unique = true, nullable = false) 
	private Long formId;
    
	@Column(name = "form_name", nullable = false)
	private String formName;
	
	public Long getFormId() {
		return formId;
	}
	public void setFormId(Long formId) {
		this.formId = formId;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(formId, formName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SubFormNames)) {
			return false;
		}
		SubFormNames other = (SubFormNames) obj;
		return Objects.equals(formId, other.formId) && Objects.equals(formName, other.formName);
	}
	
}
