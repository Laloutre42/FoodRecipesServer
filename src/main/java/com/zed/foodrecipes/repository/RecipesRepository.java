package com.zed.foodrecipes.repository;

/**
 * Created by Arnaud on 30/04/2015.
 */

import com.zed.foodrecipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipesRepository extends CrudRepository<Recipe, String> {

}
