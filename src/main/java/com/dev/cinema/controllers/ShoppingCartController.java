package com.dev.cinema.controllers;

import com.dev.cinema.dto.ShoppingCartResponseDto;
import com.dev.cinema.dto.TicketResponseDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;

    public ShoppingCartController(UserService userService,
                                  ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping(value = "/addMovieSession")
    public String addMovieSession(@RequestParam Long movieSessionId,
                                  @RequestParam Long userId) {
        shoppingCartService.addSession(movieSessionService.getById(movieSessionId),
                userService.getById(userId));
        return "Movie Session is added";
    }

    @GetMapping(value = "/byUser")
    public ShoppingCartResponseDto getShoppingCartByUser(@RequestParam Long userId) {
        return buildShoppingCartDto(userId);
    }

    private ShoppingCartResponseDto buildShoppingCartDto(Long userId) {
        ShoppingCart shoppingCartOfUser =
                shoppingCartService.getByUser(userService.getById(userId));
        ShoppingCartResponseDto shoppingCartDto = new ShoppingCartResponseDto();
        shoppingCartDto.setUserEmail(shoppingCartOfUser.getUser().getEmail());
        shoppingCartDto.setTickets(shoppingCartOfUser.getTickets()
                .stream()
                .map(this::buildTicketDto)
                .collect(Collectors.toList()));
        return shoppingCartDto;
    }

    private TicketResponseDto buildTicketDto(Ticket ticket) {
        MovieSession movieSession = ticket.getMovieSession();
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        ticketResponseDto.setShowTime(movieSession.getShowTime().toString());
        ticketResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        return ticketResponseDto;
    }
}
