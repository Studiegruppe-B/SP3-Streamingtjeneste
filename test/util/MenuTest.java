package util;

import menu.PageBuilder;
import menu.PageTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    //-----------------------------PageTemplate

    PageTemplate p;

    @BeforeEach
    void beforeEach(){
        p = new PageTemplate();
        p.addSingleOptions(new String[]{"Test"}, ()-> System.out.println("Selected"));

    }

    @Test
    void menuTester() {

    }

    @Test
    void singleOptionRunsItsOwnLambda() {
        PageTemplate page = new PageTemplate();
        StringBuilder log = new StringBuilder();

        page.addSingleOptions(new String[]{"A", "B"},
                () -> log.append("A"),
                () -> log.append("B"));

        page.choice(1);
        assertEquals("B", log.toString());
    }

    @Test
    void differentNumberOfTextsAndActionsThrows() {
        PageTemplate page = new PageTemplate();
        assertThrows(IllegalArgumentException.class,
                () -> page.addSingleOptions(new String[]{"A", "B"}, () -> {}));
    }

    @Test
    void listOptionsGiveLambdaTheRightIndex() {
        PageTemplate page = new PageTemplate();
        List<Integer> chosen = new ArrayList<>();

        page.addListOptions(List.of("X", "Y", "Z"), chosen::add);

        page.choice(2);
        assertEquals(List.of(2), chosen);
    }

    @Test
    void listOptionsComeAfterSingleOptions() {
        PageTemplate page = new PageTemplate();
        page.addSingleOptions(new String[]{"Back"}, () -> {});
        page.addListOptions(List.of("Film A", "Film B"), i -> {});

        assertEquals(List.of("Back", "Film A", "Film B"), page.getOptions());
    }





}



