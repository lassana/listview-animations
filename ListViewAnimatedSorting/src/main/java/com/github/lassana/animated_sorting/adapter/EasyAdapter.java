package com.github.lassana.animated_sorting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author lassana
 * @since 10/9/13
 */
public class EasyAdapter extends BaseAdapter {

    private ArrayList<String> mDataSet;
    private Context mContext;

    public EasyAdapter(ArrayList<String> dataSet, Context context) {
        this.mDataSet = dataSet;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rvalue = convertView;
        if( rvalue == null ) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            rvalue = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView tv = (TextView) rvalue.findViewById(android.R.id.text1);
        tv.setText(mDataSet.get(position));
        return rvalue;
    }

    public void shuffle() {
        Collections.shuffle(mDataSet);
    }
}
