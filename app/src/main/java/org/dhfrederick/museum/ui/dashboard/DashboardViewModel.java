package org.dhfrederick.museum.ui.dashboard;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.dhfrederick.museum.R;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {

    // private MutableLiveData<String> mText;
    private int dashboardId;

    private int[] images1 = {R.drawable.firstinhabitants, R.drawable.establishment, R.drawable.coloniallife, R.drawable.revolutionarywar, R.drawable.craftspeople, R.drawable.southbentz};
    private int[] images2 = {R.drawable.exhibit4, R.drawable.exhibit3, R.drawable.exhibit2, R.drawable.exhibit1};
    private ArrayList<int[]> imagelist = new ArrayList<int[]>();




    public int getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(int d) {
        dashboardId = d;
    }

    public DashboardViewModel() {
        dashboardId = 1;
        imagelist.add(images1);
        imagelist.add(images2);
    }

    /* public LiveData<String> getText() {
        return mText;
    } */

    public ArrayList<int[]> getImages() {return imagelist;}
}