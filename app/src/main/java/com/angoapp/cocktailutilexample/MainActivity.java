package com.angoapp.cocktailutilexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.angoapp.cocktail_util.activities.SearchActivity;
import com.angoapp.cocktail_util.builder.CocktailQueryBuilder;
import com.angoapp.cocktail_util.builder.IngredientQueryBuilder;
import com.angoapp.cocktail_util.listener.DataListener;
import com.angoapp.cocktail_util.listener.TagsDataListener;
import com.angoapp.cocktail_util.model.CocktailQuery;
import com.angoapp.cocktail_util.model.Recipe;
import com.angoapp.cocktail_util.services.MyService;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Recipe> mRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CocktailQueryBuilder builder = new CocktailQueryBuilder().withTags( new String[]{"Chartreuse", "Whiskey", "Vermouth"} );
////
////        builder.build(this, new DataListener() {
////            @Override
////            public void onSuccess(List<Recipe> recipes) {
////                mRecipeList = recipes;
////                displayData();
////            }
////
////            @Override
////            public void onError(Error e) {
////                Log.e("My Error Message", e.getMessage());
////            }
////        });

//        new IngredientQueryBuilder().getAllIngredientTags().build(this, new TagsDataListener() {
//            @Override
//            public void onSuccess(String[] tags) {
//                Log.i("__TAGS__", "tags");
//                for (String str : tags){
//                    Log.i("_NEW_TAG_", str);
//                }
//            }
//
//            @Override
//            public void onError(Error e) {
//
//            }
//        });
    }

    private void displayData() {
        for (Recipe recipe :
                mRecipeList) {
            Log.i("RECIPES_LIST_NEW_METHOD", recipe.getName());
        }
    }

    public void openSearchActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

}
