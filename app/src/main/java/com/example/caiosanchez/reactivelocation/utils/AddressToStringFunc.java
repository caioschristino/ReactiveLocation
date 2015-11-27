package com.example.caiosanchez.reactivelocation.utils;

import android.location.Address;

import rx.functions.Func1;

/**
 * Created by Caio Sanchez on 24/11/2015.
 */
public class AddressToStringFunc implements Func1<Address, String> {
    @Override
    public String call(Address address) {
        if (address == null) return "";

        String addressLines = "";
        for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
            addressLines += address.getAddressLine(i) + '\n';
        }
        return addressLines;
    }
}