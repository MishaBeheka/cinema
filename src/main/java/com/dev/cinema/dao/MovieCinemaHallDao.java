package com.dev.cinema.dao;

import com.dev.cinema.model.CinemaHall;

import java.util.List;

public interface MovieCinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    CinemaHall getById(Long id);

    List<CinemaHall> getAll();
}
