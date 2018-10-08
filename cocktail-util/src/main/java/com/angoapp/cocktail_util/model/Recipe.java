package com.angoapp.cocktail_util.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.angoapp.cocktail_util.builder.CocktailQueryBuilder;

public class Recipe implements Parcelable{
    private String name;

    protected Recipe(Parcel in) {
        name = in.readString();
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
    }
}
