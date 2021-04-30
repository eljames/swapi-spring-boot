package com.swapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StarshipsControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void mustReturnNotFodund() throws Exception {
		this.mockMvc
			.perform(get("/starships?page=10000"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void mustReturnOk() throws Exception {
		this.mockMvc
			.perform(get("/starships?page=1"))
			.andExpect(status().isOk());
	}
	
}
