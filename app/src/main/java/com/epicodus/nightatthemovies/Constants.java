package com.epicodus.nightatthemovies;

public class Constants {
    public static final String GOOGLE_PLACES_API_KEY = BuildConfig.GOOGLE_PLACES_API_KEY;
    public static final String GOOGLE_PLACES_BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    public static final String GOOGLE_PLACES_TEST_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=45.520758,-122.6782925&radius=1000&type=movie_theater&key=" + GOOGLE_PLACES_API_KEY;
    public static final String GOOGLE_PLACES_LOCATION_QUERY_PARAMETER = "location";
    public static final String GOOGLE_PLACES_BASE_PHOTO_URL = "https://maps.googleapis.com/maps/api/place/photo?maxheight=100&photoreference=";

}
