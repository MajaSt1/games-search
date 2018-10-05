package com.search.gamessearch.repository;

import com.search.gamessearch.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
