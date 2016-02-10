package com.instant.persistence.repository.social;

import com.instant.persistence.model.social.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

    UserAccount findByUserId(String userId);

    List<UserAccount> findAll(Sort sort);

    Page<UserAccount> findAllOrderByUserId(Pageable pageable);
}

