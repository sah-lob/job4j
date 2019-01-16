package arrays;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    RoleStore roleStore;
    Role role1 = new Role("101");
    Role role2 = new Role("102");
    Role role3 = new Role("103");
    Role role4 = new Role("104");
    Role role5 = new Role("105");

    @Before
    public void setUp() {
        roleStore = new RoleStore(5);
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        roleStore.add(role4);
        roleStore.add(role5);
    }

    @Test
    public void addThreeUserElements() {
        assertThat(roleStore.findById("101"), is(role1));
        assertThat(roleStore.findById("102"), is(role2));
        assertThat(roleStore.findById("103"), is(role3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void replaceThirdUserElement() {
        Role role6 = new Role("999");
        roleStore.replace("101", role6);
        assertThat(roleStore.findById("999"), is(role6));
        assertThat(roleStore.findById("101"), is(role1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeThirdElement() {
        assertThat(roleStore.findById("103"), is(role3));
        roleStore.delete("103");
        roleStore.findById("103");
    }
}