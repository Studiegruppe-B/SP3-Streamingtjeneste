package util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class FileIOTest {

    @Test
    void readData() {
        ArrayList<String> data = FileIO.readData("data/filmData.csv");
        TextUI.displayList(data, "Film data", true);
    }

    @Test
    void readData2() {
        String[] data = FileIO.readData("data/testData.csv", 3);
        for (String s : data) {
            System.out.println(s);
        }
    }

    @Test
    void saveData() {
    }
}