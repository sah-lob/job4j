package arrays;

public abstract class AbstractStore<E extends Base> implements Store<E> {

    private SimpleArray<E> simpleArray;

    public AbstractStore(int len) {
        simpleArray = new SimpleArray<>(len);
    }

    @Override
    public void add(E model) {
        simpleArray.add(model);
    }

    @Override
    public boolean replace(String id, E model) {
        boolean res = false;
        for (int i = 0; i < simpleArray.length(); i++) {
            if (simpleArray.get(i) != null && simpleArray.get(i).getId().equals(id)) {
                simpleArray.set(i, model);
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        for (int i = 0; i < simpleArray.length(); i++) {
            if (simpleArray.get(i) != null && simpleArray.get(i).getId().equals(id)) {
                simpleArray.remove(i);
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public E findById(String id) {
        E res = null;
        for (int i = 0; i < simpleArray.length(); i++) {
            if (simpleArray.get(i) != null && simpleArray.get(i).getId().equals(id)) {
                res = simpleArray.get(i);
                break;
            }
        }
        if (res == null) {
            throw new AssertionError();
        }
        return res;
    }

}