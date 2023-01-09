package com.sicpa.MyEnterprise.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicpa.MyEnterprise.model.Employee;
import com.sicpa.MyEnterprise.repo.IEmployeeRepo;
import com.sicpa.MyEnterprise.util.ResponseBase;
import com.sicpa.MyEnterprise.util.catalogoErrores;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	@Autowired
	private IEmployeeRepo employeeRepo;// repositorio

	@GetMapping("/list")
	public ResponseEntity<ResponseBase> list() {
		// método que lista las empresas filtrando las que tenga estado "ACTIVO"
		List<Employee> listEnterprise = employeeRepo.findAll();
		listEnterprise = listEnterprise.stream().filter(e -> e.getStatus().equalsIgnoreCase("ACTIVO"))
				.collect(Collectors.toList());
		if (listEnterprise.isEmpty()) {
			return new ResponseEntity<ResponseBase>(
					new ResponseBase(LocalDateTime.now(), catalogoErrores.NOT_FOUND.getCode(),
							catalogoErrores.NOT_FOUND.getMessage(), HttpStatus.OK.value(), listEnterprise),
					HttpStatus.OK);
		}

		return new ResponseEntity<ResponseBase>(new ResponseBase(LocalDateTime.now(), catalogoErrores.OK.getCode(),
				catalogoErrores.OK.getMessage(), HttpStatus.OK.value(), listEnterprise), HttpStatus.OK);
	}

	@PostMapping("/new")
	public ResponseEntity<ResponseBase> insert(@RequestBody Employee employee) {
		// método que crea une nueva empresa, con estado activo
		employee.setCreatedBy("admin");
		employee.setCreatedDate(LocalDateTime.now());
		employee.setStatus("ACTIVO");
		Employee entSaved = employeeRepo.save(employee);
		if (entSaved == null) {
			return new ResponseEntity<ResponseBase>(
					new ResponseBase(LocalDateTime.now(), catalogoErrores.NOT_SAVED.getCode(),
							catalogoErrores.NOT_SAVED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Employee> listNewEnt = new ArrayList<>();
		listNewEnt.add(entSaved);
		return new ResponseEntity<ResponseBase>(new ResponseBase(LocalDateTime.now(), catalogoErrores.OK.getCode(),
				catalogoErrores.OK.getMessage(), HttpStatus.OK.value(), listNewEnt), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseBase> update(@RequestBody Employee employee) {
		// método que actualiza los datos de la empresa.
		employee.setModifiedBy("admin");
		employee.setModifiedDate(LocalDateTime.now());
		employee.setStatus("ACTIVO");
		Employee entUpdated = employeeRepo.save(employee);
		if (entUpdated == null) {
			return new ResponseEntity<ResponseBase>(
					new ResponseBase(LocalDateTime.now(), catalogoErrores.NOT_UPDATED.getCode(),
							catalogoErrores.NOT_UPDATED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Employee> listNewEnt = new ArrayList<>();
		listNewEnt.add(entUpdated);
		return new ResponseEntity<ResponseBase>(new ResponseBase(LocalDateTime.now(), catalogoErrores.OK.getCode(),
				catalogoErrores.OK.getMessage(), HttpStatus.OK.value(), listNewEnt), HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<ResponseBase> delete(@RequestBody Employee employee) {
		// método para eliminaciuón lógica

		employee.setModifiedBy("admin");
		employee.setModifiedDate(LocalDateTime.now());
		employee.setStatus("INACTIVO");
		employeeRepo.save(employee);
		Employee entUpdated = employeeRepo.getReferenceById(employee.getIdEmployee());
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
	public void deleteHARD(@RequestBody Employee employee) {
		employeeRepo.delete(employee);
	}

	@GetMapping("/listHARD")
	public List<Employee> listHARD() {
		return employeeRepo.findAll();
	}
}
