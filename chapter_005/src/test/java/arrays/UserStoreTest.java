package arrays;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    UserStore userStore;
    User user1 = new User("101");
    User user2 = new User("102");
    User user3 = new User("103");
    User user4 = new User("104");
    User user5 = new User("105");

    @Before
    public void setUp() {
        userStore = new UserStore(5);
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        userStore.add(user4);
        userStore.add(user5);
    }

    @Test
    public void addThreeUserElements() {
        assertThat(userStore.findById("101"), is(user1));
        assertThat(userStore.findById("102"), is(user2));
        assertThat(userStore.findById("103"), is(user3));
    }

    @Test(expected = AssertionError.class)
    public void replaceThirdUserElement() {
        User user6 = new User("999");
        userStore.replace("101", user6);
        assertThat(userStore.findById("999"), is(user6));
        assertThat(userStore.findById("101"), is(user1));
    }

    @Test(expected = AssertionError.class)
    public void removeThirdElement() {
        assertThat(userStore.findById("103"), is(user3));
        userStore.delete("103");
        userStore.findById("103");
    }
}