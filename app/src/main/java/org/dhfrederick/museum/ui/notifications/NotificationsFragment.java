package org.dhfrederick.museum.ui.notifications;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.dhfrederick.museum.ExhibitListAdapter;
import org.dhfrederick.museum.R;
import org.dhfrederick.museum.ui.dashboard.DashboardFragmentDirections;

import static org.dhfrederick.museum.ui.dashboard.DashboardFragmentDirections.actionNavigationDashboardToExhibitDetailViewFragment;
import static org.dhfrederick.museum.ui.notifications.NotificationsFragmentDirections.actionNavigationNotificationsToNavigationExhibitDetail;

public class NotificationsFragment extends Fragment{

    private NotificationsViewModel notificationsViewModel;
    private ImageView bottomimg;
    private ImageView topimg;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        //Start map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.google_map);

        //Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(39.41517934714381, -77.40910019387118) , 18.0f) );
            }
        });
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
    private void navigateToExhibitDetail(int num)
    {
        NotificationsFragmentDirections.ActionNavigationNotificationsToNavigationExhibitDetail action = actionNavigationNotificationsToNavigationExhibitDetail();
        action.setListPosition(num);
        Navigation.findNavController(getView()).navigate(action);
    }
}