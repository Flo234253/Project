package com.example.project.Model;



import java.time.LocalDateTime;

public class Showtime {

    private int id;

    private LocalDateTime dateTime;


    private String movie;


    private String screeningRoom;

    public Showtime(int id, LocalDateTime dateTime, String movie, String screeningRoom) {
        this.id = id;
        this.dateTime = dateTime;
        this.movie = movie;
        this.screeningRoom = screeningRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public String getMovie() {
        return movie;
    }


    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(String screeningRoom) {
        this.screeningRoom = screeningRoom;
    }






}
