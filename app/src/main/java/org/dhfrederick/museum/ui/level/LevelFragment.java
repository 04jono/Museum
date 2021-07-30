package org.dhfrederick.museum.ui.level;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import org.dhfrederick.museum.LevelListAdapter;
import org.dhfrederick.museum.R;
import org.dhfrederick.museum.ui.dashboard.DashboardFragmentDirections;
import org.dhfrederick.museum.ui.dashboard.DashboardViewModel;

import static org.dhfrederick.museum.ui.dashboard.DashboardFragmentDirections.actionNavigationDashboardToExhibitDetailViewFragment;

public class LevelFragment extends Fragment
{
    private LevelViewModel levelViewModel;

    ListView lView;
    LevelListAdapter lAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        levelViewModel =
                ViewModelProviders.of(this).get(LevelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_level, container, false);
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
        lView = (ListView) getView().findViewById(R.id.level_list);

        lAdapter = new LevelListAdapter(getContext(), levelViewModel.getTitles(), levelViewModel.getImages());

        lView.setAdapter(lAdapter);


        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(MainActivity.this, version[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();
                LevelFragmentDirections.ActionNavigationLevelToNavigationDashboard action = LevelFragmentDirections.actionNavigationLevelToNavigationDashboard();
                action.setListPosition(i+1);
                Navigation.findNavController(getView()).navigate(action);

            }
        });
    }
}
