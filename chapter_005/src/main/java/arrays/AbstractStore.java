package arrays;

public abstract class AbstractStore implements Store {

    SimpleArray simpleArray;

    public AbstractStore(int len) {

        simpleArray = new SimpleArray<User>(len);
    }

    @Override
    public void add(Base model) {
        simpleArray.add(model);
    }

    @Override
    public boolean replace(String id, Base model) {
        simpleArray.set(idToIndex(id), model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        simpleArray.remove(idToIndex(id));
        return true;
    }

    @Override
    public Base findById(String id) {
        return (Base) simpleArray.get(idToIndex(id));
    }

    public abstract int idToIndex(String id);
}
