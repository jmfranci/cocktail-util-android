package com.angoapp.cocktail_util.services;

import com.angoapp.cocktail_util.helper.Constants;
import com.angoapp.cocktail_util.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MyWebService {

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    @GET("api/v1/recipes")
    Call<Recipe[]> recipesAvailable();
}
