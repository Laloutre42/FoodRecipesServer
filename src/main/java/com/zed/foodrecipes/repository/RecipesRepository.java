package com.zed.foodrecipes.repository;

/**
 * Created by Arnaud on 30/04/2015.
 */

import com.zed.foodrecipes.model.Recipe;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipesRepository extends CrudRepository<Recipe, String> {

}
