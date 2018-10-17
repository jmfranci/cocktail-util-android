package com.angoapp.cocktail_util.listener;

import com.angoapp.cocktail_util.model.Recipe;

import java.util.List;

public interface TagsDataListener {
    public void onSuccess(String[] tags);
    public void onError(Error e);
}
