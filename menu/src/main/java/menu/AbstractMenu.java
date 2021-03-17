package menu;

import java.util.Map;
import java.util.Optional;


public abstract class AbstractMenu<T> implements Menu<T> {
    /**
     * A map where all menu items a stored.
     */
    protected Map<T, MenuElement<T>> menu;

    /**
     * Menu title that can be printed on the screen.
     */
    protected String title;

    /**
     * Constructor for this object. Do not use.
     * For constructing a menu use method {@code getBuilder()}
     * @param menu map where stored all menu items
     * @param title menu title
     */
    protected AbstractMenu(Map<T, MenuElement<T>> menu, String title) {
        this.menu = menu;
        this.title = title;
    }


    public MenuElement<T> getAction(T option) throws MenuException {
        var element = getMenuElementByOption(option);
        return element
                .orElseThrow(() -> new MenuException("No menu element for key " + option));
    }

    /**
     * Override this method if you need another way to obtain
     * action (regEx menu)
     * @param option key menu element
     * @return menu element from map by key
     */
    protected Optional<MenuElement<T>> getMenuElementByOption(T option) {
        MenuElement<T> element = menu.get(option);
        if (element == null) {
            return Optional.empty();
        } else {
            return Optional.of(element);
        }
    }

    public int getSize() {
        return menu.size();
    }

    public String getTitle() {
        return title;
    }
}
