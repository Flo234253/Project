package com.example.project.Model;

import java.time.LocalDateTime;

public class Ticket {

    private int id;


    private LocalDateTime  purchaseDateTime;


    private Showtime showtime;


    public Ticket(int id, LocalDateTime purchaseDateTime, Showtime showtime) {
        this.id = id;
        this.purchaseDateTime = purchaseDateTime;
        this.showtime = showtime;
    }

    public int getId(){
        return id;
    }


    public void setId(int id){
        this.id = id;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }
}
