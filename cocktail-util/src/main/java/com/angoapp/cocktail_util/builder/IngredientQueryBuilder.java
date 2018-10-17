package com.angoapp.cocktail_util.builder;

import android.app.Activity;
import android.content.Intent;

import com.angoapp.cocktail_util.listener.DataListener;
import com.angoapp.cocktail_util.listener.TagsDataListener;
import com.angoapp.cocktail_util.services.MyService;
import com.angoapp.cocktail_util.services.TagsService;

public class IngredientQueryBuilder {

    public IngredientQueryBuilder getAllIngredientTags(){ return this; }

    public void build(Activity activity, TagsDataListener listener){
        TagsService myService = new TagsService();
        myService.getTags(listener);
        startService(activity);
    }

    private void startService(Activity activity){
        Intent intent = new Intent(activity, TagsService.class);
        activity.startService(intent);
    }
}
