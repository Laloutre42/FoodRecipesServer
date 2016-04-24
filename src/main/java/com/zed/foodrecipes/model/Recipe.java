package com.zed.foodrecipes.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Arnaud on 03/05/2015.
 */
@Data
@Entity
public class Recipe {

    public static final String IMAGES_RECIPES_PATH = "/assets/images/foodrecipes/recipes/165-124/";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;
    private String description;
    private String type;
    private String comment;
    private int status;
    private int complexity;
    private int result;
    private int duration;
    private int personNumber;
    private String webLink;
    private String imagePath;
    private boolean done;
    private boolean todo;
    private String ingredients;

    public Recipe() {
    }

}
