package menu.impl;

import menu.AbstractMenu;
import menu.Menu;
import menu.MenuElement;
import menu.MenuException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class IntKeyMenuTest {

    private Menu<Integer> menu;
    private Menu<Integer> menu2;

    @Before
    public void buildMenu() {
        AbstractMenu.Builder<Integer> builder = IntKeyMenu.getBuilder();
        menu = builder
                .reset()
                .setTitle("Test menu")
                .addOption(1, new MenuElement<>("test1", (e) -> { }))
                .addOption(2, new MenuElement<>("test2", (e) -> { }))
                .addOption(3, new MenuElement<>("test3", (e) -> { }))
                .addOption(4, new MenuElement<>("test4", (e) -> { }))
                .addOption(5, new MenuElement<>("test5", (e) -> { }))
                .addOption(6, new MenuElement<>("test6", (e) -> { }))
                .addOption(0, new MenuElement<>("exit", e -> {}))
                .build();

        menu2 = builder
                .reset()
                .setTitle("Test menu")
                .addOption(new MenuElement<>("test1", (e) -> { }))
                .addOption(new MenuElement<>("test2", (e) -> { }))
                .addOption(new MenuElement<>("test3", (e) -> { }))
                .build();
    }

    @Test
    public void menuPrintedInRightOrder() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        var printStream = new PrintStream(out);
        PrintStream sout = System.out;
        System.setOut(printStream);
        menu.print();
        assertEquals("Test menu\n" +
                        "1) test1\n" +
                        "2) test2\n" +
                        "3) test3\n" +
                        "4) test4\n" +
                        "5) test5\n" +
                        "6) test6\n" +
                        "0) exit"
                , out.toString().trim().replace("\r", "")
        );
        System.setOut(sout);
    }

    @Test
    public void menuExceptionWhenPassWrongArgument() {
        assertThrows(MenuException.class, () -> menu.getAction(8));
    }

    @Test
    public void getMenuElementWhenPassRightArgument() {
        assertEquals("test2", menu.getAction(2).toString());
        assertEquals("exit", menu.getAction(0).toString());
        assertEquals("test1", menu.getAction(1).toString());
        assertEquals("test3", menu.getAction(3).toString());
    }

    @Test
    public void autoIndexingGoesSequentiallyFromZero() {
        assertEquals("test1", menu2.getAction(0).toString());
        assertEquals("test2", menu2.getAction(1).toString());
        assertEquals("test3", menu2.getAction(2).toString());
    }
}