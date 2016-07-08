package com.epicodus.nightatthemovies.models;

public class Theater {
    private String mName;
    private String mReference;
    private String mPlaceId;
    private double mLatitude;
    private double mLongitude;
    private String mLatLng;

    public Theater(String name, String reference, String placeId, double latitude, double longitude) {
        mName = name;
        mReference = reference;
        mPlaceId = placeId;
        mLatitude = latitude;
        mLongitude = longitude;
        mLatLng = Double.toString(latitude) + "," + Double.toString(longitude);
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

    public double getLongitude() {
        return mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public String getLatLng() {
        return mLatLng;
    }
}
