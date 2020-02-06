package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.service.MovieCinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Star Wars");
        movie.setDescription("Fantastic");
        movie = movieService.add(movie);
        System.out.println("Before movieService.getAll()");
        movieService.getAll().forEach(System.out::println);

        MovieCinemaHallService cinemaHallService =
                (MovieCinemaHallService) injector.getInstance(MovieCinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("IMAX");
        cinemaHall = cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 50)));
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        System.out.println("All sessions");
        movieSessionService.findAvailableSession(movie.getId(), LocalDate.now())
                .forEach(System.out::println);

        User user = new User();
        user.setEmail("misha_beheka@ukr.net");
        user.setPassword("101");
        UserService userService = (UserService) injector.getInstance(UserService.class);
        userService.add(user);
        System.out.println("FIND BY EMAIL");
        System.out.println(userService.findByEmail("user.getEmail())"));
    }
}
