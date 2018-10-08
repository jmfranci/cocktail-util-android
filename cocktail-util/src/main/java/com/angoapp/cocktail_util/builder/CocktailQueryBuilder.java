package com.angoapp.cocktail_util.builder;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.angoapp.cocktail_util.listener.DataListener;
import com.angoapp.cocktail_util.model.Recipe;
import com.angoapp.cocktail_util.services.MyService;

import java.util.List;

public class CocktailQueryBuilder{
    private String id;
    private String[] tags;
    private int limit;
    private String category;
    private boolean alcoholic = true;
    private boolean non_alcoholic = true;

    public final CocktailQueryBuilder withId(String id){this.id = id; return this;};
    public final CocktailQueryBuilder limitTo(int limit){ this.limit = limit; return this; }
    public final CocktailQueryBuilder withCategory(String category){this.category = category; return this;}
    public final CocktailQueryBuilder withTags(String[] tags){this.tags = tags; return this;}

    public void build(Activity activity, DataListener listener){
        startService(activity);
        MyService myService = new MyService();
        myService.getRecipes(activity.getApplicationContext(), listener);
    }

    private void startService(Activity activity){
        Log.i("CocktailQuery Builder", "Called");
        Intent intent = new Intent(activity, MyService.class);
        activity.startService(intent);
    }
}
