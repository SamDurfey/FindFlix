package com.epicodus.findflix.services;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.epicodus.findflix.Constants;
import com.epicodus.findflix.models.Show;

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

    public void findShows(String actor, String director, String title, Context context, Callback callback) {

        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();

        OkHttpClient mClient = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NFROULETTE_BASE_URL).newBuilder();
        if (!actor.equals("")) {
            urlBuilder.addQueryParameter(Constants.NFROULETTE_ACTOR_QUERY_PARAMETER, actor);
        }
        if (!director.equals("")) {
            urlBuilder.addQueryParameter(Constants.NFROULETTE_DIRECTOR_QUERY_PARAMETER, director);
        }
//        if (!year.equals("")) {
//            urlBuilder.addQueryParameter(Constants.NFROULETTE_YEAR_QUERY_PARAMETER, year);
//        }
        if (!title.equals("")) {
            urlBuilder.addQueryParameter(Constants.NFROULETTE_TITLE_QUERY_PARAMETER, title);
        }

        String url = urlBuilder.build().toString();
        Log.d("Built URL: ", url);

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
            if (jsonData.startsWith("{")) {
                jsonData = "[" + jsonData + "]";
            }
            JSONArray nfJSON = new JSONArray(jsonData);
            for (int i = 0; i < nfJSON.length(); i++) {
                JSONObject mediaJSON = nfJSON.getJSONObject(i);

                int unit = mediaJSON.getInt("unit");
                int id = mediaJSON.getInt("show_id");
                String showTitle = mediaJSON.getString("show_title");
                String releaseYear = mediaJSON.getString("release_year");
                String rating = mediaJSON.getString("rating");
                String castString = mediaJSON.getString("show_cast");
                String category = mediaJSON.getString("category");
                String director = mediaJSON.getString("director");
                String summary = mediaJSON.getString("summary");
                String posterURL = mediaJSON.getString("poster");
                int mediaType = mediaJSON.getInt("mediatype");
                String runtime = mediaJSON.getString("runtime");

                Show show = new Show(unit, id, showTitle, releaseYear, rating, castString, category, director, summary, posterURL, mediaType, runtime);
                shows.add(show);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return shows;

    }
}
