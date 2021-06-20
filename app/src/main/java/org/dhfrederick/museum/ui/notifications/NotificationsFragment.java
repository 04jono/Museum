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
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        return root;
    }
    private int getHotspotColor (int x, int y) {
        bottomimg.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(bottomimg.getDrawingCache());
        bottomimg.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }
    private boolean match(int color1, int color2, int tolerance){
        if((int)Math.abs(Color.red(color1) - Color.red(color2))>tolerance)
            return false;
        if((int)Math.abs(Color.green(color1) - Color.green(color2))>tolerance)
            return false;
        if((int)Math.abs(Color.blue(color1) - Color.blue(color2))>tolerance)
            return false;
        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        bottomimg = (ImageView) getView().findViewById(R.id.image_areas);
        topimg = (ImageView) getView().findViewById(R.id.topimage);
        topimg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {

                final int action = ev.getAction();

                ImageView imageView = (ImageView) v.findViewById(R.id.topimage);
                if (imageView == null) return false;

                if (action == MotionEvent.ACTION_DOWN) {
                    final int evX = (int) ev.getX();
                    final int evY = (int) ev.getY();
                    int touchColor = getHotspotColor(evX, evY);
                    int tolerance = 30;
                    if (match(Color.RED, touchColor, tolerance)) {
                        int num = 1;
                        navigateToExhibitDetail(num);
                        return true;
                    } else if (match(Color.GREEN, touchColor, tolerance)) {
                        int num = 2;
                        navigateToExhibitDetail(num);
                        return true;
                    } else if (match(Color.BLUE, touchColor, tolerance)) {
                        int num = 3;
                        navigateToExhibitDetail(num);
                        return true;
                    } else if (match(Color.YELLOW, touchColor, tolerance)) {
                        int num = 4;
                        navigateToExhibitDetail(num);
                        return true;
                    }
                }
                return false;
            }
        });
    }
    private void navigateToExhibitDetail(int num)
    {
        NotificationsFragmentDirections.ActionNavigationNotificationsToNavigationExhibitDetail action = actionNavigationNotificationsToNavigationExhibitDetail();
        action.setListPosition(num);
        Navigation.findNavController(getView()).navigate(action);
    }
}