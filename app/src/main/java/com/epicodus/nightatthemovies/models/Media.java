package com.epicodus.nightatthemovies.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Media {
    private int unit;
    private String showID;
    private String showTitle;
    private String releaseYear;
    private String rating;
    private String category;
    private String[] showCast;
    private String director;
    private String summary;
    private String posterURL;
    private int mediaType;
    private String runtime;

    public Media(int unit, int id, String showTitle, String releaseYear, String rating, String category, String castString, String director, String summary, String posterURL, int mediaType, String runtime) {
        this.unit = unit;
        this.showID = Integer.toString(id);
        this.showTitle = showTitle;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.category = category;
        this.showCast = castString.split("\\s*,\\s*"); //should split given string into an ArrayList
        this.director = director;
        this.summary = summary;
        this.posterURL = posterURL;
        this.mediaType = mediaType;
        this.runtime = runtime;
    }

    public int getUnit() {
        return unit;
    }

    public String getShowID() {
        return showID;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public String[] getShowCast() {
        return showCast;
    }

    public String getDirector() {
        return director;
    }

    public String getSummary() {
        return summary;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public int getMediaType() {
        return mediaType;
    }

    public String getRuntime() {
        return runtime;
    }
}
