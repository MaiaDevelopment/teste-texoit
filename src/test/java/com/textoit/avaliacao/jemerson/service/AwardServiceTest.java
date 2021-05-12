package com.textoit.avaliacao.jemerson.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import com.textoit.avaliacao.jemerson.dto.Award;
import com.textoit.avaliacao.jemerson.model.Movie;
import com.textoit.avaliacao.jemerson.repository.MovieRepository;

@SpringBootTest
public class AwardServiceTest {
	
	@MockBean
	private MovieRepository movieRepository;
	
	@Autowired
	private AwardService awardService;

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
		
		Award result = awardService.getAwardWinners();
		assertThat(result.getMin().length).isEqualTo(2);
		assertThat(result.getMin()[0].getInterval()).isEqualTo(1);
		assertThat(result.getMin()[1].getInterval()).isEqualTo(1);
		
		assertThat(result.getMax().length).isEqualTo(1);
		assertThat(result.getMax()[0].getInterval()).isEqualTo(99);
	}

}
