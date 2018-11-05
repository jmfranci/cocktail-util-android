package com.angoapp.cocktailutilexample.activities;

import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.angoapp.cocktail_util.model.Recipe;
import com.angoapp.cocktailutilexample.R;
import com.angoapp.cocktailutilexample.adapters.ExpandableListViewAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CocktailDetailsActivity extends AppCompatActivity {

    private static final String TAG = "CocktailDetailsActivity";
    private ImageView imageView;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<Header> listDataHeader = new ArrayList<>();
    private HashMap<String, List<String>> listHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_details);
        initViews();

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_tb);



        Recipe recipe = getIntent().getExtras().getParcelable("recipe");

        collapsingToolbar.setTitle(recipe.getName());

        Glide.with(this)
                .asBitmap()
                .load(recipe.getImageUrl())
                .into(imageView);

        ArrayList<String> list = new ArrayList<>();
        list.add("1/2 oz Coconut Cream");
        list.add("1 ml Carbonated Water");
        list.add("2 shots Sweet Vermouth");
        list.add("2 inch Caramelized Ice Cream");

        Header header2 = new Header("Instructions", R.drawable.ic_pouring);
        Header header1 = new Header("Ingredients",R.drawable.ic_bottles);

        listDataHeader.add(header1);
        listDataHeader.add(header2);

        listHashMap.put(listDataHeader.get(0).getHeader(), list);
        listHashMap.put(listDataHeader.get(1).getHeader(), recipe.getInstructions());


        for (int i = 0; i < recipe.getInstructions().size(); i++) {
            Log.d(TAG, " " + recipe.getInstructions().get(i));
        }

        expandableListView = findViewById(R.id.expandable_lv);
        expandableListView.setGroupIndicator(null);
        expandableListView.setChildIndicator(null);
        expandableListView.setChildDivider(getResources().getDrawable(R.color.gray1));
        expandableListView.setDivider(getResources().getDrawable(R.color.white));
        expandableListView.setDividerHeight(2);

        adapter = new ExpandableListViewAdapter(this,listDataHeader,listHashMap);
        expandableListView.setAdapter(adapter);
    }

    private void initViews(){
        imageView = findViewById(R.id.image_view);

    }

    public class Header{
        String header;
        int icon;

        public Header(String header, int icon) {
            this.header = header;
            this.icon = icon;
        }

        public String getHeader() {
            return header;
        }

        public int getIcon() {
            return icon;
        }
    }
}
