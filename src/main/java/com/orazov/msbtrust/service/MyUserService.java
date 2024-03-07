package com.orazov.msbtrust.service;

import com.orazov.msbtrust.dto.RegisterRequest;
import com.orazov.msbtrust.entity.MyUser;
import com.orazov.msbtrust.entity.Role;
import com.orazov.msbtrust.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public Optional<MyUser> findByUsername(String username) {
        return myUserRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public MyUser createNewUser(RegisterRequest request) {
        MyUser user = new MyUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(List.of(roleService.getUserRole()));
        return myUserRepository.save(user);
    }

}

