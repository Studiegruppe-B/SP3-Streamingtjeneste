package menu;

import util.TextUI;

import static menu.PageBuilder.buildPages;
import static menu.PageBuilder.startPage;

public class MenuDisplay {


    private final PageBuilder builder = new PageBuilder();

    public void showMenu() {
        builder.buildPages();

        while (builder.isRunning()) {
            PageTemplate current = builder.getCurrent();
            int choice = TextUI.promptMenuChoice(current.getOptions(), "Choose:");

            if (choice >= 1 && choice <= current.getOptions().size()) {
                current.choice(choice - 1);
            } else {
                TextUI.userMessage("Invalid choice");
            }
        }
    }
}
