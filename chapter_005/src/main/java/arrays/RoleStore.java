package arrays;

import java.util.NoSuchElementException;

public class RoleStore extends AbstractStore {

    public RoleStore(int len) {
        super(len);
    }


    @Override
    public int idToIndex(String id) {
        var result = -1;
        for (int i = 0; i < simpleArray.length(); i++) {
            Role u =  (Role) simpleArray.get(i);
            if (u.getId().equals(id)) {
                result = i;
                break;
            }
        }
        if (result == -1) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
