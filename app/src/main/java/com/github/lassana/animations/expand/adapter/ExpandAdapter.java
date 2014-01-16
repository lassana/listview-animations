package com.github.lassana.animations.expand.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.lassana.animations.R;
import com.github.lassana.animations.base.AnimatorHelper;
import com.github.lassana.animations.expand.animator.ExpandAnimation;
import com.github.lassana.animations.expand.model.ListItemData;

import java.util.List;

/**
 * @author Nikolai Doronin
 * @since 1/16/14
 */
public abstract class ExpandAdapter extends ArrayAdapter<ListItemData> {

    protected Activity mActivity;
    private LayoutInflater mLayoutInflater;

    public ExpandAdapter(Activity activity, List<ListItemData> dataset) {
        super(activity, R.layout.list_item_expand, dataset);
        mActivity = activity;
        mLayoutInflater = LayoutInflater.from(mActivity);
    }

    class ViewHolder {
        TextView title;
        TextView description;
        ViewGroup contentLayout;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = convertView == null ? null : (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_expand, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(android.R.id.text1);
            viewHolder.description = (TextView) convertView.findViewById(android.R.id.text2);
            viewHolder.contentLayout = (ViewGroup) convertView.findViewById(android.R.id.content);
            convertView.setTag(viewHolder);
        }
        ListItemData item = getItem(position);
        viewHolder.title.setText(item.getTitle());
        viewHolder.description.setText(R.string.tv_description);
        viewHolder.contentLayout.setVisibility(item.isExpanded() ? View.VISIBLE : View.GONE);
        final ViewGroup content = viewHolder.contentLayout;
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

    protected void expandView(ViewGroup content, boolean isExpand) {
        AnimationSet set = new AnimationSet(true);

        AnimatorHelper.setHeightForWrapContent(mActivity, content);
        ExpandAnimation sizeAnimation = new ExpandAnimation(content, isExpand);
        set.addAnimation(sizeAnimation);

        Animation[] animations = getAdditionalAnimations(content, isExpand);
        if ( animations != null ) {
            for ( Animation animation : animations) {
                set.addAnimation(animation);
            }
        }

        set.setDuration(AnimatorHelper.DURATION_MEDIUM);
        content.startAnimation(set);
    }

    protected abstract Animation[] getAdditionalAnimations(ViewGroup content,
                                                           boolean isExpand);

}
