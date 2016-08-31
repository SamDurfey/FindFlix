package com.epicodus.findflix.util;

import com.epicodus.findflix.models.Show;

import java.util.ArrayList;

public interface OnShowSelectedListener {
    void onShowSelected(Integer position, ArrayList<Show> shows, String source);
}
