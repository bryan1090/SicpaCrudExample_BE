package com.sicpa.MyEnterprise;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sicpa.MyEnterprise.controller.EmployeeController;
import com.sicpa.MyEnterprise.controller.EnterpriseController;
import com.sicpa.MyEnterprise.model.Enterprise;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
class DepartmentTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation)) 
				.build();
	}

	static final String NEWOBJ = "{\"idDepartment\":\"\",\"enterprise\":{\"idEnterprise\":68,\"departments\":[],\"createdDate\":null,\"status\":\"ACTIVO\",\"address\":\"asd\",\"name\":\"qwe\",\"phone\":\"123\",\"createdBy\":null,\"modifiedBy\":\"admin\",\"modifiedDate\":\"2023-01-08T20:12:50.136591\"},\"created_date\":\"\",\"status\":\"\",\"description\":\"\",\"name\":\"\",\"phone\":\"\",\"created_by\":\"\",\"modified_by\":\"\",\"modified_date\":\"\"}";

	
	

	@Test
	public void debeCrear() throws Exception {
		//Enterprise newObj=new Enterprise(BigInteger.ZERO, null, null, null, "nuevo", null, null, null,null);
		this.mockMvc.perform(post("/department/create").contentType(MediaType.APPLICATION_JSON)
			    .content(NEWOBJ))
		.andDo(print())
		//.andExpect(status().isOk())
				//.andExpect(jsonPath("$.*").isNotEmpty()).andExpect(jsonPath("$.listaObjetos").isArray())
				//.andExpect(jsonPath("$.listaObjetos").isNotEmpty())
				//.andExpect(jsonPath("$.listaObjetos[0][?(@.idDepartment > 0)]").isNotEmpty())
				//.andExpect(jsonPath("$.listaObjetos[0][?(@.status =='ACTIVO')]").isNotEmpty())
				.andDo(document("department-crear")); 
	}
	
	@Test
	public void debeListar() throws Exception {
		this.mockMvc.perform(get("/department/list")).andDo(print())
		//.andExpect(status().isOk())
				//.andExpect(jsonPath("$.*").isNotEmpty()).andExpect(jsonPath("$.listaObjetos").isArray())
				//.andExpect(jsonPath("$.listaObjetos[?(@.status =='ACTIVO')]").isNotEmpty())
			.andDo(document("deparment-listar")); 
	}
}
