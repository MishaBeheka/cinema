package com.dev.cinema.service.impl;

import com.dev.cinema.dao.MovieCinemaHallDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.MovieCinemaHallService;

import java.util.List;

@Service
public class MovieCinemaHallServiceImpl implements MovieCinemaHallService {
    @Inject
    MovieCinemaHallDao movieCinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return movieCinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return movieCinemaHallDao.getAll();
    }
}
