package com.zed.foodrecipes.controller;

import com.zed.foodrecipes.model.Recipe;
import com.zed.foodrecipes.repository.RecipesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Arnaud on 01/05/2015.
 */

@RestController
@RequestMapping("/api/recipes")
public class RecipesController {

    /**
     * Class Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RecipesController.class);

    @Autowired
    RecipesRepository recipesRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Recipe> getAllRecipes() {
        return StreamSupport.stream(recipesRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Recipe getRecipesById(@PathVariable String id) {
        return recipesRepository.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Recipe addRecipe(@Valid @RequestBody Recipe recipe) {
        return recipesRepository.save(recipe);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Recipe updateRecipe(@Valid @RequestBody Recipe recipe) {
        return recipesRepository.save(recipe);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRecipe(@PathVariable String id) {
        recipesRepository.delete(id);
    }


}
