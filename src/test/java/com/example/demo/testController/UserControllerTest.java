package com.example.demo.testController;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import antlr.collections.List;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wc;
	
	ArrayList<User> userList = new ArrayList<User>();
	
	public static final User user = User.builder()
			.id(2)
			.name("jey")
			.build();
	
	ObjectMapper MAPPER = new ObjectMapper();
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}
	 
	@Test
	public void saveUser() throws JsonProcessingException, Exception{
		MvcResult result = mockMvc.perform(get("/api/employee{employee-id}/{employee-name}", 2, "jey"))
				.andExpect(status().isOk())
				.andReturn(); 
		User response = MAPPER.readValue(result.getResponse()
				.getContentAsString(), User.class);
		assertEquals(user, response);
	}
	
	
	@Test
	public void getUser() throws JsonProcessingException, Exception{
		MvcResult result = mockMvc.perform(post("/api/employee/")
				.content(MAPPER.writeValueAsString(2)))
				.andExpect(status().isOk())
				.andReturn();
		
		User response = MAPPER.readValue(result.getResponse().getContentAsString(), User.class);
		assertEquals(user, response);
	}

}
