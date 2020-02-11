package com.dev.cinema;

import com.dev.cinema.exceptoin.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.MovieCinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
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

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User user = authenticationService.register("misha@ukr.net", "101");
        System.out.println("Check LOGIN");
        try {
            System.out.println(authenticationService.login("misha@ukr.net", "101"));
        } catch (AuthenticationException e) {
            LOGGER.error(e);
        }

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        System.out.println("SHOPPING_CART");
        System.out.println(shoppingCart.getTickets());
        System.out.println(shoppingCart.getUser());

        OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);
        Order order = orderService.completeOrder(shoppingCart.getTickets(), user);
        List<Order> orders = orderService.getOrderHistory(user);
        System.out.println(order);
        System.out.println(orders);
    }
}
