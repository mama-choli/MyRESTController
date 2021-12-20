package com.example.myrestcontroller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserJson {
    private long id;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String[] roles;
    private String password;

    public UserJson() {
    }

    public UserJson(long id, String name, String lastname, String email, String username, String[] roles, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.roles = roles;
        this.password = password;
    }

    public User toUser() {
        return new User(
                this.name,
                this.lastname,
                this.email,
                this.username,
                this.password
        );
    }

    public static UserJson fromUser(User user) {
        UserJson userJson = new UserJson();
        userJson.setId(user.getId());
        userJson.setName(user.getName());
        userJson.setLastname(user.getLastname());
        userJson.setEmail(user.getEmail());
        userJson.setUsername(user.getUsername());
        userJson.setPassword(user.getPassword());
        userJson.setRoles(user.getRoles().stream().map(Role::getRoleName).sorted().toArray(String[]::new));
        return userJson;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
