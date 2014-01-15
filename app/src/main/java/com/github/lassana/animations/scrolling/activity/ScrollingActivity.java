package com.github.lassana.animations.scrolling.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.github.lassana.animations.R;
import com.github.lassana.animations.scrolling.fragment.ScrollingFragment;

public class ScrollingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ScrollingFragment())
                    .commit();
        }
    }

}
