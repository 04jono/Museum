package org.dhfrederick.museum.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
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

import static org.dhfrederick.museum.ui.dashboard.DashboardFragmentDirections.*;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    ListView lView;
    ExhibitListAdapter lAdapter;

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
        lView = (ListView) getView().findViewById(R.id.exhibits_list);

        lAdapter = new ExhibitListAdapter(getContext(), dashboardViewModel.getTitles(),
                dashboardViewModel.getDescriptions(), dashboardViewModel.getImages());

        lView.setAdapter(lAdapter);


        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(MainActivity.this, version[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();
                ActionNavigationDashboardToExhibitDetailViewFragment action = actionNavigationDashboardToExhibitDetailViewFragment();
                action.setListPosition(i+1);
                Navigation.findNavController(getView()).navigate(action);

            }
        });
    }
}