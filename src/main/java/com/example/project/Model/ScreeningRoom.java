package com.example.project.Model;

/**
 * Represents a screening room with detailed information such as its name, capacity, and features.
 */
public class ScreeningRoom {
    private String aName;
    private int aCapacity;
    private String aFeatures;

    /**
     * Constructs a ScreeningRoom instance with the specified details.
     *
     * @param pName     The name of the screening room.
     * @param pCapacity The capacity of the screening room.
     * @param pFeatures The features of the screening room (e.g., "IMAX", "3D").
     */
    public ScreeningRoom(String pName, int pCapacity, String pFeatures) {
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
     * Gets the capacity of the screening room.
     *
     * @return The capacity of the screening room.
     */
    public int getCapacity() {
        return aCapacity;
    }

    /**
     * Gets the features of the screening room.
     *
     * @return The features of the screening room.
     */
    public String getFeatures() {
        return aFeatures;
    }
}
