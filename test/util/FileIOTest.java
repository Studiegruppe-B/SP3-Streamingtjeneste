package util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

class FileIOTest {

    @Test
    void readData() {
        ArrayList<String> data = FileIO.readData("data/filmData.csv", false);
        TextUI.displayList(data, "Film data", true);
    }

    @Test
    void readData2() {
        String[] data = FileIO.readData("data/testData.csv", 3, false);
        for (String s : data) {
            System.out.println(s);
        }
    }

    @Test
    void createFile() {
        try {
            FileIO.createFile("data/player1.csv");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}