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

    private static final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Recipe[] recipes = (Recipe[]) intent
                    .getParcelableArrayExtra(MyService.MY_SERVICE_PAYLOAD);
            if (recipes.length != 0) {
                Toast.makeText(context, "Received " + recipes.length + "items from service", Toast.LENGTH_SHORT).show();
                mRecipeList = Arrays.asList(recipes);

                dataListener.onSuccess(mRecipeList);
            }else{
                dataListener.onError(new Error("API Internal Error"));
            }
        }
    };

    public MyService() {
        super("MY_SERVICE");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        MyWebService webService =
                MyWebService.retrofit.create(MyWebService.class);

        Call<Recipe[]> call = webService.recipesAvailable();

        Recipe[] recipes;

        try {
            recipes = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MyService", "onHandleIntent " + e.getMessage());
            return;
        }

        Intent messageIntent = new Intent(MY_SERVICE_MESSAGE);
        messageIntent.putExtra(MY_SERVICE_PAYLOAD, recipes);
        LocalBroadcastManager manager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    private void startService(){

    }
    public static List<Recipe> getRecipes(Context context, DataListener listener) {
        dataListener = listener;
        LocalBroadcastManager.getInstance(context)
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(MyService.MY_SERVICE_MESSAGE));

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
    }

    public interface DataListener {
        public void onSuccess(List<Recipe> recipes);
        public void onError(Error e);
    }
}
