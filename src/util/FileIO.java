package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class FileIO {

    private static final Logger LOGGER = Logger.getLogger( FileIO.class.getName() );

    public static ArrayList<String> readData(String path, boolean skipHeader) {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(path);

        try (Scanner scan = new Scanner(file)) {
            if (skipHeader) {
                scan.nextLine(); // Skip the header.
            }

            while(scan.hasNextLine()) {
                data.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            LOGGER.warning("[FileIO] File not found!");
        }
        return data;
    }

    public static String[] readData(String path, int size, boolean skipHeader) {
        String[] data = new String[size];
        File file = new File(path);

        try (Scanner scan = new Scanner(file)) {
            int counter = 0; // Create counter for Array index.

            if (skipHeader) {
                scan.nextLine(); // Skip the header.
            }

            while (scan.hasNextLine()) {
                data[counter] = scan.nextLine();
                counter++;
            }
        } catch (FileNotFoundException e) {
            LOGGER.warning("[FileIO] File not found!");
        }
        return data;
    }

    public static void saveData(String path, List<String> data, String header) {
        File file = new File(path); // Create new file object.

        try (Writer writer = new FileWriter(file)) {
            writer.write(header +  "\n"); // Write the header.

            for (String s : data) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            LOGGER.warning("[FileIO] File not found!");
        }
    }

    public static void saveData(String path, List<String> data) {
        File file = new File(path); // Create new file object.

        try (Writer writer = new FileWriter(file)) {

            for (String s : data) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            LOGGER.warning("[FileIO] File not found!");
        }
    }

    public static boolean createFile(String path) throws IOException{
            File file = new File(path);

            if (file.createNewFile()) {
                LOGGER.info("[FileIO] Created new file: " + file.getName());
                return true;
            } else {
                LOGGER.warning("[FileIO] File already exists.");
            }
        return false;
    }
}
