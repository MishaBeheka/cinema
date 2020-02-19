package com.dev.cinema.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.dev.cinema.dto.CinemaHallDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.MovieCinemaHallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {

    private final MovieCinemaHallService cinemaHallService;

    public CinemaHallController(MovieCinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping(value = "/add")
    public void create(@RequestBody CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallDto.getCapacity());
        cinemaHall.setDescription(cinemaHallDto.getDescription());
        cinemaHallService.add(cinemaHall);
    }

    @GetMapping(value = "/all")
    public List<CinemaHallDto> getAllCinemaHalls() {
        return cinemaHallService.getAll()
                .stream()
                .map(this::migrateToDto)
                .collect(Collectors.toList());
    }

    private CinemaHallDto migrateToDto(CinemaHall cinemaHall) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallDto.setDescription(cinemaHall.getDescription());
        return cinemaHallDto;
    }
}
