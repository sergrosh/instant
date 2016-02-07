package com.instant.util;


import com.instant.persistence.model.security.SpringUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sroshchupkin
 */
public class SecurityUtils {

    private static final String DEFAULT_ROLE = "ROLE_USER";

    public static Set<? extends GrantedAuthority> toAuthorities(Set<String> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (roles != null && roles.size() != 0) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        } else {
            authorities.add(new SimpleGrantedAuthority(DEFAULT_ROLE));
        }
        return authorities;
    }

    public static SpringUser getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (SpringUser) auth.getPrincipal();
    }
}
