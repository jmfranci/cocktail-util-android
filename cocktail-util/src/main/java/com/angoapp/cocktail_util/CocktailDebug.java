package com.angoapp.cocktail_util;

import android.util.Log;

public class CocktailDebug {
    public static final String TAG = "Cocktail Library";

    public static void d(String msg){
        Log.d(TAG, msg);
    }
}
