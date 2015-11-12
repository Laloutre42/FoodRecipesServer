package com.zed.foodrecipes.model;

import com.zed.foodrecipes.service.Operations;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Arnaud on 03/05/2015.
 */
@Data
@Document
public class Recipe {

    public static final String IMAGES_RECIPES_PATH = "/assets/images/foodrecipes/recipes/165-124/";
    @Id
    private String id;
    private String name;
    private String description;
    private String type;
    private String comment;
    private int complexity;
    private int result;
    private int duration;
    private int personNumber;
    private String webLink;
    private String imagePath;
    private boolean done;
    private boolean todo;
    private User user;
    private Collection<String> ingredients = new ArrayList<String>();

    public Recipe(){
    }

    /**
     * Create a recipe from a list of parameters
     * @param listFields
     * @return
     */
    public static Recipe createRecipe(List<String> listFields){

        int i=0;
        Recipe recipe = new Recipe();

        recipe.setId(listFields.get(i++));
        recipe.setName(listFields.get(i++));
        recipe.setDescription(listFields.get(i++));
        recipe.setType(listFields.get(i++));
        recipe.setDuration(Operations.parseInt(listFields.get(i++)));
        recipe.setPersonNumber(Operations.parseInt(listFields.get(i++)));
        recipe.setComplexity(Operations.parseInt(listFields.get(i++)));
        recipe.setResult(Operations.parseInt(listFields.get(i++)));
        recipe.setWebLink(listFields.get(i++));
        recipe.setImagePath(IMAGES_RECIPES_PATH + listFields.get(i++));
        recipe.setDone(Operations.parseBoolean(listFields.get(i++)));
        recipe.setTodo(Operations.parseBoolean(listFields.get(i++)));
        recipe.setComment(listFields.get(i++));

        return recipe;
    }

}
