package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Lobachev (sah-lob@ya.ru)
 * @version $Id$
 * @since 0.1
 */

public class TriangleTest {
    @Test
    public void whenDrawSquare() {
        Triangle triangle = new Triangle();
        StringBuilder pic = new StringBuilder();
        pic.append("   +\n");
        pic.append("  + +\n");
        pic.append(" +   +\n");
        pic.append("+++++++");
        String result = pic.toString();
        assertThat(triangle.draw(), is(result));
    }
}
