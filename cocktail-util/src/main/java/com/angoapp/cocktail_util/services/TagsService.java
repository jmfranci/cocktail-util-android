package com.angoapp.cocktail_util.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.angoapp.cocktail_util.listener.TagsDataListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;

public class TagsService extends IntentService {

    private List<String> mTagsList;
    private static TagsDataListener dataListener;

    public TagsService() {
        super("MY_TAG_SERVICE");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("HANDLE", "Handler Called");
        MyWebService webService =
                MyWebService.retrofit.create(MyWebService.class);

        Call<String[]> call;
        call = webService.getAllTags();

        String[] tags;

        try {
            tags = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MyService", "onHandleIntent " + e.getMessage());
            dataListener.onError(new Error(e.getCause()));
            return;
        }

        try {
            //mTagsList = Arrays.asList(tags);
            dataListener.onSuccess(tags);
        }catch (NullPointerException e){
            dataListener.onError(new Error("Null pointer exception occurred", e.getCause()));
        }
    }

    public void getTags(TagsDataListener listener){
        this.dataListener = listener;
    }
}
