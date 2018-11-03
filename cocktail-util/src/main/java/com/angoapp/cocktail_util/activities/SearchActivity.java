package com.angoapp.cocktail_util.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.angoapp.cocktail_util.R;
import com.angoapp.cocktail_util.builder.IngredientQueryBuilder;
import com.angoapp.cocktail_util.listener.TagsDataListener;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Random;

public class SearchActivity extends AppCompatActivity {

    private FlexboxLayout flexboxLayout;
    private AutoCompleteTextView editText;
    private Button searchButton;

    private final ArrayList<String> allTags = new ArrayList<>();
    private final ArrayList<String> selectedTags = new ArrayList<>();
    private final ArrayList<TextView> textViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();
        initOnClickListeners();
        getTagsFromDb();

    }

    private void initOnClickListeners() {
        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final TextView textView = new TextView(SearchActivity.this);
                textView.setText(adapterView.getItemAtPosition(i).toString());
                textView.setTextSize(20);
                textView.setBackgroundColor(generateRandomColor());
                textView.setId(textViews.size());

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flexboxLayout.removeView(textView);
                    }
                });

                textView.setTextColor(Color.argb(255,255,255,255));
                editText.setText("");

                FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(5,2,5,2);
                flexboxLayout.addView(textView, params);
                textViews.add(textView);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void getTagsFromDb() {
        final ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this, android.R.layout.simple_list_item_1, allTags);
        editText.setAdapter(adapter);

        //Get tags from DB
        new IngredientQueryBuilder().getAllIngredientTags().build(this, new TagsDataListener() {
            @Override
            public void onSuccess(String[] tags) {
                Log.i("__TAGS__", "tags from search activity");
                for (String str : tags){
                    Log.i("_NEW_TAG_", str);
                    allTags.add(str);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Error e) {
                Toast.makeText(SearchActivity.this, "Couldn't get tags", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int generateRandomColor() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }

    private void initViews() {
        editText = findViewById(R.id.actv);
        searchButton = findViewById(R.id.btn_search);
        flexboxLayout = findViewById(R.id.linear_layout);
        flexboxLayout.setFlexDirection(FlexDirection.ROW);
    }


}
