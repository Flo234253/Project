package com.example.project.Model;

/**
 * This class will hold the manager information name and time and date when they sign up and it extends
 * the user class that holds the email and password
 */
public class Manager extends User
{
    /*Name attributes*/
    public String aName;

    /**
     *Get the name of the manager
     * @return the name of the manager
     */
    public String getName() {
        return aName;
    }

    /**
     * This method will set the name of the manager, and check
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
     * Constructor to create a new manager using their email and their password
     * @param email have the email of the manager
     * @param password have the password of the manager
     */
    public Manager(String email, String password) {
       super(email, password);
    }



    /**
     * Overriding the getRole method so it return the manger
     * @return the user manager
     */
    @Override
    public String getRole() {
        return "Manager";
    }

}
