package com.example.project.Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a screening room with detailed information such as its name, capacity, and features.
 * @implNote This class validates its inputs to ensure data integrity.
 */
public class ScreeningRoom implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;// Serial version UID for compatibility

    // Fields
    private String aName;
    private int aCapacity;
    private String aFeatures;

    // Constructor
    public ScreeningRoom(String pName, int pCapacity, String pFeatures) {
        setName(pName);
        setCapacity(pCapacity);
        setFeatures(pFeatures);
    }

    // Getter and Setter methods (mutable class)
    public String getName() {
        return aName;
    }

    public void setName(String pName) {
        if (pName == null || pName.trim().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be null or empty.");
        }
        this.aName = pName.trim();
    }

    public int getCapacity() {
        return aCapacity;
    }

    public void setCapacity(int pCapacity) {
        if (pCapacity <= 0 || pCapacity > 255) {
            throw new IllegalArgumentException("Capacity must be a positive number and cannot exceed 255.");
        }
        this.aCapacity = pCapacity;
    }

    public String getFeatures() {
        return aFeatures;
    }

    public void setFeatures(String pFeatures) {
        List<String> allowedFeatures = List.of("IMAX", "3D", "Standard");
        if (pFeatures == null || !allowedFeatures.contains(pFeatures.trim())) {
            throw new IllegalArgumentException("Invalid feature. Allowed values are: IMAX, 3D, Standard.");
        }
        this.aFeatures = pFeatures.trim();
    }

    @Override
    public String toString() {
        return String.format("ScreeningRoom[Name=%s, Capacity=%d, Features=%s]", aName, aCapacity, aFeatures);
    }
}
