package menu.impl;

import menu.AbstractMenu;
import menu.Menu;
import menu.MenuElement;

import java.util.*;
import java.util.stream.Collectors;

public class StringKeyMenu extends AbstractMenu<String> {

    protected StringKeyMenu(Map<String, MenuElement<String>> menu, String title) {
        super(menu, title);
    }

    @Override
    public void print() {
        List<String> actions = menu.values()
                .stream()
                .map(MenuElement::toString)
                .collect(Collectors.toList());

        var line = actions.toString();
        System.out.print(title
                + " ("
                + line.substring(1, line.length() - 1)
                + "): "
        );
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder implements Menu.Builder<String> {
        protected Map<String, MenuElement<String>> menu = new LinkedHashMap<>();
        protected String title;

        @Override
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        @Override
        public Builder addOption(String key, MenuElement<String> element) {
            menu.put(key, element);
            return this;
        }

        @Override
        public Builder addOption(MenuElement<String> element) {
            menu.put(element.toString(), element);
            return this;
        }

        @Override
        public Builder reset() {
            this.title = null;
            this.menu.clear();
            return this;
        }

        @Override
        public StringKeyMenu build() {
            return new StringKeyMenu(new LinkedHashMap<>(menu), title);
        }
    }
}
