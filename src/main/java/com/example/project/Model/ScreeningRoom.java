package com.example.project.Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a screening room with detailed information such as its name, capacity, and features.
 * <p>
 * This class encapsulates the details of a screening room used in a cinema, such as the room's name,
 * its capacity (number of seats), and its features (e.g., IMAX, 3D, Standard).
 * It implements {@link Serializable} to allow screening rooms to be saved to or loaded from files,
 * which is crucial for persistence, such as saving the list of screening rooms in a file for future use.
 * </p>
 *
 * <p>
 * The {@link ScreeningRoom} class is utilized throughout the application, including in classes like
 * {@link com.example.project.Controllers.ScreenRoomListController}, which manages the creation, modification,
 * and viewing of screening rooms.
 * </p>
 *
 * @implNote This class validates its inputs to ensure data integrity by using appropriate setter methods.
 */
public class ScreeningRoom implements Serializable {

    /**
     * The serial version UID for ensuring compatibility during deserialization.
     * <p>
     * This UID is necessary to ensure that during deserialization, the version of the class is compatible
     * with the serialized version. It is essential for maintaining data integrity when changes are made to
     * the class.
     * </p>
     */
    @Serial
    private static final long serialVersionUID = 1L;

    // Fields

    /**
     * The name of the screening room.
     * <p>
     * The room name uniquely identifies a screening room within the cinema.
     * This is crucial for referencing specific rooms in other parts of the application, such as when consulting
     * or modifying the screening room details in the UI.
     * </p>
     */
    private String aName;

    /**
     * The capacity of the screening room.
     * <p>
     * The capacity represents the number of seats available in the screening room. It must be between 1 and 255.
     * This is used to ensure that the screening room can accommodate the correct number of viewers.
     * </p>
     */
    private int aCapacity;

    /**
     * The features of the screening room.
     * <p>
     * This field represents the special features of the screening room (e.g., IMAX, 3D, Standard).
     * It helps categorize the types of rooms and the experiences they provide.
     * </p>
     */
    private String aFeatures;

    // Constructor

    /**
     * Constructs a {@link ScreeningRoom} instance with the specified details.
     * <p>
     * The constructor initializes a new screening room with its name, capacity, and features.
     * This constructor is used in various parts of the application, such as when adding a new room in
     * the {@link com.example.project.Controllers.AddScreenRoomController} or during the initialization
     * of default rooms.
     * </p>
     *
     * @param pName     The name of the screening room. Must not be null or empty.
     * @param pCapacity The capacity of the screening room. Must be between 1 and 255.
     * @param pFeatures The features of the screening room. Must be one of: IMAX, 3D, Standard.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public ScreeningRoom(String pName, int pCapacity, String pFeatures) {
        setName(pName);
        setCapacity(pCapacity);
        setFeatures(pFeatures);
    }

    // Getter and Setter methods (mutable class)

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
     * <p>
     * The name must be non-null and non-empty. It is an essential identifier for the screening room.
     * This method is used in constructors or when modifying room details, such as in the
     * {@link com.example.project.Controllers.ModifyScreeningRoomController}.
     * </p>
     *
     * @param pName The name of the screening room. Must not be null or empty.
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
     * <p>
     * The capacity represents the number of seats available in the screening room. It must be greater than 0
     * and cannot exceed 255 to ensure proper room configuration.
     * This method is called when creating or modifying room details, such as in the
     * {@link com.example.project.Controllers.ModifyScreeningRoomController}.
     * </p>
     *
     * @param pCapacity The capacity of the screening room. Must be greater than 0 and no more than 255.
     * @throws IllegalArgumentException if the capacity is out of the valid range.
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
     * <p>
     * The features describe what special capabilities the room has, such as being an IMAX or 3D theater.
     * Only allowed values ("IMAX", "3D", "Standard") can be set to ensure the room's feature type is valid.
     * This method is used when initializing or updating room details, such as when editing a room in
     * the {@link com.example.project.Controllers.ModifyScreeningRoomController}.
     * </p>
     *
     * @param pFeatures The features of the screening room. Must be one of: IMAX, 3D, Standard.
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
     * Returns a string representation of the screening room.
     * <p>
     * The string representation includes the room name, capacity, and features.
     * It is useful for debugging purposes or when displaying screening room details in the UI.
     * </p>
     *
     * @return A formatted string representation of the screening room.
     */
    @Override
    public String toString() {
        return String.format("ScreeningRoom[Name=%s, Capacity=%d, Features=%s]", aName, aCapacity, aFeatures);
    }
}
