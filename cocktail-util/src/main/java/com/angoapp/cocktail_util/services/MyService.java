package com.angoapp.cocktail_util.services;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.angoapp.cocktail_util.listener.DataListener;
import com.angoapp.cocktail_util.model.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;

public class MyService extends IntentService {

    public static final String MY_SERVICE_MESSAGE = "MY_SERVICE_MESSAGE";
    public static final String MY_SERVICE_PAYLOAD = "MY_SERVICE_PAYLOAD";
    private static List<Recipe> mRecipeList;
    private static DataListener dataListener;

    public MyService() {
        super("MY_SERVICE");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("ON_HANDLE", "on handle intent");
        MyWebService webService =
                MyWebService.retrofit.create(MyWebService.class);

        Call<Recipe[]> call = webService.recipesAvailable();

        Recipe[] recipes;

        try {
            recipes = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MyService", "onHandleIntent " + e.getMessage());
            dataListener.onError(new Error(e.getCause()));
            return;
        }

        mRecipeList = Arrays.asList(recipes);
        dataListener.onSuccess(mRecipeList);
    }

//    private void startService(){
//        Log.i("CalledStartService", "Called");
//        Intent intent = new Intent(this, MyService.class);
//        startService(intent);
//    }

    public void getRecipes(Context context, DataListener listener) {
        Log.i("CalledListRecipe", "Called");
        dataListener = listener;
    }
}
