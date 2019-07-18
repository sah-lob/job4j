package ru.job4j.templates;

import java.util.HashMap;

public interface Template {
    String generate(String template, HashMap<String, String> map);
}
