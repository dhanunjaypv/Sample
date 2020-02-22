package com.bicgraphic.ods.product.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import com.bicgraphic.ods.product.Application;
import com.bicgraphic.ods.product.beans.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesforceProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	public void testOdsSaveproduct() throws Exception {

		File file = ResourceUtils.getFile("classpath:Product_testdata_odsReqforSF.json");
		ObjectMapper mapper = new ObjectMapper();
		Event event = mapper.readValue(file, Event.class);
		String eventJson = mapper.writeValueAsString(event);
		mockMvc.perform(post("/addProduct").contentType(MediaType.APPLICATION_JSON).content(eventJson)).andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$.statusCode").value("INTERR-000")).andExpect(jsonPath("$.message").value("Operation Successfully completed"));
	}
	@Test
	public void testSaveproduct() throws Exception {

		File file = ResourceUtils.getFile("classpath:Product_testdata_sf.json");
		ObjectMapper mapper = new ObjectMapper();
		Event event = mapper.readValue(file, Event.class);
		String eventJson = mapper.writeValueAsString(event);
		mockMvc.perform(post("/pushProductToSalesForce").contentType(MediaType.APPLICATION_JSON).content(eventJson)).andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$.statusCode").value("INTERR-000")).andExpect(jsonPath("$.message").value("Operation Successfully completed"));
	}

}
