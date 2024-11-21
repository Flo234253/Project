package com.example.project.Model;

import java.time.LocalDateTime;

public class Client //implements Users
{
    /*Name attributes*/
    public String aName;
    /*Password attributes*/
    public String aPassword;
    /*email attributes*/
    public String aEmail;
    /*ID attributes*/
    public int ID;
    /*Date and time variable*/
    private final LocalDateTime registrationDateTime;

    //@Override

    /**
     *Get the name of the client
     * @return the name of the client
     */
    public String getName() {
        return aName;
    }

    /**
     * This method will set the name of the client
     * @param pName assign the name of the client
     */
    public void setName(String pName) {
        aName = pName;
    }

    /**
     * Get the password that the client entered
     * @return the client's password
     */
    public String getPassword() {
        return aPassword;
    }

    /**
     * Set the client's password
     * @param pPassword assign the password
     */
    public void setPassword(String pPassword) {
        aPassword = pPassword;
    }

    /**
     * Get the client's email
     * @return the client's email
     */
    public String getEmail() {
        return aEmail;
    }

    /**
     * Set the client's email
     * @param pEmail assign the email
     */
    public void setEmail(String pEmail) {
        aEmail = pEmail;
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
     * Default constructor.
     * Capture the current date and time when the client sign up
     */
    public Client() {
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





}
