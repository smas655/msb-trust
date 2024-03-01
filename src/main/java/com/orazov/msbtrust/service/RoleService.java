package com.orazov.msbtrust.service;

import com.orazov.msbtrust.entity.Role;
import com.orazov.msbtrust.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
