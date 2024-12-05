package Helpers;

import java.io.*;
import java.util.List;

/**
 * Helper class for serializing and deserializing objects to and from files.
 * <p>
 * This class provides utility methods to save a list of serializable objects to a file
 * and load them back. It is commonly used to persist application data, like a list of movies or screening rooms.
 * Serialization allows the objects to be stored and retrieved for later use.
 * </p>
 */
public class SerializationHelper {

    /**
     * Saves a list of serializable objects to a file.
     * <p>
     * This method takes a list of serializable objects and writes them to a specified file path.
     * It ensures that the list of objects can be reloaded in the future, which is useful for maintaining
     * the state of the application, such as saving a list of movies or screening rooms to be reloaded
     * when the application restarts.
     * </p>
     *
     * @param filePath The path to the file where the data should be saved.
     *                 This parameter specifies where the serialized data will be stored.
     * @param data     The data to be saved, which is a list of serializable objects.
     *                 This parameter allows the function to save any list that implements Serializable.
     * @param <T>      The type parameter that extends {@link Serializable}.
     *                 This generic type allows flexibility in saving different types of data, as long as
     *                 the objects in the list implement {@link Serializable}.
     */
    public static <T extends Serializable> void saveData(String filePath, List<T> data) {
        // Use try-with-resources to ensure the FileOutputStream and ObjectOutputStream are properly closed
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            // Write the list of serializable objects to the file
            oos.writeObject(data);
        } catch (FileNotFoundException e) {
            // Handle case where the specified file path does not exist or cannot be found
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            // Handle generic IO exceptions, such as issues in writing data to the file
            System.err.println("Error saving data to file: " + filePath);
            e.printStackTrace();
        }
    }

    /**
     * Loads a list of serializable objects from a file.
     * <p>
     * This method reads data from a file, deserializing it into a list of objects.
     * It is used to restore previously saved data, ensuring that the application state can be retained
     * between sessions. If the file does not exist or an error occurs during reading,
     * it returns {@code null} to indicate that loading failed.
     * </p>
     *
     * @param filePath The path to the file from which the data should be loaded.
     *                 This specifies where to find the serialized data.
     * @param <T>      The type parameter that extends {@link Serializable}.
     *                 This ensures that the data being read from the file can be properly cast back into the original type.
     * @return The loaded data, which is a list of serializable objects, or {@code null} if loading fails.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> loadData(String filePath) {
        File file = new File(filePath);
        // Check if the specified file exists before attempting to load data
        if (!file.exists()) {
            System.err.println("File not found: " + filePath);
            return null;
        }

        // Use try-with-resources to ensure the FileInputStream and ObjectInputStream are properly closed
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // Read the serialized data and cast it to the appropriate type
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Handle case where the specified file path does not exist or cannot be found
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            // Handle generic IO exceptions, such as issues in reading data from the file
            System.err.println("Error reading data from file: " + filePath);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Handle the case where the serialized class cannot be found during the deserialization process
            System.err.println("Class not found while loading data from file: " + filePath);
            e.printStackTrace();
        }
        // Return null if there is an error during reading or casting
        return null;
    }
}
