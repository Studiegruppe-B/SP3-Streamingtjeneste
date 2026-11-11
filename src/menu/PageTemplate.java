package menu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

public class PageTemplate {

    private String title;

    private final ArrayList<Runnable> action = new ArrayList<>();
    private final ArrayList<String> options = new ArrayList<>();

    public PageTemplate(String title) {
        this.title = title;
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
    public void clearOptions() {
        options.clear();
        action.clear();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }







}


