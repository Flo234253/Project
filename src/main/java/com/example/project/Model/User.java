package com.example.project.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class User implements Serializable {

    /**
     * Using for serial to save the data
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /*attribute for email*/
    private String aEmail;
    /*attribute for password*/
    private String aPassword;

    /*List so we can store the user both client and manager*/
    private static final List<User> userList = new ArrayList<>();

    /*Making a file path for saving data */
    private static final String DATA_FILE = "users.ser";

    /*Start the list so we have sample data and also load user date to see if some user
    had been added before then save it all together*/
    static {
        loadUserData();

        //userList.add(new Manager("Saboor2@example.com", "Pass1234"));
        // userList.add(new Manager("sarah.lee@example.com", "Admin2023"));
      //userList.add(new Manager("john.doe@example.com", "Manager45"));
      // userList.add(new Client("henryRobert@gmail.ca", "1234"));
      // userList.add(new Client("avachapman@hotmail.ca", "4321"));
      // userList.add(new Client("ethanPrice@gmail.ca", "2134"));

        saveClientData();
    }

    /**
     * Constructor a new user
     * @param pEmail assign the attribute to the parameters
     * @param pPassword assign the attribute to the parameters
     */
    public User(String pEmail, String pPassword) {
        this.aEmail = pEmail;
        this.aPassword = pPassword;
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
        if (pEmail == null || pEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
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
        if (pPassword == null || pPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        aPassword = pPassword;
    }

    /*Get the role of the user Client or Manager*/
    public abstract String getRole();

    /**
     * Creating a method for the list of the different user
     * @return the user List
     */
    public static List<User> getUserList() {
        return userList;
    }

    /**
     * This methods uses a for loop so that it goes through each elements of
     * the userList to see if it matches the user input. If it does then
     * it will return the user if not then it will be null
     * @param email verifying if the email matches
     * @param password verifying if the password matches
     * @return if the match is found then it will return the user and if not it will return null
     */
    public static User authenticate(String email, String password) {
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Save the new client to a file so that we will have the new user save when we close the application
     */
    public static void saveClientData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Go get the list from the file and then read it
     */
    @SuppressWarnings("unchecked")
    public static void loadUserData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            List<User> loadedUsers = (List<User>) ois.readObject();
            userList.addAll(loadedUsers);
        } catch (Exception e) {
            // If file does not exist or is invalid, start with an empty list
            System.err.println("No existing user data found or failed to load. Starting with sample data.");
        }
    }
}
