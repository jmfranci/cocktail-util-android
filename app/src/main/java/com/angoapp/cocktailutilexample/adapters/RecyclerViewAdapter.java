package com.angoapp.cocktailutilexample.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.angoapp.cocktail_util.model.Recipe;
import com.angoapp.cocktailutilexample.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private List<Recipe> recipes;
    private Context mContext;

    public RecyclerViewAdapter(List<Recipe> recipes, Context mContext) {
        this.recipes = recipes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        Glide.with(mContext)
                .asBitmap()
                .load(recipes.get(i).getImageUrl())
                .into(viewHolder.image);

        viewHolder.cocktailName.setText(recipes.get(i).getName());

        if (recipes.get(i).isAlcoholic()){
            viewHolder.isAlcoholic.setText(" Alcoholic ");
            viewHolder.isAlcoholic.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
        }else{
            viewHolder.isAlcoholic.setText(" Non Alcoholic ");
            viewHolder.isAlcoholic.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        }

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicked on: " + recipes.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView cocktailName;
        TextView isAlcoholic;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            cocktailName = itemView.findViewById(R.id.cocktail_name);
            isAlcoholic = itemView.findViewById(R.id.is_alcoholic);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
