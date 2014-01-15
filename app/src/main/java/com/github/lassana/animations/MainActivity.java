package com.github.lassana.animations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.github.lassana.animations.scrolling.activity.ScrollingActivity;
import com.github.lassana.animations.sorting.activity.SortingActivity;

/**
 * @author lassana
 * @since 1/15/14
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonSorting).setOnClickListener(this);
        findViewById(R.id.buttonScrolling).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSorting:
                startActivity(new Intent(this, SortingActivity.class));
                break;
            case R.id.buttonScrolling:
                startActivity(new Intent(this, ScrollingActivity.class));
                break;
            default:
                break;
        }
    }
}
