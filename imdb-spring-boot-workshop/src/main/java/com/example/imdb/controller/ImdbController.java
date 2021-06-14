package com.example.imdb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.domain.Movie;
import com.example.imdb.service.MovieService;

// Descriptive Programming -> Annotation -> Meta-data -> Framework/Library (Spring MVC)
@RestController // Component: Spring MVC -> Controller -> RestController -> Restful Service
@RequestScope
@RequestMapping("/movies")
@CrossOrigin
public class ImdbController { // RESTful Service
    @Autowired
    private MovieService movieService;
    // application.properties:
    // server.address=localhost
    // server.port=4400
    // server.servlet.context-path=/imdb
    // spring.mvc.servlet.path=/api/v1
    // http://localhost:4400/imdb/api/v1/movies?from=1970&to=1979
	@GetMapping
	public Collection<Movie> getYearRangeMovies(
			@RequestParam(name = "from") int fromYear, // query param
			@RequestParam(name = "to") int toYear){
		return movieService.findAllMoviesByYearRange(fromYear, toYear);
	}
}
