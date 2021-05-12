package com.textoit.avaliacao.jemerson.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.textoit.avaliacao.jemerson.model.Movie;
import com.textoit.avaliacao.jemerson.repository.MovieRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieRepository movieRepository;
	
	@Autowired
	private MoviesController movieController;


	@Test
	void winners() throws Exception {
		ArrayList<Movie> movies = new ArrayList<>();
		Movie m = null;
		
		m = new Movie();
		m.setProducers("Producer 1");
		m.setYear((short)1900);
		movies.add(m);
		m = new Movie();
		m.setProducers("Producer 1");
		m.setYear((short)1999);
		movies.add(m);
		m = new Movie();
		m.setProducers("Producer 1");
		m.setYear((short)2008);
		movies.add(m);
		m = new Movie();
		m.setProducers("Producer 1");
		m.setYear((short)2009);
		movies.add(m);
		
		m = new Movie();
		m.setProducers("Producer 2");
		m.setYear((short)2000);
		movies.add(m);
		m = new Movie();
		m.setProducers("Producer 2");
		m.setYear((short)2018);
		movies.add(m);
		m = new Movie();
		m.setProducers("Producer 2");
		m.setYear((short)2019);
		movies.add(m);
		m = new Movie();
		m.setProducers("Producer 2");
		m.setYear((short)2099);
		movies.add(m);
		
		Mockito.when(movieRepository.findAllByWinner(true, Sort.by("producers", "year"))).thenReturn(movies);
		
		
		mockMvc.perform(
				get("/api/movies/awards")
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(jsonPath("$.min[0].interval", is(1)))
			    .andExpect(jsonPath("$.min[1].interval", is(1)))
			    .andExpect(jsonPath("$.max[0].interval", is(99)));

		
	}

}
