package com.dev.cinema.dto;

public class MovieSessionResponseDto {
    private String descriptionOfCinemaHall;
    private String titleOfMovie;
    private String showOfTime;

    public String getDescriptionOfCinemaHall() {
        return descriptionOfCinemaHall;
    }

    public void setDescriptionOfCinemaHall(String descriptionOfCinemaHall) {
        this.descriptionOfCinemaHall = descriptionOfCinemaHall;
    }

    public String getTitleOfMovie() {
        return titleOfMovie;
    }

    public void setTitleOfMovie(String titleOfMovie) {
        this.titleOfMovie = titleOfMovie;
    }

    public String getShowOfTime() {
        return showOfTime;
    }

    public void setShowOfTime(String showOfTime) {
        this.showOfTime = showOfTime;
    }
}
