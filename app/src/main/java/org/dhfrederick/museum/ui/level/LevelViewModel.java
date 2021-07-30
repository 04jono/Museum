package org.dhfrederick.museum.ui.level;

import androidx.lifecycle.ViewModel;

import org.dhfrederick.museum.R;

public class LevelViewModel extends ViewModel {
    // private MutableLiveData<String> mText;

    int[] images = {R.drawable.exhibit1, R.drawable.exhibit2, R.drawable.exhibit3, R.drawable.exhibit4};

    String[] titles = {"Level1", "Level2", "Level3", "Level4"};

    public LevelViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is dashboard fragment");
    }

    /* public LiveData<String> getText() {
        return mText;
    } */

    public int[] getImages() {return images;}
    public String[] getTitles() {return titles;}
}
