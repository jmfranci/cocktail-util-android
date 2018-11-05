package com.angoapp.cocktailutilexample;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.ExpandableListView;

@Deprecated
public class CustomExpandableListView extends ExpandableListView {
    Context context;
    public CustomExpandableListView(Context context) {
        super(context);
        this.context = context;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int screenWidth = (int)(metrics.widthPixels); //or whatever you need here for width of the row

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(screenWidth, MeasureSpec.AT_MOST);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(20000, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
