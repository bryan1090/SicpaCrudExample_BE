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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees", schema = "enterprise")
public class Employee {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_employees")
	private BigInteger idEmployee;

	@OneToMany(mappedBy = "employee")
	List<DepartmentEmployee> dep_emp;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "status",length = 20)
	private String status;

	@Column(name = "age",length = 2)
	private String age;

	@Column(name = "email",length = 20)
	private String email;

	@Column(name = "name",length = 20)
	private String name;

	@Column(name = "position",length = 20)
	private String position;

	@Column(name = "surname",length = 20)
	private String surname;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;
	
	public Employee() {
		super();
	}

	public Employee(BigInteger idEmployee, LocalDateTime createdDate, String status, String age, String email,
			String name, String position, String surname, String createdBy, String modifiedBy,
			LocalDateTime modifiedDate) {
		super();
		this.idEmployee = idEmployee;
		this.createdDate = createdDate;
		this.status = status;
		this.age = age;
		this.email = email;
		this.name = name;
		this.position = position;
		this.surname = surname;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public BigInteger getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(BigInteger idEmployee) {
		this.idEmployee = idEmployee;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public List<DepartmentEmployee> getDep_emp() {
		return dep_emp;
	}

	public void setDep_emp(List<DepartmentEmployee> dep_emp) {
		this.dep_emp = dep_emp;
	}

}
