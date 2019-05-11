package ru.job4j.templates;

import java.util.HashMap;
import java.util.regex.Pattern;

public class TemplateAction implements Template {

    @Override
    public String generate(String template, HashMap<String, String> map) {
        var pattern = Pattern.compile("(\\$\\{).*?(})");
        var matcher = pattern.matcher(template);
        while (matcher.find()) {
            var key = matcher.group().substring(2, matcher.group().length() - 1);
            if (map.containsKey(key)) {
                var matcher1 = Pattern.compile("(\\$\\{)" + key + "(})").matcher(template);
                template = matcher1.replaceAll(map.get(key));
                map.remove(key);
            } else {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (map.size() > 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return template;
    }
}
