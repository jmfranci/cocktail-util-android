package com.angoapp.cocktail_util.services;

import com.angoapp.cocktail_util.helper.Constants;
import com.angoapp.cocktail_util.model.CocktailQuery;
import com.angoapp.cocktail_util.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyWebService {

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    @GET("api/v1/recipes")
    Call<Recipe[]> getAllRecipes();

    @POST("api/v1/recipes/withTags/loose2")
    Call<Recipe[]> getRecipes(@Body CocktailQuery body);

    @GET("api/v1/ingredients/tags/all")
    Call<String[]> getAllTags();


}
