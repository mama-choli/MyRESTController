package com.example.myrestcontroller.service;

import com.example.myrestcontroller.model.Role;
import com.example.myrestcontroller.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getByRoleName(String name) {
        return roleRepository.getByRoleName(name);
    }


}

