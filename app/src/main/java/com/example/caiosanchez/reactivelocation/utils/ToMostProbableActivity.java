package com.example.caiosanchez.reactivelocation.utils;


import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import rx.functions.Func1;
/**
 * Created by Caio Sanchez on 24/11/2015.
 */
public class ToMostProbableActivity implements Func1<ActivityRecognitionResult, DetectedActivity> {
    @Override
    public DetectedActivity call(ActivityRecognitionResult activityRecognitionResult) {
        return activityRecognitionResult.getMostProbableActivity();
    }
}
