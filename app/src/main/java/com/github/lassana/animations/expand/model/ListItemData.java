package com.github.lassana.animations.expand.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolai Doronin
 * @since 1/16/14
 */
public class ListItemData {

    private boolean mExpanded;
    private String title;

    public ListItemData(String title, boolean expanded) {
        mExpanded = expanded;
        this.title = title;
    }

    public boolean isExpanded() {
        return mExpanded;
    }

    public void setExpanded(boolean expanded) {
        mExpanded = expanded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static List<ListItemData> build(List<String> list) {
        List<ListItemData> rvalue = new ArrayList<>(list.size());
        for (String string : list) {
            rvalue.add(new ListItemData(string, false));
        }
        return rvalue;
    }

}
