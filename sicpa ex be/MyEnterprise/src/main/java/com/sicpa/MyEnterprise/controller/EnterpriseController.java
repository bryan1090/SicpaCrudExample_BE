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

import com.sicpa.MyEnterprise.model.Enterprise;
import com.sicpa.MyEnterprise.repo.IEnterpriseRepo;
import com.sicpa.MyEnterprise.util.ResponseBase;
import com.sicpa.MyEnterprise.util.catalogoErrores;

@RestController
@RequestMapping("/enterprise")
@CrossOrigin(origins = "http://localhost:4200")
public class EnterpriseController {
	@Autowired
	private IEnterpriseRepo enterpriseRepo;// repositorio

	@GetMapping("/list")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ResponseBase> list() {
		// método que lista las empresas filtrando las que tenga estado "ACTIVO"
		List<Enterprise> listEnterprise = enterpriseRepo.findAll();
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

	@PostMapping("/create")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ResponseBase> insert(@RequestBody Enterprise enterprise) {
		// método que crea une nueva empresa, con estado activo
		enterprise.setCreatedBy("admin");
		enterprise.setCreatedDate(LocalDateTime.now());
		enterprise.setStatus("ACTIVO");
		Enterprise entSaved = enterpriseRepo.save(enterprise);
		if (entSaved == null) {
			return new ResponseEntity<ResponseBase>(
					new ResponseBase(LocalDateTime.now(), catalogoErrores.NOT_SAVED.getCode(),
							catalogoErrores.NOT_SAVED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Enterprise> listNewEnt = new ArrayList<>();
		listNewEnt.add(entSaved);
		return new ResponseEntity<ResponseBase>(new ResponseBase(LocalDateTime.now(), catalogoErrores.OK.getCode(),
				catalogoErrores.OK.getMessage(), HttpStatus.OK.value(), listNewEnt), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseBase> update(@RequestBody Enterprise enterprise) {
		// método que actualiza los datos de la empresa.
		enterprise.setModifiedBy("admin");
		enterprise.setModifiedDate(LocalDateTime.now());
		enterprise.setStatus("ACTIVO");
		Enterprise entUpdated = enterpriseRepo.save(enterprise);
		if (entUpdated == null) {
			return new ResponseEntity<ResponseBase>(
					new ResponseBase(LocalDateTime.now(), catalogoErrores.NOT_UPDATED.getCode(),
							catalogoErrores.NOT_UPDATED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Enterprise> listNewEnt = new ArrayList<>();
		listNewEnt.add(entUpdated);
		return new ResponseEntity<ResponseBase>(new ResponseBase(LocalDateTime.now(), catalogoErrores.OK.getCode(),
				catalogoErrores.OK.getMessage(), HttpStatus.OK.value(), listNewEnt), HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<ResponseBase> delete(@RequestBody Enterprise enterprise) {
		// método para eliminaciuón lógica

		enterprise.setModifiedBy("admin");
		enterprise.setModifiedDate(LocalDateTime.now());
		enterprise.setStatus("INACTIVO");
		enterpriseRepo.save(enterprise);
		Enterprise entUpdated = enterpriseRepo.getReferenceById(enterprise.getIdEnterprise());
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
	public void deleteHARD(@RequestBody Enterprise enterprise) {
		enterpriseRepo.delete(enterprise);
	}

	@GetMapping("/listHARD")
	public List<Enterprise> listHARD() {
		return enterpriseRepo.findAll();
	}
}
