package com.theoss.jwtbackend.entity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.theoss.jwtbackend.entity.Permissions.ADMIN_WORK;
import static com.theoss.jwtbackend.entity.Permissions.USER_WORK;;

@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(
            ADMIN_WORK

    )),
    USER(Set.of(
        USER_WORK
    ));

    @Getter
    private final Set<Permissions>permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
