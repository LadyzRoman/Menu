package menu.impl;


import menu.Menu;
import menu.MenuElement;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class RegexKeyMenuTest {
    private Menu<String> menu;

    @Before
    public void init() {
        menu = RegexKeyMenu.getBuilder()
                .setTitle("Title")
                .addOption("^\\d+$", new MenuElement<>("digitRegex", e -> {}))
                .addOption("^reg( [a-zA-Z]+)?$", new MenuElement<>("wordRegex", e -> {}))
                .addOption(new MenuElement<>("exit", e -> {}))
                .build();
    }

    @Test
    public void regexKeyGetsRightValue() {
        assertEquals("digitRegex", menu.getAction("123").toString());
        assertEquals("wordRegex", menu.getAction("reg abc").toString());
        assertEquals("exit", menu.getAction("exit").toString());
    }

    @Test
    public void menuPrintedAsExpected() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        var printStream = new PrintStream(out);
        PrintStream sout = System.out;
        System.setOut(printStream);
        menu.print();

        assertEquals("Title (digitRegex, wordRegex, exit):", out.toString().trim());
        System.setOut(sout);
    }
}