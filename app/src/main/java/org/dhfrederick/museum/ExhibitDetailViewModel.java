package org.dhfrederick.museum;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.dhfrederick.museum.R;

public class ExhibitDetailViewModel extends ViewModel {

    public int getExhibitId() {
        return exhibitId;
    }

    public void setExhibitId(int exhibitId) {
        exhibitId = exhibitId;
    }

    private int exhibitId;
    private int[] images = {R.drawable.exhibit1, R.drawable.exhibit2, R.drawable.exhibit3, R.drawable.exhibit4};
    private int[] audios = {R.raw.cubism_sample, R.raw.impressionism_sample, R.raw.cubism_sample, R.raw.impressionism_sample};

    public ExhibitDetailViewModel()
    {
        exhibitId = 1;
    }

    public ExhibitDetailViewModel(int pos)
    {
        exhibitId = pos;
    }

    public int[] getImages()
    {
        return images;
    }
    public int[] getAudios()
    {
        return audios;
    }


}
