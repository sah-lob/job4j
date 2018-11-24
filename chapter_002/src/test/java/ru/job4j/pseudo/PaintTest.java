package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Lobachev (sah-lob@ya.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));

    }

    @After
    public void backOutput() {
        System.setOut(System.out);
        System.out.println("execute after method");
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        StringBuilder pic = new StringBuilder();
        pic.append("+++++++\n");
        pic.append("+     +\n");
        pic.append("+     +\n");
        pic.append("+++++++");
        String result = pic.toString();
        assertThat(new String(out.toByteArray()), is(result));
    }

    @Test
    public void whenDrawTriange() {
        new Paint().draw(new Triangle());
        StringBuilder pic = new StringBuilder();
        pic.append("   +\n");
        pic.append("  + +\n");
        pic.append(" +   +\n");
        pic.append("+++++++");
        String result = pic.toString();
        assertThat(new String(out.toByteArray()), is(result));
    }
}