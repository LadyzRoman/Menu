package menu;

import java.util.function.Consumer;

public class MenuElement<T> {
    private String title;
    private Consumer<T> action;

    public MenuElement(String title, Consumer<T> action) {
        this.title = title;
        this.action = action;
    }

    public void execute(T activator) {
        action.accept(activator);
    }

    @Override
    public String toString() {
        return title;
    }
}
