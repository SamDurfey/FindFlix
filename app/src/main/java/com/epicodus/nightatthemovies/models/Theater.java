package com.epicodus.nightatthemovies.models;

import com.epicodus.nightatthemovies.Constants;

public class Theater {
    private String mName;
    private String mPlaceId;
    private double mLatitude;
    private double mLongitude;
    private String mLatLng;
    private String mImageUrl;

    public Theater(String name, String placeId, double latitude, double longitude, String imageUrl) {
        mName = name;
        mPlaceId = placeId;
        mLatitude = latitude;
        mLongitude = longitude;
        mLatLng = Double.toString(latitude) + "," + Double.toString(longitude);
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
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

    public String getImageUrl() {
        return mImageUrl;
    }
}
