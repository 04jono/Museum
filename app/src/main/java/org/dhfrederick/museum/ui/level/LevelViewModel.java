package org.dhfrederick.museum.ui.level;

import androidx.lifecycle.ViewModel;

import org.dhfrederick.museum.R;

public class LevelViewModel extends ViewModel {

    //Content
    int[] images = {R.drawable.wethepeople, /*R.drawable.exhibit2*/};
    String[] titles = {"We the People", /*"Walking Tour"*/};

    public LevelViewModel() {
    }

    public int[] getImages() {return images;}
    public String[] getTitles() {return titles;}
}
