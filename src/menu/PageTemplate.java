package menu;

import java.util.ArrayList;
import java.util.List;

public class PageTemplate {

    private final MenuEnum page;
    private final ArrayList<Runnable> action;
    private final ArrayList<String> options;

    public PageTemplate(MenuEnum page) {
        this.page = page;
        this.options = new ArrayList<>();
        this.action = new ArrayList<>();
    }

    public void addOptions(String[] texts, Runnable... newActions) {
        if (texts.length != newActions.length) {
            throw new IllegalArgumentException("Hver option skal have en handling");
        }
        this.options.addAll(List.of(texts));
        this.action.addAll(List.of(newActions));
    }

    public void choice(int index) {
        action.get(index).run();
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public MenuEnum getPage() {
        return page;
    }

    

}


