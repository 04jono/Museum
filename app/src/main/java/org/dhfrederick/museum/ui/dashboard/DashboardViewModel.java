package org.dhfrederick.museum.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.dhfrederick.museum.R;

public class DashboardViewModel extends ViewModel {

    // private MutableLiveData<String> mText;
    private int dashboardId;
    int[] images = {R.drawable.exhibit1, R.drawable.exhibit2, R.drawable.exhibit3, R.drawable.exhibit4};

    String[] titles = {"Exhibit1", "Exhibit2", "Exhibit3", "Exhibit4"};

    String[] descriptions = {"Description 1", "Description 2", "Description 3", "Description 4"};

    public int getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(int d) {
        dashboardId = d;
    }

    public DashboardViewModel() {
        dashboardId = 1;
    }

    /* public LiveData<String> getText() {
        return mText;
    } */

    public int[] getImages() {return images;}
    public String[] getTitles() {return titles;}
    public String[] getDescriptions() { return descriptions;}
}