package com.example.myrestcontroller.service;

import com.example.myrestcontroller.model.Role;
import com.example.myrestcontroller.model.User;
import com.example.myrestcontroller.repositories.RoleRepository;
import com.example.myrestcontroller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userDetailsServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(long id) {
        return userRepository.getById(id);
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        if (!user.getPassword().equals(getUser(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user);
        return false;
    }

    @Override
    @Transactional
    public boolean deleteUser(long id) {
        userRepository.deleteById(id);
        return false;
    }

    @Override
    @Transactional
    public void addInitUsers() {
        User admin = new User("ADMIN", "ADMIN", "admin@gmail.com", "ADMIN", "ADMIN");

        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        addUser(admin);

        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        admin.addRole(adminRole);
        admin.addRole(userRole);

        adminRole.addUser(admin);
        userRole.addUser(admin);

    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.getByUsername(s);
    }
}
