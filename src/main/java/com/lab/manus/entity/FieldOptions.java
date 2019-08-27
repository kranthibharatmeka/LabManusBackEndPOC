package com.lab.manus.entity;

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

@Entity
@Table(name = "field_options")
public class FieldOptions {
  
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_entity_id", nullable = false)
    private FormEntity formEntity;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_Id", nullable = false)
    private SubFormNames subFormNames;
	
	@Column(name = "field_type")
	private String fieldType;
	
	@Column(name = "options")
	private String options;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FormEntity getFormEntity() {
		return formEntity;
	}

	public void setFormEntity(FormEntity formEntity) {
		this.formEntity = formEntity;
	}

	public SubFormNames getSubFormNames() {
		return subFormNames;
	}

	public void setSubFormNames(SubFormNames subFormNames) {
		this.subFormNames = subFormNames;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fieldType, formEntity, id, options, subFormNames);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FieldOptions)) {
			return false;
		}
		FieldOptions other = (FieldOptions) obj;
		return Objects.equals(fieldType, other.fieldType) && Objects.equals(formEntity, other.formEntity)
				&& Objects.equals(id, other.id) && Objects.equals(options, other.options)
				&& Objects.equals(subFormNames, other.subFormNames);
	}
	
}
