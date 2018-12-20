package ru.job4j.maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = (HashMap<Integer, User>) list.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        return result;
    }
}
