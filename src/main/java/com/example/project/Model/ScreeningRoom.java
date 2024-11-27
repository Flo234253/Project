package com.example.project.Model;

import java.util.List;

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
     * The features of the screening room (e.g., "IMAX", "3D").
     */
    private String aFeatures;

    /**
     * Constructs a ScreeningRoom instance with the specified details.
     *
     * @param pName     The name of the screening room. Must not be null or empty.
     * @param pCapacity The capacity of the screening room. Must be greater than zero and less than or equal to 255.
     * @param pFeatures The features of the screening room (e.g., "IMAX", "3D"). Must be valid and predefined.
     * @throws IllegalArgumentException if any of the parameters are invalid.
     */
    public ScreeningRoom(String pName, int pCapacity, String pFeatures) {
        setName(pName);
        setCapacity(pCapacity);
        setFeatures(pFeatures);
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
            throw new IllegalArgumentException("Room name cannot be null or empty.");
        }
        this.aName = pName.trim();
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
     * Sets the capacity of the screening room.
     *
     * @param pCapacity The new capacity of the screening room. Must be greater than zero and less than or equal to 255.
     * @throws IllegalArgumentException if the capacity is not within the valid range.
     */
    public void setCapacity(int pCapacity) {
        if (pCapacity <= 0 || pCapacity > 255) {
            throw new IllegalArgumentException("Capacity must be a positive number and cannot exceed 255.");
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
     * @param pFeatures The new features of the screening room. Must be one of the predefined values.
     * @throws IllegalArgumentException if the features are not valid.
     */
    public void setFeatures(String pFeatures) {
        List<String> allowedFeatures = List.of("IMAX", "3D", "Standard");
        if (pFeatures == null || !allowedFeatures.contains(pFeatures.trim())) {
            throw new IllegalArgumentException("Invalid feature. Allowed values are: IMAX, 3D, Standard.");
        }
        this.aFeatures = pFeatures.trim();
    }

    /**
     * Returns a string representation of the ScreeningRoom object.
     *
     * @return A formatted string with the room's details.
     */
    @Override
    public String toString() {
        return String.format("ScreeningRoom[Name=%s, Capacity=%d, Features=%s]", aName, aCapacity, aFeatures);
    }

    /**
     * Checks equality based on room name.
     *
     * @param obj The object to compare.
     * @return True if the names are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ScreeningRoom room = (ScreeningRoom) obj;
        return aName.equalsIgnoreCase(room.aName);
    }

    /**
     * Computes the hash code based on the room name.
     *
     * @return The hash code of the room.
     */
    @Override
    public int hashCode() {
        return aName.toLowerCase().hashCode();
    }
}
