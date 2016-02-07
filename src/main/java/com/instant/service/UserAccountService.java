package com.instant.service;

import com.instant.persistence.model.social.UserAccount;
import com.instant.persistence.model.social.UserRoleType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * @author sroshchupkin
 */
public interface UserAccountService extends SocialUserDetailsService, UserDetailsService {

    /**
     * Creates a new UserAccount with user social network account Connection Data and UserProfile.
     * Default has ROLE_USER role.
     *
     * @param data
     * @param profile
     * @return
     */
    UserAccount createUserAccount(ConnectionData data, UserProfile profile);

    /**
     * Add role to user account.
     *
     * @param userId
     * @param role
     */
    UserAccount addRole(String userId, UserRoleType role);

    /**
     * Remove role from user account.
     *
     * @param userId
     * @param role
     */
    UserAccount removeRole(String userId, UserRoleType role);

    /**
     * Override SocialUserDetailsService.loadUserByUserId(String userId) to
     * return UserAccount.
     */
    UserAccount loadUserByUserId(String userId) throws UsernameNotFoundException;

    /**
     * Gets current logged in user. Reload UserAccount object from database.
     *
     * @return UserAccount object from database for current user. Null if no logged in user.
     */
    UserAccount getCurrentUser();

}

