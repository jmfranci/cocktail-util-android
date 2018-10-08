package com.angoapp.cocktail_util.services;

import com.angoapp.cocktail_util.model.Recipe;

import java.util.List;

public class RecipesUtil {
    private static final RecipesUtil instance = new RecipesUtil();
    private List<Recipe> recipes;

    private RecipesUtil(){


//        CocktailClient client = retrofit.create(CocktailClient.class);
//        Call<List<Recipe>> call = client.recipesAvailable();
//
//        call.enqueue(new Callback<List<Recipe>>() {
//            @Override
//            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
//                recipes = response.body();
//                recipes.notifyAll();
//                for (Recipe recipe:
//                     recipes) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Recipe>> call, Throwable t) {
//                Log.e("Error occurred", t.getMessage());
//            }
//        });
    }

    public static final RecipesUtil getInstance(){
        return instance;
    }

    public List<Recipe> getRecipes(){
        return recipes;
    }
}
