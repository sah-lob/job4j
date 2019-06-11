package ru.job4j.usersmodel;

import java.util.Objects;

public class User {

    private final int id;
    private final String createDate;
    private String name;
    private String login;
    private String email;


    public User(int id, String name, String login, String email, String createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(String id, String name, String login, String email, String createDate) {
        this.id = Integer.parseInt(id);
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "User{"
                + "id=" + id + ", name='" + name + '\'' + ", login='" + login
                + '\'' + ", email='" + email + '\'' + ", createDate='" + createDate + '\'' + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, email);
    }

    @Override
    public boolean equals(Object o) {
        var result = false;
        if (this == o) {
            result = true;
        } else if (!(o instanceof User)) {
            result = false;
        } else {
            var user = (User) o;
            result = name.equals(user.name)
                    && login.equals(user.login)
                    && email.equals(user.email);
        }
        return result;
    }
}
