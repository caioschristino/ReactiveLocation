package com.example.caiosanchez.reactivelocation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by Caio Sanchez on 24/11/2015.
 */
public class PlacesResultActivity extends ActionBarActivity {

    private static final String EXTRA_PLACE_ID = "EXTRA_PLACE_ID";

    private ReactiveLocationProvider reactiveLocationProvider;
    private CompositeSubscription compositeSubscription;
    private TextView placeNameView;
    private TextView placeLocationView;
    private TextView placeAddressView;
    private String placeId;

    public static Intent getStartIntent(Context context, @NonNull String placeId) {
        Intent startIntent = new Intent(context, PlacesResultActivity.class);
        startIntent.putExtra(EXTRA_PLACE_ID, placeId);

        return startIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_result);

        placeNameView = (TextView) findViewById(R.id.place_name_view);
        placeLocationView = (TextView) findViewById(R.id.place_location_view);
        placeAddressView = (TextView) findViewById(R.id.place_address_view);

        reactiveLocationProvider = new ReactiveLocationProvider(this);

        getPlaceIdFromIntent();
    }

    private void getPlaceIdFromIntent() {
        Intent loadedIntent = getIntent();
        placeId = loadedIntent.getStringExtra(EXTRA_PLACE_ID);

        if (placeId == null) {
            throw new IllegalStateException("You must start SearchResultsActivity with a non-null place Id using getStartIntent(Context, String)");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(reactiveLocationProvider.getPlaceById(placeId)
                .subscribe(new Action1<PlaceBuffer>() {
                    @Override
                    public void call(PlaceBuffer buffer) {
                        Place place = buffer.get(0);
                        if (place != null) {
                            placeNameView.setText(place.getName());
                            placeLocationView.setText(place.getLatLng().latitude + ", " + place.getLatLng().longitude);
                            placeAddressView.setText(place.getAddress());
                        }
                        buffer.release();
                    }
                }));
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeSubscription.unsubscribe();
        compositeSubscription = null;
    }
}

