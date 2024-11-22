package com.example.project.Model;

import java.time.LocalDateTime;

public class Client
        //extends User
{
    /*Name attributes*/
    private String aName;
    /*ID attributes*/
    public int ID;
    /*Date and time variable*/
    private final LocalDateTime registrationDateTime;

    /**
     *Get the name of the user
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
     * Default constructor.
     * Capture the current date and time when the client sign up
     */
    public Client(String email, String password) {
       // super(email, password);
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
    //@Override
    //public String getRole() {
     //   return "Client";
    //}

}





