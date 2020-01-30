package com.nefedov.project.security.jwt;

import com.nefedov.project.model.Role;
import com.nefedov.project.model.UserSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserSecurity user) {
        return new JwtUser(
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        List<GrantedAuthority> result = new ArrayList<>();
        userRoles.forEach(role -> {
            result.add(new SimpleGrantedAuthority(role.getRole()));
        });
        return result;
    }
}
