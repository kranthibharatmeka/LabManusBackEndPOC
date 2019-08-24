package com.lab.manus.util;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lab.manus.entity.SubFormNames;

@Entity
@Table(name = "form_entity")
public class FormEntity {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_Id", nullable = false)
    private SubFormNames subFormNames;
	
	@Column(name = "field_name")
	private String fieldName;
	
	@Column(name = "field_type")
	private String fieldType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		
		this.fieldName = fieldName.replaceAll(" ", "_");
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public SubFormNames getSubFormNames() {
		return subFormNames;
	}
	public void setSubFormNames(SubFormNames subFormNames) {
		this.subFormNames = subFormNames;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fieldName, fieldType, subFormNames, id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FormEntity)) {
			return false;
		}
		
		FormEntity other = (FormEntity) obj;
		return Objects.equals(fieldName, other.fieldName) && Objects.equals(fieldType, other.fieldType)
				&& Objects.equals(subFormNames, other.subFormNames) && Objects.equals(id, other.id);
	}
	
	
	
}
