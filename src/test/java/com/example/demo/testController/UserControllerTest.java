package com.example.demo.testController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;



@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wc;
	
	UserRepo user;
	
	ObjectMapper MAPPER = new ObjectMapper();
	
	protected User getUserMock() {
		User req = new User();
		req.setId(2);
		System.out.println(req);
		return req;
	}
	
	protected User createUserMock() {
	    User req = new User();
	    req.setId(2);
	    req.setName("jey");
	    return req;
	  }
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}
	 
	  @Test
	  @Order(1)
	  public void testAddUser() throws Exception{
		  MvcResult result = this.mockMvc.perform(get("/add/user/" + 2 + "/" + "jey")
                  .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(status().is2xxSuccessful())
				  .andReturn();
		  Gson g = new Gson(); 
		  assertEquals(result.getResponse().getContentAsString(), g.toJson(createUserMock(), User.class));
	  }
	    
	  
	  @Test
		@Order(2)
		public void testGetUser() throws Exception{
		    MvcResult result = this.mockMvc.perform(post("/get/user")
		                                            .content(MAPPER.writeValueAsBytes(getUserMock()))
		                                            .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().is2xxSuccessful())
		        .andReturn();

		    assertThat(result.getResponse().getContentAsString()).isNotNull(); 

		  }
	  
	  @Test
		@Order(2)
		public void GetAllUsers() throws Exception{
			System.out.println(getUserMock());
		    MvcResult result = this.mockMvc.perform(post("/get/allusers")
		                                            .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().is2xxSuccessful())
		        .andReturn();

		    assertThat(result.getResponse().getContentAsString()).isNotNull(); 

		  }

}
