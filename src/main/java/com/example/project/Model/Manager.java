package com.example.project.Model;

import java.time.LocalDateTime;

public class Manager //implements Users
{
    /*Name attributes*/
    public String aName;
    /*Password attributes*/
    public String aPassword;
    /*email attributes*/
    public String aEmail;


    //@Override

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
     * get the manager's password
     * @return password
     */
    public String getPassword() {
        return aPassword;
    }

    /**
     * Set the password
     * @param pPassword assign the password manager
     */
    public void setPassword(String pPassword) {
        aPassword = pPassword;
    }
    /**
     * get the manager's email
     * @return email
     */
    public String getEmail() {
        return aEmail;
    }
    /**
     * Set the manager's email
     * @param pEmail assign the email manager
     */
    public void setEmail(String pEmail) {
        aEmail = pEmail;
    }
}
