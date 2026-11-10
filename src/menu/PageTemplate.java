package menu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

public class PageTemplate {

    private final ArrayList<Runnable> action;
    private final ArrayList<String> options;

    public PageTemplate() {
        this.options = new ArrayList<>();
        this.action = new ArrayList<>();
    }



    // Skriv manuelt options, hvor hver option får sin egen lambda.
    public void addSingleOptions(String[] texts, Runnable... newActions) {
        if (texts.length != newActions.length) {
            throw new IllegalArgumentException("Hver option skal have en handling");
        }
        this.options.addAll(List.of(texts));
        this.action.addAll(List.of(newActions));
    }

    // Hele listen får én lambda. i = placering i listen
    public void addListOptions(List<String> texts, Consumer<Integer> sharedAction) {
        for (int i = 0; i < texts.size(); i++) {
            int index = i;
            options.add(texts.get(i));
            action.add(() -> sharedAction.accept(index));
        }
    }

    public void choice(int index) {
        action.get(index).run();
    }


    public ArrayList<String> getOptions() {
        return options;
    }

    // Rydder siden, så den kan bygges forfra under runtime
    public void clearOptions(int indexFrom, int indexTo) {
        options.clear();
        action.clear();
    }





}


