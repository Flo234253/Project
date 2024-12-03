package Helpers;

import java.io.*;
import java.util.List;

public class SerializationHelper {

    /**
     * Saves a list of serializable objects to a file.
     *
     * @param filePath The path to the file where the data should be saved.
     * @param data     The data to be saved (a list of serializable objects).
     * @param <T>      The type parameter that extends Serializable.
     */
    public static <T extends Serializable> void saveData(String filePath, List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + filePath);
            e.printStackTrace();
        }
    }

    /**
     * Loads a list of serializable objects from a file.
     *
     * @param filePath The path to the file from which the data should be loaded.
     * @param <T>      The type parameter that extends Serializable.
     * @return The loaded data (a list of serializable objects), or null if loading fails.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> loadData(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File not found: " + filePath);
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + filePath);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found while loading data from file: " + filePath);
            e.printStackTrace();
        }
        return null;
    }
}
