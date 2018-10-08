package com.angoapp.cocktail_util.services;

import com.angoapp.cocktail_util.model.Recipe;

import java.util.List;

public class RecipesUtil {
    private static final RecipesUtil instance = new RecipesUtil();
    private List<Recipe> recipes;

    private RecipesUtil(){

    }

    public static final RecipesUtil getInstance(){
        return instance;
    }

    public List<Recipe> getRecipes(){
        return recipes;
    }
}
