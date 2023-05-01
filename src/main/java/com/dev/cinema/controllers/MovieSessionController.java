package com.dev.cinema.controllers;

import com.dev.cinema.dto.MovieSessionRequestDto;
import com.dev.cinema.dto.MovieSessionResponseDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieCinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final MovieService movieService;
    private final MovieCinemaHallService cinemaHallService;
    private final MovieSessionService movieSessionService;

    public MovieSessionController(MovieService movieService,
                                  MovieCinemaHallService cinemaHallService,
                                  MovieSessionService movieSessionService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping(value = "/add")
    public MovieSession create(@RequestBody @Valid MovieSessionRequestDto movieSessionRequestDto) {
        return movieSessionService.add(buildMovieSession(movieSessionRequestDto));
    }

    @GetMapping
    public List<MovieSessionResponseDto> getAllMovieSessions(
            @RequestParam Long id, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
        return movieSessionService.findAvailableSession(id, localDate)
                .stream()
                .map(this::buildMovieSessionDto)
                .collect(Collectors.toList());
    }

    private MovieSession buildMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(
                cinemaHallService.getById(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setShowTime(
                LocalDateTime.parse(movieSessionRequestDto.getShowTime(), formatter));
        return movieSession;
    }

    private MovieSessionResponseDto buildMovieSessionDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionDto = new MovieSessionResponseDto();
        movieSessionDto.setDescriptionOfCinemaHall(movieSession.getCinemaHall().getDescription());
        movieSessionDto.setTitleOfMovie(movieSession.getMovie().getTitle());
        movieSessionDto.setShowOfTime(movieSession.getShowTime().toString());
        return movieSessionDto;
    }
}
