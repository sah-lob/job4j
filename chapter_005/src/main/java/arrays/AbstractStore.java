package arrays;

public abstract class AbstractStore<E extends Base> implements Store<E> {

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
    public E findById(String id) {
        return (E) simpleArray.get(idToIndex(id));
    }

    public abstract int idToIndex(String id);
}