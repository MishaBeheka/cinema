package com.dev.cinema.controllers;

import com.dev.cinema.dto.MovieSessionRequestDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieCinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {

    private final MovieService movieService;
    private final MovieCinemaHallService cinemaHallService;
    private final MovieSessionService movieSessionService;

    public MovieSessionController(MovieService movieService, MovieCinemaHallService cinemaHallService, MovieSessionService movieSessionService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping(value = "/add")
    public void create(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {

    }

    private MovieSession buildMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        ;
    }
}
