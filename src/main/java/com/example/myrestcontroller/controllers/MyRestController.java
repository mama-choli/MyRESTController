package com.example.myrestcontroller.controllers;

import com.example.myrestcontroller.model.User;
import com.example.myrestcontroller.model.UserJson;
import com.example.myrestcontroller.service.RoleService;
import com.example.myrestcontroller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin")
public class MyRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MyRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserJson>> getAllUsers() {
        final List<User> users = userService.getAllUsers();
        ArrayList<UserJson> userJsonArrayList = new ArrayList<>();

        for (User user : users) {
            userJsonArrayList.add(UserJson.fromUser(user));
        }

        return !userJsonArrayList.isEmpty()
                ? new ResponseEntity<>(userJsonArrayList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        final boolean result = userService.deleteUser(id);

        return result
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserJson> getUser(@PathVariable("id") int id) {
        final User user = userService.getUser(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserJson userJson = UserJson.fromUser(user);

        return new ResponseEntity<>(userJson, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<User> addNewUser(@RequestBody UserJson userJson) {
        User user = userJson.toUser();
        user.setRoles(Arrays.stream(userJson.getRoles()).map(roleService::getByRoleName).collect(Collectors.toSet()));
        userService.addUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody UserJson userJson) {
        User user = userJson.toUser();
        user.setId(id);
        user.setRoles(Arrays.stream(userJson.getRoles()).map(roleService::getByRoleName).collect(Collectors.toSet()));
        final boolean result = userService.updateUser(user);

        return result
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
