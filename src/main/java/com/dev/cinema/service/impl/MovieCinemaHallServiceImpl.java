package com.dev.cinema.service.impl;

import com.dev.cinema.dao.MovieCinemaHallDao;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.MovieCinemaHallService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCinemaHallServiceImpl implements MovieCinemaHallService {
    @Autowired
    private MovieCinemaHallDao movieCinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return movieCinemaHallDao.add(cinemaHall);
    }

    @Override
    public CinemaHall getById(Long id) {
        return movieCinemaHallDao.getById(id);
    }

    @Override
    public List<CinemaHall> getAll() {
        return movieCinemaHallDao.getAll();
    }
}
