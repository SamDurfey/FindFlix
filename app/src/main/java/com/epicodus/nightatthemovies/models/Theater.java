package com.epicodus.nightatthemovies.models;

public class Theater {
    private String mName;
    private String mReference;
    private String mPlaceId;

    public Theater(String name, String reference, String placeId) {
        mName = name;
        mReference = reference;
        mPlaceId = placeId;
    }

    public String getName() {
        return mName;
    }

    public String getReference() {
        return mReference;
    }

    public String getPlaceId() {
        return mPlaceId;
    }
}
