package com.github.lassana.animations.expand.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.lassana.animations.R;
import com.github.lassana.animations.base.AnimatorHelper;
import com.github.lassana.animations.base.SkewingRelativeLayout;
import com.github.lassana.animations.expand.animator.ExpandAnimation;
import com.github.lassana.animations.expand.animator.SkewAnimation;
import com.github.lassana.animations.expand.model.ListItemData;

import java.util.List;

/**
 * @author Nikolai Doronin
 * @since 1/16/14
 */
public class ExpandAdapter extends ArrayAdapter<ListItemData> {

    private Activity mActivity;
    private LayoutInflater mLayoutInflater;

    public ExpandAdapter(Activity activity, List<ListItemData> dataset) {
        super(activity, R.layout.list_item_expand, dataset);
        mActivity = activity;
        mLayoutInflater = LayoutInflater.from(mActivity);
    }

    class ViewHolder {
        TextView title;
        SkewingRelativeLayout contentLayout;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = convertView == null ? null : (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_expand, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(android.R.id.text1);
            viewHolder.contentLayout = (SkewingRelativeLayout) convertView.findViewById(android.R.id.content);
            convertView.setTag(viewHolder);
        }
        ListItemData item = getItem(position);
        viewHolder.title.setText(item.getTitle());
        viewHolder.contentLayout.setVisibility(item.isExpanded() ? View.VISIBLE : View.GONE);
        final SkewingRelativeLayout content = viewHolder.contentLayout;
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExpand = content.getVisibility() == View.GONE;
                getItem(position).setExpanded(isExpand);
                expandView(content, isExpand);
            }
        });
        return convertView;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void expandView(SkewingRelativeLayout content, boolean isExpand) {
        AnimatorHelper.setHeightForWrapContent(mActivity, content);
        AnimationSet set = new AnimationSet(true);
        ExpandAnimation sizeAnimation = new ExpandAnimation(content, isExpand);
        set.addAnimation(sizeAnimation);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            SkewAnimation skewAnimation;
            if (isExpand) {
                content.setSkewX(-1.0f);
                content.setSkewY(-1.0f);
                skewAnimation = new SkewAnimation(content, 0f, 0f);
            } else {
                content.setSkewX(0f);
                content.setSkewY(0f);
                skewAnimation = new SkewAnimation(content, -1.0f, -1.0f);
            }
            set.addAnimation(skewAnimation);
        }
        set.setDuration(AnimatorHelper.DURATION_MEDIUM);
        content.startAnimation(set);
    }
}
