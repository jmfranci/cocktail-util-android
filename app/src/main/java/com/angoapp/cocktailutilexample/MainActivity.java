package com.angoapp.cocktailutilexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.angoapp.cocktail_util.builder.CocktailQueryBuilder;
import com.angoapp.cocktail_util.listener.DataListener;
import com.angoapp.cocktail_util.model.Recipe;
import com.angoapp.cocktail_util.services.MyService;
import com.angoapp.cocktail_util.services.RecipesUtil;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Recipe> mRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyService myService = new MyService();
        CocktailQueryBuilder cocktailQueryBuilder = new CocktailQueryBuilder();

        cocktailQueryBuilder.build(this, new DataListener() {
            @Override
            public void onSuccess(List<Recipe> recipes) {
                mRecipeList = recipes;
                displayData();
            }

            @Override
            public void onError(Error e) {

            }
        });

//        myService.getRecipes(getApplicationContext(), new MyService.DataListener() {
//            @Override
//            public void onSuccess(List<Recipe> recipes) {
//                mRecipeList = recipes;
//                displayData();
//            }
//
//            @Override
//            public void onError(Error e) {
//                Log.e("ERROR OCCURRED", e.getMessage());
//            }
//        });
//
//        getRecipes();
    }

    private void displayData() {
        for (Recipe recipe :
                mRecipeList) {
            Log.i("RECIPES_LIST_NEW_METHOD", recipe.getName());
        }
    }

//    private void getRecipes() {
//        Log.e("getRecipes()", "chegeui");
//        Intent intent = new Intent(this, MyService.class);
//        startService(intent);
//    }
}
