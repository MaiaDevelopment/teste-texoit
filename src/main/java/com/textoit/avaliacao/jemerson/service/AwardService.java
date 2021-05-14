package com.textoit.avaliacao.jemerson.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.textoit.avaliacao.jemerson.dto.Award;
import com.textoit.avaliacao.jemerson.dto.Winner;
import com.textoit.avaliacao.jemerson.model.Movie;
import com.textoit.avaliacao.jemerson.repository.MovieRepository;

@Service
public class AwardService {

	@Autowired
	private MovieRepository movieRepostory;
	
	public Award getAwardWinners() {
		
		Iterable<Movie> movies = movieRepostory.findAllByWinner(true, null);
		ArrayList<Movie> producersList = new ArrayList<>();
		for(Movie movie : movies) {
			String[] producers = movie.getProducers().replace("and",  ",").split(",");
			for(String p : producers) {
				Movie m = new Movie();
				m.setProducers(p.trim());
				m.setStudios(movie.getStudios());
				m.setTitle(movie.getTitle());
				m.setWinner(movie.getWinner());
				m.setYear(movie.getYear());
				producersList.add(m);
			}
		}
		
		Collections.sort(producersList, Comparator.comparing(Movie::getProducers).thenComparing(Movie::getYear));
		
		LinkedList<Winner> minListTmp = new LinkedList<>();
		LinkedList<Winner> maxListTmp = new LinkedList<>();
		
		int min = Short.MAX_VALUE;
		int max = Short.MIN_VALUE;
		Movie last = null;
		for(Movie movie : producersList) {
			if (last != null) {
				if (last.getProducers().equals(movie.getProducers())) {
					int interval = movie.getYear() - last.getYear();
					if (interval <= min) {
						minListTmp.push(new Winner(movie.getProducers(), interval, last.getYear(), movie.getYear()));
						min = interval;
					} 
					if (interval >= max) {
						maxListTmp.push(new Winner(movie.getProducers(), interval, last.getYear(), movie.getYear()));
						max = interval;
					}
				}
			}
			last = movie;
		}

		Stream<Winner> mins = minListTmp.stream().filter(i -> i.getInterval() == minListTmp.peek().getInterval());
		Stream<Winner> maxs = maxListTmp.stream().filter(i -> i.getInterval() == maxListTmp.peek().getInterval());
		
		Award award = new Award();
		award.setMin(mins.toArray(size -> new Winner[size]));
		award.setMax(maxs.toArray(size -> new Winner[size]));
		return award;
	}
	
}
