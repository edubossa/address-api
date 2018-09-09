package com.ews.address.addressapi;

import com.ews.address.addressapi.model.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressApiApplicationTests {

	public static final String URL = "http://localhost:8080/api";

	private MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void addAddress() throws Exception {
		Address address = new Address();
		address.setStreet("Av. Voluntários da Pátria ");
		address.setNumber(3454);
		address.setZipCode("09876-234");
		address.setDistrict("CENTRO");
		address.setComplement("Proximo Metro");
		address.setCity("Sao Paulo");
		address.setState("SP");
		mockMvc.perform(post("/addresses", address).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(address))).andExpect(status()
				.is2xxSuccessful()).andExpect(redirectedUrl(null));
	}

}
