package com.zed.foodrecipes.repository;

/**
 * Created by Arnaud on 30/04/2015.
 */

import com.zed.foodrecipes.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByNameAndPassword(String name, String password);
    User findByNameProviderId(String nameProviderId);
    User findByName(String name);

}
