package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public final class FileIO {

    public static ArrayList<String> readData(String path) {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(path);

        try (Scanner scan = new Scanner(file)) {
            scan.nextLine(); // Skip the header.

            while(scan.hasNextLine()) {
                data.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("[FileIO] File not found!");
        }
        return data;
    }

    public static void saveData(String path, String header, ArrayList<String> data) {
        File file = new File(path); // Create new file object.

        try (Writer writer = new FileWriter(file)) {
            writer.write(header); // Write the header.

            for (String s : data) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            System.out.println("[FileIO] File not found!");
        }
    }
}
