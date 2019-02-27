package ru.job4j.inputoutput;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class ArchiveTest {

    @Test
    public void test() {
        Archive archive = new Archive();
        HashSet<String> keys = new HashSet<>();
        keys.addAll(Arrays.asList("jpg", "pdf", "png"));
        archive.archiveProject("project", "/Users/alexanderlobachev/Desktop/testing", keys);
    }

}