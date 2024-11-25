package com.example.project.Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class will hold the client information id, name and time and date when they sign up and it extends
 * the user class that holds the email and password and implements serializable to save the new user
 */
public class Client extends User implements Serializable {
    /**
     * Using for serial to save the data
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /*Name attributes*/
    private String aName;
    /*ID attributes*/
    public int aID;
    /*Date and time variable*/
    private final LocalDateTime registrationDateTime;

    /**
     * Get the name of the user
     * @return the name of the user
     */
    public String getName() {
        return aName;
    }

    /**
     * This method will set the name of the client, and check
     * that the name is not null or empty.
     *
     * @param pName The name to set.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public void setName(String pName) {
        if (pName == null || pName.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        aName = pName;
    }

    /**
     * Get the clients ID
     * @return the client's ID
     */
    public int getID() {
        return aID;
    }

    /**
     * Set the client's ID, and check if the ID is a positive number
     *
     *
     * @param pID The ID to set.
     * @throws IllegalArgumentException if the ID is less than or equal to 0.
     */
    public void setID(int pID) {
        if (pID <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }
        aID = pID;
    }



    /**
     * Constructor with name, email, password, and ID for creating a new client
     *
     * @param name     getting the name of the client
     * @param email     getting the email of the client
     * @param password  getting the password of the client
     * @param ID       getting the ID of the client
     */
    public Client(String name, String email, String password, int ID) {
        super(email, password);
        this.aName = name;
        this.aID = ID;
        this.registrationDateTime = LocalDateTime.now();
    }

    /**
     * Default constructor.
     * Capture the current date and time when the client sign up
     */
    public Client(String email, String password) {
        super(email, password);
        this.registrationDateTime = LocalDateTime.now();
    }

    /**
     * Gets the registration date and time of the client.
     *
     * @return The registration date and time of the client.
     */
    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    /**
     * Assign the user to client
     * @return client
     */
    @Override
    public String getRole() {
        return "Client";
    }
}
