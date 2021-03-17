package menu.impl;

import menu.MenuElement;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class RegexKeyMenu extends StringKeyMenu {
    protected RegexKeyMenu(Map<String, MenuElement<String>> menu, String title) {
        super(menu, title);
    }

    @Override
    protected Optional<MenuElement<String>> getMenuElementByOption(String option) {
        return menu.entrySet()
                .stream()
                .filter(e -> option.matches(e.getKey()))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder extends StringKeyMenu.Builder {

        @Override
        public Builder setTitle(String title) {
            super.setTitle(title);
            return this;
        }

        @Override
        public Builder addOption(String regex, MenuElement<String> element) {
            super.addOption(regex, element);
            return this;
        }

        @Override
        public Builder addOption(MenuElement<String> element) {
            super.addOption(element);
            return this;
        }

        @Override
        public Builder reset() {
            super.reset();
            return this;
        }

        @Override
        public RegexKeyMenu build() {
            return new RegexKeyMenu(new LinkedHashMap<>(menu), title);
        }
    }
}
