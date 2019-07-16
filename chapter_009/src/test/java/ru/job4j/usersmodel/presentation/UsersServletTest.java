package ru.job4j.usersmodel.presentation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.usersmodel.User;
import ru.job4j.usersmodel.logic.Validate;
import ru.job4j.usersmodel.logic.ValidateService;
import ru.job4j.usersmodel.logic.ValidateStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@PrepareForTest(ValidateService.class)
@RunWith(PowerMockRunner.class)
public class UsersServletTest {

    @Test
    public void whenAddUserThenGetIt() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("email")).thenReturn("email");
        when(req.getParameter("admin")).thenReturn("yes");
        when(req.getParameter("password")).thenReturn("password");
        new UserCreateServlet().doPost(req, resp);
        User user = validate.findAll().iterator().next();
        assertThat(user.getName(), is("name"));
        assertThat(user.getLogin(), is("login"));
        assertThat(user.getPassword(), is("password"));
        assertThat(user.getEmail(), is("email"));
        assertThat(user.isAdmin(), is(true));
    }

    @Test
    public void whenUpdateUser() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        var name = "1";
        var login = "2";
        var email = "3";
        var createDate = "date";
        var admin = false;
        var password = "password";
        validate.add(new User(name, login, email, createDate, admin, password));
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn(name);
        when(req.getParameter("login")).thenReturn(login);
        when(req.getParameter("email")).thenReturn(email);
        when(req.getParameter("admin")).thenReturn(String.valueOf(admin));
        when(req.getParameter("password")).thenReturn(password);
        new UserUpdateServlet().doPost(req, resp);
        var newUser = validate.findAll().iterator().next();

        assertThat(newUser.getName(), is(name));
        assertThat(newUser.getLogin(), is(login));
        assertThat(newUser.getPassword(), is(password));
        assertThat(newUser.getEmail(), is(email));
        assertThat(newUser.isAdmin(), is(false));
    }

    @Test
    public void whenDeleteUser() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        var name = "1";
        var login = "2";
        var email = "3";
        var createDate = "date";
        var admin = false;
        var password = "password";
        validate.add(new User(name, login, email, createDate, admin, password));
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        new UsersServlet().doPost(req, resp);
        assertThat(validate.findAll().size(), is(0));
    }
}