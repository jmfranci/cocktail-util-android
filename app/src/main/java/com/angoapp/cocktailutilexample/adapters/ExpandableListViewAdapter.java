package com.angoapp.cocktailutilexample.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.angoapp.cocktailutilexample.R;
import com.angoapp.cocktailutilexample.activities.CocktailDetailsActivity;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<CocktailDetailsActivity.Header> listDataHeader;
    private HashMap<String, List<String>> listHashMap;

    public ExpandableListViewAdapter(Context mContext, List<CocktailDetailsActivity.Header> listDataHeader, HashMap<String, List<String>> listHashMap) {
        this.mContext = mContext;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i).getHeader()).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i).getHeader();
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(listDataHeader.get(i).getHeader()).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group,null);
        }

        ImageView imageView = view.findViewById(R.id.ic_section);
        TextView textView = view.findViewById(R.id.lbl_title);

        imageView.setBackground(view.getResources().getDrawable(listDataHeader.get(i).getIcon()));
        textView.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String childText = (String)getChild(i,i1);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandable_lv_item,null);
        }

        TextView textView = (TextView) view.findViewById(R.id.item);
        textView.setText(childText);

        if(i1%2!=0){
            view.setBackgroundColor(mContext.getResources().getColor(R.color.gray2));
        }else{
            view.setBackgroundColor(mContext.getResources().getColor(R.color.gray1));
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


}
