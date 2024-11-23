package com.example.project.Model;

import java.time.LocalDateTime;

/**
 * Represents a ticket purchased by a client for a specific showtime.
 */
public class Ticket {

    /** The unique identifier for the ticket. */
    private int id;

    /** The date and time the ticket was purchased. */

    private LocalDateTime  purchaseDateTime;

    /** The associated showtime for which the ticket was purchased. */
    private Showtime showtime;


    /**
     * Constructor to create a new ticket with a unique ID, purchase date and time, and associated showtime.
     *
     * @param id The unique identifier for the ticket.
     * @param purchaseDateTime The date and time the ticket was purchased.
     * @param showtime The associated showtime for which the ticket was purchased.
     */
    public Ticket(int id, LocalDateTime purchaseDateTime, Showtime showtime) {
        this.id = id;
        this.purchaseDateTime = purchaseDateTime;
        this.showtime = showtime;
    }


    /**
     * Gets the unique identifier for the ticket.
     *
     * @return The unique identifier for the ticket.
     */
    public int getId(){
        return id;
    }

    /**
     * Sets the unique identifier for the ticket.
     *
     * @param id The unique identifier for the ticket.
     */
    public void setId(int id){
        this.id = id;
    }



    /**
     * Gets the date and time the ticket was purchased.
     *
     * @return The date and time the ticket was purchased.
     */
    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }



    /**
     * Sets the date and time the ticket was purchased.
     *
     * @param purchaseDateTime The date and time the ticket was purchased.
     */
    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }


    /**
     * Gets the associated showtime for which the ticket was purchased.
     *
     * @return The associated showtime.
     */
    public Showtime getShowtime() {
        return showtime;
    }


    /**
     * Sets the associated showtime for which the ticket was purchased.
     *
     * @param showtime The associated showtime.
     */
    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }
}
