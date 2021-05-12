package com.textoit.avaliacao.jemerson.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.textoit.avaliacao.jemerson.model.Movie;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

	Iterable<Movie> findAllByOrderByProducers();
	
	Iterable<Movie> findAllByWinner(boolean winner, Sort sort);
	
}
