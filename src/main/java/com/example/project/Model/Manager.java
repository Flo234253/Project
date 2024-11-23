package com.example.project.Model;

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
     * This method will set the name of the manager
     * @param pName assign the name of the manager
     */
    public void setName(String pName) {
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
