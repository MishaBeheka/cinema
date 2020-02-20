package com.dev.cinema.dto;

import com.dev.cinema.model.Ticket;

import java.util.List;

public class OrderResponseDto {
    private String orderDate;
    private List<Ticket> tickets;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
