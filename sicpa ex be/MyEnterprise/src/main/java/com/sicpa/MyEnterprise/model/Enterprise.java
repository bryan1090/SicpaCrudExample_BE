package com.sicpa.MyEnterprise.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "enterprises", schema = "enterprise")
public class Enterprise {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_enterprise")
	private BigInteger idEnterprise;
	
//	@OneToMany(mappedBy = "enterprise")
//	List<Department> departments;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "status",length = 20)
	private String status;

	@Column(name = "address",length = 50)
	private String address;

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
	
	public Enterprise() {
		super();
	}

	public Enterprise(BigInteger idEnterprise, LocalDateTime createdDate, String status, String address, String name,
			String phone, String createdBy, String modifiedBy, LocalDateTime modifiedDate) {
		super();
		this.idEnterprise = idEnterprise;
		this.createdDate = createdDate;
		this.status = status;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public BigInteger getIdEnterprise() {
		return idEnterprise;
	}

	public void setIdEnterprise(BigInteger idEnterprise) {
		this.idEnterprise = idEnterprise;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

//	public List<Department> getDepartments() {
//		return departments;
//	}
//
//	public void setDepartments(List<Department> departments) {
//		this.departments = departments;
//	}
//	
	

}
