package com.instant.persistence.repository.user;


import com.instant.persistence.model.security.SpringUser;

/**
 * @author sroshchupkin
 */
public interface SpringUserRepositoryCustom {

    boolean isSpringUserExists(String username, String email);

    boolean isExistsByEmail(String email);

    boolean isExistsByUsername(String username);

    Iterable<SpringUser> search(String search);

}
