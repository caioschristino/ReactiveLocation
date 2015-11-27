package com.example.caiosanchez.reactivelocation.utils;

import android.location.Location;

import rx.functions.Func1;

/**
 * Created by Caio Sanchez on 24/11/2015.
 */
public class LocationToStringFunc implements Func1<Location, String> {
    @Override
    public String call(Location location) {
        if (location != null)
            return location.getLatitude() + " " + location.getLongitude() + " (" + location.getAccuracy() + ")";
        return "no location available";
    }
}
