package com.dev.cinema.dto;

import java.util.List;

public class OrderResponseDto {
    private String orderDate;
    private List<TicketResponseDto> tickets;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }
}
