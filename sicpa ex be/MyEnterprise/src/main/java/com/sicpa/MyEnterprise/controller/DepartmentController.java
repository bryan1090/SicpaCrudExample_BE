package com.sicpa.MyEnterprise.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicpa.MyEnterprise.model.Department;
import com.sicpa.MyEnterprise.repo.IDepartmentRepo;
import com.sicpa.MyEnterprise.util.ResponseBase;
import com.sicpa.MyEnterprise.util.catalogoErrores;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {
	@Autowired
	private IDepartmentRepo departmentRepo;// repositorio

	@GetMapping("/list")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ResponseBase> list() {
		// método que lista las empresas filtrando las que tenga estado "ACTIVO"
		List<Department> listObj = departmentRepo.findAll();
		listObj = listObj.stream().filter(e -> e.getStatus().equalsIgnoreCase("ACTIVO"))
				.collect(Collectors.toList());
		if (listObj.isEmpty()) {
			return new ResponseEntity<ResponseBase>(
					new ResponseBase(LocalDateTime.now(), catalogoErrores.NOT_FOUND.getCode(),
							catalogoErrores.NOT_FOUND.getMessage(), HttpStatus.OK.value(), listObj),
					HttpStatus.OK);
		}

		return new ResponseEntity<ResponseBase>(new ResponseBase(LocalDateTime.now(), catalogoErrores.OK.getCode(),
				catalogoErrores.OK.getMessage(), HttpStatus.OK.value(), listObj), HttpStatus.OK);
	}

	@PostMapping("/create")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ResponseBase> insert(@RequestBody Department department) {
		// método que crea une nueva empresa, con estado activo
		department.setCreatedBy("admin");
		department.setCreatedDate(LocalDateTime.now());
		department.setStatus("ACTIVO");
		Department entSaved = departmentRepo.save(department);
		if (entSaved == null) {
			return new ResponseEntity<ResponseBase>(
					new ResponseBase(LocalDateTime.now(), catalogoErrores.NOT_SAVED.getCode(),
							catalogoErrores.NOT_SAVED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Department> listNewEnt = new ArrayList<>();
		listNewEnt.add(entSaved);
		return new ResponseEntity<ResponseBase>(new ResponseBase(LocalDateTime.now(), catalogoErrores.OK.getCode(),
				catalogoErrores.OK.getMessage(), HttpStatus.OK.value(), listNewEnt), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseBase> update(@RequestBody Department department) {
		// método que actualiza los datos de la empresa.
		department.setModifiedBy("admin");
		department.setModifiedDate(LocalDateTime.now());
		department.setStatus("ACTIVO");
		Department entUpdated = departmentRepo.save(department);
		if (entUpdated == null) {
			return new ResponseEntity<ResponseBase>(
					new ResponseBase(LocalDateTime.now(), catalogoErrores.NOT_UPDATED.getCode(),
							catalogoErrores.NOT_UPDATED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Department> listNewEnt = new ArrayList<>();
		listNewEnt.add(entUpdated);
		return new ResponseEntity<ResponseBase>(new ResponseBase(LocalDateTime.now(), catalogoErrores.OK.getCode(),
				catalogoErrores.OK.getMessage(), HttpStatus.OK.value(), listNewEnt), HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<ResponseBase> delete(@RequestBody Department department) {
		// método para eliminaciuón lógica

		department.setModifiedBy("admin");
		department.setModifiedDate(LocalDateTime.now());
		department.setStatus("INACTIVO");
		departmentRepo.save(department);
		Department entUpdated = departmentRepo.getReferenceById(department.getIdDepartment());
		entUpdated = entUpdated.getStatus() == "INACTIVO" ? null : entUpdated;

		if (entUpdated != null) {
			return new ResponseEntity<ResponseBase>(
					new ResponseBase(LocalDateTime.now(), catalogoErrores.NOT_DELETED.getCode(),
							catalogoErrores.NOT_DELETED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseBase>(new ResponseBase(LocalDateTime.now(), catalogoErrores.OK.getCode(),
				catalogoErrores.OK.getMessage(), HttpStatus.OK.value(), null), HttpStatus.OK);

	}

	@PostMapping("/deleteHARD")
	public void deleteHARD(@RequestBody Department department) {
		departmentRepo.delete(department);
	}

	@GetMapping("/listHARD")
	public List<Department> listHARD() {
		return departmentRepo.findAll();
	}
}
