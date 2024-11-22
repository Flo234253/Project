package com.example.project.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String aName;
    private String aEmail;
    private String aPassword;

    // Static list to store users
    private static final List<User> userList = new ArrayList<>();

    // Static block to initialize the list
    static {
        //userList.add(new Manager("Tom Manager", "tom.manager@example.com", "admin123"));
        //userList.add(new Client("Tim Client", "tim.taler@gmail.com", "123456"));
    }

    // Constructor
    public User(String pName, String pEmail, String pPassword) {
        this.aName = pName;
        this.aEmail = pEmail;
        this.aPassword = pPassword;
    }

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

    // Abstract method to define the role
    public abstract String getRole();

    /**
     * Creating a method for the list of the different user
     * @return the user List
     */
    public static List<User> getUserList() {
        return userList;
    }

    // Static method for user authentication
    public static User authenticate(String email, String password) {
        return userList.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
