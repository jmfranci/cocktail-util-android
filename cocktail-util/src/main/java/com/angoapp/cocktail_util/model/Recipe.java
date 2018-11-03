package com.angoapp.cocktail_util.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.angoapp.cocktail_util.builder.CocktailQueryBuilder;

public class Recipe implements Parcelable{
    private String name;
    private String imageUrl;
    private boolean isAlcoholic;

    protected Recipe(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        isAlcoholic = Boolean.valueOf(in.readString());
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isAlcoholic(){
        return isAlcoholic;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.imageUrl);
        parcel.writeString(String.valueOf(isAlcoholic));
    }
}
