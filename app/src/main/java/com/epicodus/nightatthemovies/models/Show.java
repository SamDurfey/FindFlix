package com.epicodus.nightatthemovies.models;

import com.epicodus.nightatthemovies.Constants;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Parcel
public class Show {
    private int unit;
    private String showID;
    private String showTitle;
    private String releaseYear;
    private String rating;
    private String showCast;
    private String category;
    private String director;
    private String summary;
    private String posterURL;
    private int mediaType;
    private String runtime;
    private String nfMediaURL;

    public Show() {}

    public Show(int unit, int id, String showTitle, String releaseYear, String rating, String castString, String category, String director, String summary, String posterURL, int mediaType, String runtime) {
        this.unit = unit;
        this.showID = Integer.toString(id);
        this.nfMediaURL = Constants.NETFLIX_MEDIA_BASE_URL.concat(this.showID);
        this.showTitle = showTitle;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.showCast = castString;
        this.category = category;
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

    public String getNfMediaURL() {
        return nfMediaURL;
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

    public String getShowCast() {
        return showCast;
    }

    public String getCategory() {
        return category;
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
