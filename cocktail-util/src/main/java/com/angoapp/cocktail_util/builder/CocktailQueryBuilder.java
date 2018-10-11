package com.angoapp.cocktail_util.builder;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.angoapp.cocktail_util.CocktailDebug;
import com.angoapp.cocktail_util.listener.DataListener;
import com.angoapp.cocktail_util.model.CocktailQuery;
import com.angoapp.cocktail_util.model.Recipe;
import com.angoapp.cocktail_util.services.MyService;

import java.util.List;

public class CocktailQueryBuilder{
    private CocktailQuery query;

    public CocktailQueryBuilder(){
        query = new CocktailQuery();
    }
    public final CocktailQueryBuilder withId(String id){query.setId(id); return this;};
    public final CocktailQueryBuilder limitTo(int limit){ query.setLimit(limit); return this; }
    public final CocktailQueryBuilder withCategory(String category){query.setCategory(category); return this;}
    public final CocktailQueryBuilder withTags(String tags[]){query.setTags(tags); return this;}
    public final CocktailQueryBuilder onlyAlcoholic(){query.setNon_alcoholic(false); query.setAlcoholic(true); return this; }
    public final CocktailQueryBuilder onlyNonAlcoholic(){query.setAlcoholic(false); query.setNon_alcoholic(true); return this; }

    public void build(Activity activity, DataListener listener){
        MyService myService = new MyService();
        myService.getRecipes(listener, query);
        startService(activity);
    }

    private void startService(Activity activity){
        Intent intent = new Intent(activity, MyService.class);
        activity.startService(intent);
    }
}
