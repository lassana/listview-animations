package com.github.lassana.animations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.View;

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
        mButtonsList.add(new Pair<>(R.id.buttonSorting, ListFragmentActivity.FRAGMENT_LIST_SORTING));
        mButtonsList.add(new Pair<>(R.id.buttonScrolling, ListFragmentActivity.FRAGMENT_LIST_SCROLLING));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (Pair<Integer, Integer> pair : mButtonsList) {
            View view = findViewById(pair.first);
            view.setTag(pair.second);
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int fragmentId = (int) v.getTag();
        startActivity(new Intent(this, ListFragmentActivity.class)
                .putExtra(ListFragmentActivity.EXTRA_FRAGMENT_ID, fragmentId));
    }
}
