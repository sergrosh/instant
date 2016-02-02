package com.instant.persistence.repository.rememberme;

/**
 * @author sroshchupkin
 */
public interface MongoRememberMeTokenRepositoryCustom {
    void removeUserTokens(String username);
}
