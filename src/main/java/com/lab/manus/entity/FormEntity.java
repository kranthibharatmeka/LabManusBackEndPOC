package com.lab.manus.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "form_entity")
public class FormEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable=false, updatable=false)
	private Long id;
    
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_id", nullable = false)
    private SubFormNames subFormNames;
	
	@Column(name = "field_name")
	private String fieldName;
	
	@Column(name = "field_type")
	private String fieldType;
	
	@JoinColumns({
	   @JoinColumn(name="form_id", referencedColumnName="form_id"),
	   @JoinColumn(name="form_entity_id", referencedColumnName="id")
	})
	@OneToMany
	private List<FieldOptions> fieldOptions;
	
	
	public List<FieldOptions> getFieldOptions() {
		return fieldOptions;
	}
	public void setFieldOptions(List<FieldOptions> fieldOptions) {
		this.fieldOptions = fieldOptions;
	}
	@Column(name = "archive")
	private String archive;
	
	@Transient
	private String options;
	
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
	public String getArchive() {
		return archive;
	}
	public void setArchive(String archive) {
		this.archive = archive;
	}
	
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(archive, fieldName, fieldType, id, subFormNames);
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
		return Objects.equals(archive, other.archive) && Objects.equals(fieldName, other.fieldName)
				&& Objects.equals(fieldType, other.fieldType) && Objects.equals(id, other.id)
				&& Objects.equals(subFormNames, other.subFormNames);
	}
	
}
