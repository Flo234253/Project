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
    public int ID;
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
     * This method will set the name of the user
     * @param pName assign the name of the user
     */
    public void setName(String pName) {
        aName = pName;
    }

    /**
     * Get the clients ID
     * @return the client's ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the client's ID
     * @param pID assign the ID
     */
    public void setID(int pID) {
        ID = pID;
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
        this.ID = ID;
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
