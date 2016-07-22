package com.epicodus.nightatthemovies.services;

//import com.net.codeusa.NetflixRoulette;

import com.epicodus.nightatthemovies.Constants;
import com.epicodus.nightatthemovies.models.Show;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RouletteService {

    public void findShows(String actor, String genre, String director, Callback callback) {

        OkHttpClient mClient = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NFROULETTE_BASE_URL).newBuilder();
        if (actor != "") {
            urlBuilder.addQueryParameter(Constants.NFROULETTE_ACTOR_QUERY_PARAMETER, actor);
        }
//        if (genre != "") {
//            urlBuilder.addQueryParameter(Constants.NFROULETTE_GENRE_QUERY_PARAMETER, genre);
//        }
        if (director != "") {
            urlBuilder.addQueryParameter(Constants.NFROULETTE_DIRECTOR_QUERY_PARAMETER, director);
        }

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Show> processResponse(Response response) {
        ArrayList<Show> shows = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONArray nfJSON = new JSONArray(jsonData);
            for (int i = 0; i < nfJSON.length(); i++) {
                JSONObject mediaJSON = nfJSON.getJSONObject(i);

                int unit = mediaJSON.getInt("unit");
                int id = mediaJSON.getInt("show_id");
                String showTitle = mediaJSON.getString("show_title");
                String releaseYear = mediaJSON.getString("release_year");
                String rating = mediaJSON.getString("rating");
                String category = mediaJSON.getString("category");
                String castString = mediaJSON.getString("show_cast");
                String director = mediaJSON.getString("director");
                String summary = mediaJSON.getString("summary");
                String posterURL = mediaJSON.getString("poster");
                int mediaType = mediaJSON.getInt("mediatype");
                String runtime = mediaJSON.getString("runtime");

                Show show = new Show(unit, id, showTitle, releaseYear, rating, category, castString, director, summary, posterURL, mediaType, runtime);
                shows.add(show);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return shows;

    }



//    private NetflixRoulette mRoulette;
//    private String mActors;
//    private String mDirector;
//    private String mGenre;
//
//    public RouletteService(String actors, String director, String genre) {
//        mRoulette = new NetflixRoulette();
//        mActors = actors;
//        mDirector = director;
//        mGenre = genre;
//    }
//
//    public NetflixRoulette getRoulette() {
//        return mRoulette;
//    }
//
//    public String getActors() {
//        return mActors;
//    }
//
//    public String getDirector() {
//        return mDirector;
//    }
//
//    public String getGenre() {
//        return mGenre;
//    }


}
