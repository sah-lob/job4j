package ru.job4j.templates;

import java.util.HashMap;
import java.util.regex.Pattern;

public class TemplateAction implements Template {

    @Override
    public String generate(String template, HashMap<String, String> map) {
        var pattern = Pattern.compile("(\\$\\{).*?(})");
        var matcher = pattern.matcher(template);
        while (matcher.find()) {
            var word =  matcher.group();
            var key = word.substring(2, matcher.group().length() - 1);
            if (!map.containsKey(key)) {
                throw new NullPointerException();
            }
            template = template.replace(word, map.get(key));
            map.remove(key);
        }
        if (map.size() > 0) {
            throw new NullPointerException();
        }
        return template;
    }
}
