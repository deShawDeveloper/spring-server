package io.swagger.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import io.swagger.model.request.CreditCardRequest;

@WebMvcTest
@RunWith(SpringRunner.class)
public class CreditcardsApiControllerTest {
	
	public static String CSS_CARDS_PATH = "/creditcards";
	public static String SCORED_CARDS_PATH = "https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards";
	
	
	@Autowired 
	MockMvc mockMvc;
	
	

	@Test
	public void testCreditcardsPostIsOk() throws Exception {
		String name="John Smith";
		int creditScore =341; 
		int salary=18500;
		CreditCardRequest anObject = new CreditCardRequest();
		anObject.setName(name);
		anObject.setCreditScore(creditScore);
		anObject.setSalary(salary);
		mockMvc.perform(
				post(CSS_CARDS_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(anObject))
				
		).andExpect(
			status().isOk()
		);
	}
	
	@Test
	public void testCreditcardsPostResultSize() throws Exception {
		String name="John Smith";
		int creditScore =341; 
		int salary=18500;
		CreditCardRequest anObject = new CreditCardRequest();
		anObject.setName(name);
		anObject.setCreditScore(creditScore);
		anObject.setSalary(salary);
		MvcResult result = mockMvc.perform(
				post(CSS_CARDS_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(anObject))
				
		).andExpect(
			status().isOk()
		).andReturn()
		;
		
		String content = result.getResponse().getContentAsString();
		JSONArray array = new JSONArray(content);
		assertEquals(array.length(),3);
	}
	
	 public static String asJsonString(final Object obj) {
		    try {
		        return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		    
	 }

}
