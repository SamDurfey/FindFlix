package com.epicodus.nightatthemovies;

public class Constants {
    public static final String GOOGLE_PLACES_API_KEY = BuildConfig.GOOGLE_PLACES_API_KEY;
    public static final String GOOGLE_PLACES_BASE_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?type=movie_theater&radius=1000";
    public static final String GOOGLE_PLACES_LOCATION_QUERY_PARAMETER = "query";
    public static final String GOOGLE_PLACES_TEST_LOCATION = "45.520758,-122.6782925";
    public static final String GOOGLE_PLACES_PLACEID_QUERY_PARAMETER = "place_id";
    public static final String GOOGLE_PLACES_BASE_PHOTO_URL = "https://maps.googleapis.com/maps/api/place/photo?maxheight=100&photoreference=";
    public static final String GOOGLE_PLACES_PHOTO_REFERENCE_QUERY_PARAMETER = "photo_reference";

    public static final String PREFERENCES_USER_KEY = "user";
    public static final String PREFERENCES_LOCATION_KEY = "location";

}
