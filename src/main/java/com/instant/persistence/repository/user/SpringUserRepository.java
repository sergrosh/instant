package com.instant.persistence.repository.user;


import com.instant.persistence.model.SpringUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sroshchupkin
 */
@Repository
public interface SpringUserRepository extends PagingAndSortingRepository<SpringUser, String>, SpringUserRepositoryCustom {

    SpringUser findByUsername(String username);

    SpringUser findByEmail(String email);

}
