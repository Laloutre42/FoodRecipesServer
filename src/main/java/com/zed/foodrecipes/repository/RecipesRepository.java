package com.zed.foodrecipes.repository;

/**
 * Created by Arnaud on 30/04/2015.
 */

import com.zed.foodrecipes.model.Recipe;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends CrudRepository<Recipe, String> {

    @Query("{ 'user._id' : ?0 }")
    List<Recipe> findByUserId(ObjectId objectId);

    List<Recipe> findByStatus(int status);
}
