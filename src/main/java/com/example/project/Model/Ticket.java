package com.example.project.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a ticket purchased by a client for a specific showtime.
 */
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    /** The unique identifier for the ticket. */
    private int aID;

    /** The date and time the ticket was purchased. */

    private LocalDateTime aPurchaseDateTime;

    /** The associated showtime for which the ticket was purchased. */
    private ShowTime aShowtime;

    /** The number of tickets sold. */
    private int aTicketsPurchased;





    /**
     * Constructor to create a new ticket with a unique ID, purchase date and time, and associated pShowtime.
     *
     * @param pID The unique identifier for the ticket.
     * @param pPurchaseDateTime The date and time the ticket was purchased.
     * @param pShowtime The associated pShowtime for which the ticket was purchased.
     */
    public Ticket(int pID, LocalDateTime pPurchaseDateTime, ShowTime pShowtime, int pTicketsPurchased) {
        this.aID = pID;
        this.aPurchaseDateTime = pPurchaseDateTime;
        this.aShowtime = pShowtime;
        this.aTicketsPurchased = pTicketsPurchased;

    }


    /**
     * Gets the unique identifier for the ticket.
     *
     * @return The unique identifier for the ticket.
     */
    public int getID(){
        return aID;
    }

    /**
     * Sets the unique identifier for the ticket.
     *
     * @param aID The unique identifier for the ticket.
     */
    public void setID(int aID){
        this.aID = aID;
    }



    /**
     * Gets the date and time the ticket was purchased.
     *
     * @return The date and time the ticket was purchased.
     */
    public LocalDateTime getPurchaseDateTime() {
        return aPurchaseDateTime;
    }



    /**
     * Sets the date and time the ticket was purchased.
     *
     * @param pPurchaseDateTime The date and time the ticket was purchased.
     */
    public void setPurchaseDateTime(LocalDateTime pPurchaseDateTime) {
        this.aPurchaseDateTime = pPurchaseDateTime;
    }


    /**
     * Gets the associated showtime for which the ticket was purchased.
     *
     * @return The associated showtime.
     */
    public ShowTime getShowtime() {
        return aShowtime;
    }


    /**
     * Sets the associated showtime for which the ticket was purchased.
     *
     * @param pShowtime The associated showtime.
     */
    public void setShowtime(ShowTime pShowtime) {
        this.aShowtime = pShowtime;
    }

    /**
     * Gets the number of tickets sold for this showtime.
     *
     * @return The number of tickets sold.
     */
    public int getTicketsPurchased() {
        return aTicketsPurchased;
    }

    /**
     * Sets the number of tickets sold for this showtime.
     *
     * @param pTicketsSold The number of tickets sold.
     */
    public void setTicketsPurchased(int pTicketsSold) {
        this.aTicketsPurchased = pTicketsSold;
    }



}
