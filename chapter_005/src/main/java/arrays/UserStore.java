package arrays;

import java.util.NoSuchElementException;

public class UserStore extends AbstractStore {

    public UserStore(int len) {
        super(len);
    }


    @Override
    public int idToIndex(String id) {
        var result = -1;
        for (int i = 0; i < simpleArray.length(); i++) {
            User u =  (User) simpleArray.get(i);
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
