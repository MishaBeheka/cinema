package com.dev.cinema.controllers;

import java.util.ArrayList;
import java.util.List;

import com.dev.cinema.dto.MovieRequestDto;
import com.dev.cinema.dto.MovieResponseDto;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/movies")
    public void create(MovieRequestDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movieService.add(movie);
    }

    @GetMapping("/movies")
    public List<MovieResponseDto> getAllMovies() {
        List<MovieResponseDto> allMovies = new ArrayList<>();
        for (Movie movie : movieService.getAll()) {
            MovieResponseDto movieDto = new MovieResponseDto();
            movieDto.setTitle(movie.getTitle());
            movieDto.setDescription(movie.getDescription());
            allMovies.add(movieDto);
        }
        return allMovies;
    }
}
