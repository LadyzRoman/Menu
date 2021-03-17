package menu.impl;

import menu.AbstractMenu;
import menu.Menu;
import menu.MenuElement;

import java.util.*;

public class IntKeyMenu extends AbstractMenu<Integer> {

    protected IntKeyMenu(Map<Integer, MenuElement<Integer>> menu, String title) {
        super(menu, title);
    }

    @Override
    public void print() {
        System.out.println(title);
        for (var entry: menu.entrySet()) {
            System.out.printf("%d) %s\n",entry.getKey(), entry.getValue());
        }
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder implements Menu.Builder<Integer> {
        protected Map<Integer, MenuElement<Integer>> menu = new LinkedHashMap<>();
        protected String title;

        protected Builder() {
        }

        @Override
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        @Override
        public Builder addOption(Integer key, MenuElement<Integer> element) {
            menu.put(key, element);
            return this;
        }

        public Builder addOption(MenuElement<Integer> element) {
            int key = menu.size();
            menu.put(key, element);
            return this;
        }

        @Override
        public Builder reset() {
            this.title = null;
            this.menu.clear();
            return this;
        }

        @Override
        public IntKeyMenu build() {
            return new IntKeyMenu(new LinkedHashMap<>(menu), title);
        }
    }
}
