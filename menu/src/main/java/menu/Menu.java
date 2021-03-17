package menu;

/**
 * An object that represent console menu.
 * It can be used with different key types
 * @param <T> menu keys type
 */
public interface Menu<T> {

    /**
     * Method in witch menu printing happens
     */
    void print();

    /**
     * Base method to interact with the menu.
     * Method returns action tied with particular option
     * @param option key menu element
     * @return action tied to this option
     * @throws MenuException if the key is not present in the menu
     */
    MenuElement<T> getAction(T option) throws MenuException;

    /**
     * Returns the number of menu items in the menu.
     * @return the number of menu items in the menu
     */
    int getSize();

    /**
     * @return menu title
     */
    String getTitle();

    /**
     * Implementation of builder pattern for {@link AbstractMenu} class.
     * Use this class to create particular menu.
     * @param <T> menu keys type
     */
    interface Builder<T> {
        /**
         * Sets the menu title
         * @param title menu title
         * @return this builder instance
         */
        Builder<T> setTitle(String title);

        /**
         * Add new menu item with specific {@code key}.
         * This item then can be executed by this {@code key}
         * @param key menu key
         * @param element new menu item with action that can be performed
         * @return this builder instance
         */
        Builder<T> addOption(T key, MenuElement<T> element);

        /**
         * Add new menu item without specific {@code key}
         * A key value will be calculated internally
         * @param element new menu item with action that can be performed
         * @return this builder instance
         * @throws UnsupportedOperationException if for this menu is impossible to calculate key internally
         */
        Builder<T> addOption(MenuElement<T> element) throws UnsupportedOperationException;

        /**
         * Resets the builder to create new menu instance
         * @return this builder instance
         */
        Builder<T> reset();

        Menu<T> build();
    }
}
