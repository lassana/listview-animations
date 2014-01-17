package com.github.lassana.animations.base;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lassana
 * @since 1/16/14
 */
public abstract class DatasetBuilder {

    private final static String[] CARS = {
            "Volvo", "Mercedes", "Audi", "Land Rover", "BMW", "Ford", "GMC", "Mazda", "Acura",
            "Vaz", "Renault", "DeLorean", "Alfa Romeo", "Toyota", "Saab", "Ferrari", "Tesla"
    };


    private DatasetBuilder() {
    }

    public static List<String> build() {
        return Arrays.asList(CARS);
    }

    public static List<String> buildLarge() {
        return buildLarge(50);
    }


    public static List<String> buildLarge(int size) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < size-1; i++) Collections.addAll(arrayList, CARS);
        Collections.shuffle(arrayList);
        return arrayList;
    }


}
