package com.example.caiosanchez.reactivelocation.utils;

import android.widget.TextView;

import rx.functions.Action1;

/**
 * Created by Caio Sanchez on 24/11/2015.
 */
public class DisplayTextOnViewAction implements Action1<String> {
    private final TextView target;

    public DisplayTextOnViewAction(TextView target) {
        this.target = target;
    }

    @Override
    public void call(String s) {
        target.setText(s);
    }
}