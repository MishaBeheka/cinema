package com.dev.cinema.controllers;

import com.dev.cinema.dto.MovieRequestDto;
import com.dev.cinema.dto.MovieResponseDto;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "/add")
    public void create(@RequestBody @Valid MovieRequestDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movieService.add(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll()
                .stream()
                .map(this::migrateToMovieDto)
                .collect(Collectors.toList());
    }

    private MovieResponseDto migrateToMovieDto(Movie movie) {
        MovieResponseDto movieDto = new MovieResponseDto();
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        return movieDto;
    }
}
