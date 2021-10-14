package org.dhfrederick.museum;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ExhibitDetailViewModel extends ViewModel {

    public int getExhibitId() {
        return exhibitId;
    }

    public void setExhibitId(int exhibitId) {
        exhibitId = exhibitId;
    }

    private int exhibitId;
    private int[] images1 = {R.drawable.firstinhabitants, R.drawable.establishment, R.drawable.coloniallife, R.drawable.revolutionarywar, R.drawable.craftspeople};
    private int[] images2 = {R.drawable.exhibit4, R.drawable.exhibit3, R.drawable.exhibit2, R.drawable.exhibit1};
    private ArrayList<int[]> imagelist = new ArrayList<int[]>();
    private int[] audios1 = {R.raw.cubism_sample, R.raw.impressionism_sample, R.raw.cubism_sample, R.raw.impressionism_sample, R.raw.cubism_sample};
    private int[] audios2 = {R.raw.cubism_sample, R.raw.impressionism_sample, R.raw.cubism_sample, R.raw.impressionism_sample};
    private ArrayList<int[]> audiolist = new ArrayList<int[]>();
    public ExhibitDetailViewModel()
    {
        exhibitId = 1;
        imagelist.add(images1);
        imagelist.add(images2);
        audiolist.add(audios1);
        audiolist.add(audios2);
    }

    public ExhibitDetailViewModel(int pos)
    {
        exhibitId = pos;
    }

    public ArrayList<int[]> getImages()
    {
        return imagelist;
    }
    public ArrayList<int[]> getAudios()
    {
        return audiolist;
    }


}
