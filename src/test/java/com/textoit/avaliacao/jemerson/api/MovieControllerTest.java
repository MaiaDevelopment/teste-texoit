package com.textoit.avaliacao.jemerson.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private MoviesController movieController;


	@Test
	void winners() throws Exception {
		mockMvc.perform(
				get("/api/movies/awards")
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(jsonPath("$.min[0].producer", is("Joel Silver")))
			    .andExpect(jsonPath("$.min[0].interval", is(1)))
			    .andExpect(jsonPath("$.min[0].previousWin", is(1990)))
			    .andExpect(jsonPath("$.min[0].followingWin", is(1991)))
			    .andExpect(jsonPath("$.max[0].producer", is("Matthew Vaughn")))
			    .andExpect(jsonPath("$.max[0].interval", is(13)))
			    .andExpect(jsonPath("$.max[0].previousWin", is(2002)))
			    .andExpect(jsonPath("$.max[0].followingWin", is(2015)));		
	}

}
