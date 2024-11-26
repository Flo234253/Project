package com.example.project.Model;

/**
 * Represents a screening room with detailed information such as its name, capacity, and features.
 * @implNote This class validates its inputs to ensure data integrity.
 */
public class ScreeningRoom {
    /**
     * The name of the screening room.
     */
    private String aName;

    /**
     * The capacity of the screening room.
     */
    private int aCapacity;

    /**
     * The features of the screening room (ex: "IMAX", "3D").
     */
    private String aFeatures;

    /**
     * Constructs a ScreeningRoom instance with the specified details.
     *
     * @param pName     The name of the screening room. Must not be null or empty.
     * @param pCapacity The capacity of the screening room. Must be greater than zero.
     * @param pFeatures The features of the screening room (e.g., "IMAX", "3D"). Must not be null or empty.
     * @throws IllegalArgumentException if any of the parameters are invalid.
     */
    public ScreeningRoom(String pName, int pCapacity, String pFeatures) {
        if (pName == null || pName.trim().isEmpty()) {
            throw new IllegalArgumentException("Screening room name cannot be null or empty.");
        }
        if (pCapacity <= 0) {
            throw new IllegalArgumentException("Screening room capacity must be greater than zero.");
        }
        if (pFeatures == null || pFeatures.trim().isEmpty()) {
            throw new IllegalArgumentException("Screening room features cannot be null or empty.");
        }

        this.aName = pName;
        this.aCapacity = pCapacity;
        this.aFeatures = pFeatures;
    }

    /**
     * Gets the name of the screening room.
     *
     * @return The name of the screening room.
     */
    public String getName() {
        return aName;
    }

    /**
     * Sets the name of the screening room.
     *
     * @param pName The new name of the screening room. Must not be null or empty.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public void setName(String pName) {
        if (pName == null || pName.trim().isEmpty()) {
            throw new IllegalArgumentException("Screening room name cannot be null or empty.");
        }
        this.aName = pName;
    }

    /**
     * Gets the capacity of the screening room.
     *
     * @return The capacity of the screening room.
     */
    //Todo
    public int getCapacity() {
        return aCapacity;
    }

    /**
     * Sets the capacity of the screening room.
     *
     * @param pCapacity The new capacity of the screening room. Must be greater than zero.
     * @throws IllegalArgumentException if the capacity is not greater than zero.
     */
    //Todo
    public void setCapacity(int pCapacity) {
        if (pCapacity <= 0) {
            throw new IllegalArgumentException("Screening room capacity must be greater than zero.");
        }
        this.aCapacity = pCapacity;
    }

    /**
     * Gets the features of the screening room.
     *
     * @return The features of the screening room.
     */
    public String getFeatures() {
        return aFeatures;
    }

    /**
     * Sets the features of the screening room.
     *
     * @param pFeatures The new features of the screening room. Must not be null or empty.
     * @throws IllegalArgumentException if the features are null or empty.
     */
    //Todo
    public void setFeatures(String pFeatures) {
        if (pFeatures == null || pFeatures.trim().isEmpty()) {
            throw new IllegalArgumentException("Screening room features cannot be null or empty.");
        }
        this.aFeatures = pFeatures;
    }
}
