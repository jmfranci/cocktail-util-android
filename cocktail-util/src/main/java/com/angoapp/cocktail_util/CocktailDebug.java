package com.angoapp.cocktail_util;

import android.util.Log;

public class CocktailDebug {
    public final String TAG = "Cocktail Library";

    public void d(String msg){
        Log.d(TAG, msg);
    }
}
