package com.angoapp.cocktail_util.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.angoapp.cocktail_util.builder.CocktailQueryBuilder;

import java.util.ArrayList;

public class Recipe implements Parcelable{
    private String id;
    private String name;
    private String imageUrl;
    private boolean isAlcoholic;
    private ArrayList<String> instructions;
    private String glassType;



    protected Recipe(Parcel in) {
        id = in.readString();
        name = in.readString();
        imageUrl = in.readString();
        isAlcoholic = Boolean.valueOf(in.readString());
        instructions = in.createStringArrayList();
        glassType = in.readString();
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

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public String getId() {
        return id;
    }

    public String getGlassType() {
        return glassType;
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
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.imageUrl);
        parcel.writeString(String.valueOf(this.isAlcoholic));
        parcel.writeStringList(this.instructions);
        parcel.writeString(this.glassType);
    }
}
