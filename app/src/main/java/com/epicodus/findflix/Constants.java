package com.epicodus.findflix;

public class Constants {

    public static final String NFROULETTE_BASE_URL = "http://netflixroulette.net/api/api.php?";
    public static final String NFROULETTE_ACTOR_QUERY_PARAMETER = "actor";
    public static final String NFROULETTE_DIRECTOR_QUERY_PARAMETER = "director";
    public static final String NETFLIX_MEDIA_BASE_URL = "https://www.netflix.com/title/";

    public static String RECENT_QUERY = "";

    // Google crap. ech.wav
    public static final String GOOGLE_PLACES_API_KEY = BuildConfig.GOOGLE_PLACES_API_KEY;
    public static final String GOOGLE_PLACES_BASE_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?type=movie_theater&radius=1000";
    public static final String GOOGLE_PLACES_LOCATION_QUERY_PARAMETER = "query";
    public static final String GOOGLE_PLACES_TEST_LOCATION = "45.520758,-122.6782925";
    public static final String GOOGLE_PLACES_PLACEID_QUERY_PARAMETER = "place_id";
    public static final String GOOGLE_PLACES_BASE_PHOTO_URL = "https://maps.googleapis.com/maps/api/place/photo?maxheight=100&photoreference=";
    public static final String GOOGLE_PLACES_PHOTO_REFERENCE_QUERY_PARAMETER = "photo_reference";

    public static final String PREFERENCES_USER_KEY = "user";
    public static final String PREFERENCES_LOCATION_KEY = "location";

    public static final String FIREBASE_CHILD_USER = "user";
    public static final String FIREBASE_CHILD_SHOWS = "shows";

}