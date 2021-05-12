package com.textoit.avaliacao.jemerson.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.textoit.avaliacao.jemerson.dto.Award;
import com.textoit.avaliacao.jemerson.model.Movie;
import com.textoit.avaliacao.jemerson.repository.MovieRepository;
import com.textoit.avaliacao.jemerson.service.AwardService;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AwardService awardService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> all() {
        return movieRepository.findAll(Sort.by("producers", "year"));
    }
	
	@GetMapping(value = "/awards", produces = MediaType.APPLICATION_JSON_VALUE)
    public Award winners() {
		return awardService.getAwardWinners();
    }
	
}
