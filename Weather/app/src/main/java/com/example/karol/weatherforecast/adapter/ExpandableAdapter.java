package com.example.karol.weatherforecast.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.karol.weatherforecast.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Karol on 26.02.2018.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private List<String> listData;
    private Context context;
    private HashMap<String,List<String>>listHashMap;

    public ExpandableAdapter(List<String> listData, Context context, HashMap<String, List<String>> listHashMap) {
        this.listData = listData;
        this.context = context;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listData.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listData.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String)getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.list_group,null);
        }
        TextView listBorderView = (TextView)convertView.findViewById(R.id.ListBorder);
        listBorderView.setTypeface(null, Typeface.BOLD);
        listBorderView.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childTxt = (String)getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.list_item,null);
        }
        TextView listItemView = (TextView)convertView.findViewById(R.id.list_itemV);
        listItemView.setText(childTxt);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
