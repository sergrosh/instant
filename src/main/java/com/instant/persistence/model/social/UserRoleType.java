package com.instant.persistence.model.social;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author sroshchupkin
 */
public enum UserRoleType implements GrantedAuthority {
    ROLE_ADMIN,  // can manage user account, all posts
    ROLE_AUTHOR, // can manage own posts
    ROLE_USER   // can edit own comment, can edit own profile
    ;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
