package com.dev.cinema.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private List<TicketDto> tickets;
    private String userEmail;

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
