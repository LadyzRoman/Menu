package menu.impl;

import junit.framework.TestCase;
import menu.Menu;
import menu.MenuElement;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class StringKeyMenuTest {

    private Menu<String> menu;

    @Before
    public void init() {
        menu = StringKeyMenu.getBuilder()
                .setTitle("Title")
                .addOption(new MenuElement<>("print", e -> {}))
                .addOption(new MenuElement<>("title2", e -> {}))
                .addOption(new MenuElement<>("exit", e -> {}))
                .build();
    }

    @Test
    public void menuPrintedAsExpected() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        var printStream = new PrintStream(out);
        PrintStream sout = System.out;
        System.setOut(printStream);
        menu.print();

        assertEquals("Title (print, title2, exit):", out.toString().trim());
        System.setOut(sout);
    }
}