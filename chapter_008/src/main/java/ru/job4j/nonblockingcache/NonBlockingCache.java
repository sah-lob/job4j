package ru.job4j.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCache {

    private ConcurrentHashMap<Integer, Base> models = new ConcurrentHashMap<>();

    public void add(Base model) {
        models.putIfAbsent(model.getId(), model);
    }


    public boolean update(Base model) {
        var result = false;
        if (models.contains(model.getId())) {
            if (models.get(model.getId()).getVersion() >= model.getVersion()) {
                throw new OptimisticException();
            }
            model.setVersion(model.getVersion() + 1);
            add(model);
            result = true;
        }
        return result;
    }
}
