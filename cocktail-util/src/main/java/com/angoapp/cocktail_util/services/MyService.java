package com.angoapp.cocktail_util.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.angoapp.cocktail_util.builder.CocktailQueryBuilder;
import com.angoapp.cocktail_util.listener.DataListener;
import com.angoapp.cocktail_util.model.CocktailQuery;
import com.angoapp.cocktail_util.model.Recipe;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;

public class MyService extends IntentService {

    private List<Recipe> mRecipeList;
    private static DataListener dataListener;
    private static CocktailQuery query;

    public MyService() {
        super("MY_SERVICE");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        MyWebService webService =
                MyWebService.retrofit.create(MyWebService.class);

        Call<Recipe[]> call = null;

        if (query.getTags().length !=0){
            //call = webService.getRecipes(query.getTags());
            call = webService.getRecipes(query);
        }else{
            call = webService.getAllRecipes();
        }

        Recipe[] recipes;

        try {
            recipes = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MyService", "onHandleIntent " + e.getMessage());
            dataListener.onError(new Error(e.getCause()));
            return;
        }

        try {
            mRecipeList = Arrays.asList(recipes);
            dataListener.onSuccess(mRecipeList);
        }catch (NullPointerException e){
            dataListener.onError(new Error("Null pointer exception occurred", e.getCause()));
        }

    }

    public void getRecipes(DataListener listener, CocktailQuery query) {
        this.query = query;
        this.dataListener = listener;
    }
}
