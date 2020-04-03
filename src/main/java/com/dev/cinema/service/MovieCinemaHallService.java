package com.dev.cinema.service;

import com.dev.cinema.model.CinemaHall;
import java.util.List;

public interface MovieCinemaHallService {

    CinemaHall add(CinemaHall cinemaHall);

    CinemaHall getById(Long id);

    List<CinemaHall> getAll();
}
