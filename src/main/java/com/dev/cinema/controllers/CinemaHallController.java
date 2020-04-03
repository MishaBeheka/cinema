package com.dev.cinema.controllers;

import com.dev.cinema.dto.CinemaHallRequestDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.MovieCinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
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
    public void create(@RequestBody @Valid CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHallService.add(cinemaHall);
    }

    @GetMapping
    public List<CinemaHallRequestDto> getAllCinemaHalls() {
        return cinemaHallService.getAll()
                .stream()
                .map(this::migrateToDto)
                .collect(Collectors.toList());
    }

    private CinemaHallRequestDto migrateToDto(CinemaHall cinemaHall) {
        CinemaHallRequestDto cinemaHallRequestDto = new CinemaHallRequestDto();
        cinemaHallRequestDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallRequestDto.setDescription(cinemaHall.getDescription());
        return cinemaHallRequestDto;
    }
}
