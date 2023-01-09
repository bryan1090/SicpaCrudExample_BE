package com.sicpa.MyEnterprise.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments", schema = "enterprise")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_department")
	private BigInteger idDepartment;

	@OneToMany(mappedBy = "department")
	List<DepartmentEmployee> dep_emp;
	
    @ManyToOne
    @JoinColumn(name="id_enterprise", nullable=false)
	private Enterprise enterprise;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "status",length = 20)
	private String status;

	@Column(name = "description",length = 50)
	private String description;

	@Column(name = "name",length = 20)
	private String name;

	@Column(name = "phone",length = 20)
	private String phone;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;
	
	public Department() {
		super();
	}
	

	public BigInteger getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(BigInteger idDepartment) {
		this.idDepartment = idDepartment;
	}

	

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	


	public Enterprise getEnterprise() {
		return enterprise;
	}


	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}


	public List<DepartmentEmployee> getDep_emp() {
		return dep_emp;
	}


	public void setDep_emp(List<DepartmentEmployee> dep_emp) {
		this.dep_emp = dep_emp;
	}

}
