package com.bookshop.service.impl;

import com.bookshop.entity.Role;
import com.bookshop.repository.RoleRepository;
import com.bookshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findById(Long id) {
        Role role=roleRepository.findOne(id);
        List<Role> roles=new ArrayList<>();
        roles.add(role);
        return roles;

    }
}
