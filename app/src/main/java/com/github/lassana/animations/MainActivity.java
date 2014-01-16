package com.github.lassana.animations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lassana
 * @since 1/15/14
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private List<Pair<Integer, Integer>> mButtonsList;

    {
        mButtonsList = new ArrayList<>();
        mButtonsList.add(new Pair<>(ListFragmentActivity.FRAGMENT_LIST_SORTING, R.string.sorting));
        mButtonsList.add(new Pair<>(ListFragmentActivity.FRAGMENT_LIST_SCROLLING, R.string.scrolling));
        mButtonsList.add(new Pair<>(ListFragmentActivity.FRAGMENT_EXPAND_LIST_ITEM, R.string.expand));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = (LinearLayout) findViewById(android.R.id.primary);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        for (Pair<Integer, Integer> pair : mButtonsList) {
            Button button = new Button(this);
            button.setLayoutParams(params);
            button.setTag(pair.first);
            button.setText(pair.second);
            button.setOnClickListener(this);
            layout.addView(button);
        }
    }

    @Override
    public void onClick(View v) {
        int fragmentId = (int) v.getTag();
        startActivity(new Intent(this, ListFragmentActivity.class)
                .putExtra(ListFragmentActivity.EXTRA_FRAGMENT_ID, fragmentId));
    }
}
