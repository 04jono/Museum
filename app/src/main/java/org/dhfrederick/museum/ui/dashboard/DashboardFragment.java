package org.dhfrederick.museum.ui.dashboard;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import org.dhfrederick.museum.ExhibitDetailViewFragment;
import org.dhfrederick.museum.ExhibitDetailViewFragmentArgs;
import org.dhfrederick.museum.ExhibitListAdapter;
import org.dhfrederick.museum.MainActivity;
import org.dhfrederick.museum.R;

import java.util.ArrayList;

import static org.dhfrederick.museum.ui.dashboard.DashboardFragmentDirections.*;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private String[] titles;
    private String[] descriptions;
    ListView lView;
    ExhibitListAdapter lAdapter;
    public static DashboardFragment newInstance(int list)
    {
        DashboardFragment dashboardFragment = new DashboardFragment();

        Bundle args = new Bundle();
        args.putInt("listPosition", list);
        dashboardFragment.setArguments(args);

        return dashboardFragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        /* final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });  */


        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        final int dashboardNum = ExhibitDetailViewFragmentArgs.fromBundle(getArguments()).getListPosition();
        dashboardViewModel.setDashboardId(dashboardNum);
        lView = (ListView) getView().findViewById(R.id.exhibits_list);

        Resources res = getResources();
        switch(dashboardNum){
            case 1:
                titles = res.getStringArray(R.array.titles1);
                descriptions = res.getStringArray(R.array.descriptions1);
                break;
            case 2:
                titles = res.getStringArray(R.array.titles2);
                descriptions = res.getStringArray(R.array.descriptions2);
                break;
        }
        ArrayList<int[]> imagelist = dashboardViewModel.getImages();
        int[] images = imagelist.get(dashboardNum-1);

        lAdapter = new ExhibitListAdapter(getContext(), titles,
                descriptions, images);

        lView.setAdapter(lAdapter);


        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(MainActivity.this, version[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();
                ActionNavigationDashboardToExhibitDetailViewFragment action = actionNavigationDashboardToExhibitDetailViewFragment();
                action.setListPosition(i+1 + (dashboardNum * 10));
                Navigation.findNavController(getView()).navigate(action);

            }
        });
    }
}