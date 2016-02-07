package com.instant.persistence.repository.social;

import com.instant.persistence.model.security.RememberMeToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface RememberMeTokenRepository extends MongoRepository<RememberMeToken, String> {

    RememberMeToken findBySeries(String series);

    List<RememberMeToken> findByUsername(String username);
}