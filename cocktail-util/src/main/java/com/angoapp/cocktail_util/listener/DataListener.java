package com.angoapp.cocktail_util.listener;

import com.angoapp.cocktail_util.model.Recipe;

import java.util.List;

public interface DataListener {
    public void onSuccess(List<Recipe> recipes);
    public void onError(Error e);
}
