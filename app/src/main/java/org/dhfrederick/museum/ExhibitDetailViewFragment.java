package org.dhfrederick.museum;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.dhfrederick.museum.ui.dashboard.DashboardFragmentDirections;
import org.dhfrederick.museum.ui.dashboard.DashboardViewModel;
import org.dhfrederick.museum.ui.level.LevelFragmentDirections;
import org.dhfrederick.museum.ui.notifications.NotificationsFragment;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.dhfrederick.museum.ui.dashboard.DashboardFragmentDirections.actionNavigationDashboardToExhibitDetailViewFragment;

public class ExhibitDetailViewFragment extends Fragment {

    private ExhibitDetailViewModel exhibitDetailViewModel;
    private MediaPlayer player;
    private Handler handler = new Handler();
    private Runnable runnable;
    private TextView playerPosition, playerDuration;
    private SeekBar seekbar;
    private ImageView btrewind, btplay, btpause, btforward;
    private ImageView backbutton;

    //For title bar
    private String[] titles;
    /*
    public ExhibitDetailViewFragment()
    {} */

    public static ExhibitDetailViewFragment newInstance(int list)
    {
        ExhibitDetailViewFragment exhibitDetailFragment = new ExhibitDetailViewFragment();

        Bundle args = new Bundle();
        args.putInt("listPosition", list);
        exhibitDetailFragment.setArguments(args);

        return exhibitDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        exhibitDetailViewModel =
                ViewModelProviders.of(this).get(ExhibitDetailViewModel.class);

        return inflater.inflate(R.layout.fragment_exhibit_detail_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        int listpos = ExhibitDetailViewFragmentArgs.fromBundle(getArguments()).getListPosition();
        int exhibitNum = listpos % 10;
        final int dashboardNum = listpos / 10;
        Resources res = getResources();
        switch(dashboardNum){
            case 1:
                titles = res.getStringArray(R.array.titles1);
                break;
            case 2:
                titles = res.getStringArray(R.array.titles2);
                break;
        }
        String title = titles[exhibitNum-1];
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        exhibitDetailViewModel.setExhibitId(exhibitNum);

        ArrayList<int[]> imagelist = exhibitDetailViewModel.getImages();
        int[] images = imagelist.get(dashboardNum - 1);

        ArrayList<int[]> audiolist = exhibitDetailViewModel.getAudios();
        int[] audios = audiolist.get(dashboardNum - 1);

        String[] text = {};
        if(dashboardNum == 1)
            text = res.getStringArray(R.array.exhibit1_info);
        else if(dashboardNum == 2)
            text = res.getStringArray(R.array.exhibit2_info);
        ImageView imgView = (ImageView) getView().findViewById(R.id.exhibit_detail_image_view);
        imgView.setImageResource(images[exhibitNum - 1]);
        TextView txtView = (TextView) getView().findViewById(R.id.textView);
        txtView.setText(text[exhibitNum - 1]);

        //Back Button
        backbutton = getView().findViewById(R.id.button_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExhibitDetailViewFragmentDirections.ActionNavigationExhibitDetailToNavigationDashboard action = ExhibitDetailViewFragmentDirections.actionNavigationExhibitDetailToNavigationDashboard();
                action.setListPosition(dashboardNum);
                Navigation.findNavController(getView()).navigate(action);
            }
        });



        //Media Player
        playerPosition = getView().findViewById(R.id.playerpos);
        playerDuration = getView().findViewById(R.id.player_duration);
        seekbar = getView().findViewById(R.id.seek_bar);
        btrewind = getView().findViewById(R.id.button_rewind);
        btpause = getView().findViewById(R.id.button_pause);
        btplay = getView().findViewById(R.id.button_play);
        btforward = getView().findViewById(R.id.button_forward);


        player = MediaPlayer.create(getActivity(), audios[exhibitNum - 1]);
        runnable = new Runnable() {
            @Override
            public void run() {

                seekbar.setProgress(player.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        };

        int duration = player.getDuration();
        String strDuration = convertFormat(duration);
        playerDuration.setText(strDuration);

        //Play
        btplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btplay.setVisibility(View.GONE);
                btpause.setVisibility(View.VISIBLE);
                player.start();
                seekbar.setMax(player.getDuration());
                handler.postDelayed(runnable, 0);
            }
        });
        //Pause
        btpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btpause.setVisibility(View.GONE);
                btplay.setVisibility(View.VISIBLE);
                player.pause();
                handler.removeCallbacks(runnable);
            }
        });
        //Fast Forward
        btforward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currPosition = player.getCurrentPosition();
                int duration = player.getDuration();
                if (player.isPlaying() && duration != currPosition) {
                    currPosition += 5000;
                    playerPosition.setText(convertFormat(currPosition));
                    player.seekTo(currPosition);
                }
            }
        });
        //Fast Rewind
        btrewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currPosition = player.getCurrentPosition();
                int duration = player.getDuration();
                if (player.isPlaying() && currPosition >= 5000) {
                    currPosition -= 5000;
                    playerPosition.setText(convertFormat(currPosition));
                    player.seekTo(currPosition);
                } else if (player.isPlaying()) {
                    currPosition = 0;
                    playerPosition.setText(convertFormat(currPosition));
                    player.seekTo(currPosition);
                }
            }
        });


        //Seek Bar Updating
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    player.seekTo(progress);
                playerPosition.setText(convertFormat(player.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //Player finishes, reset
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btpause.setVisibility(View.GONE);
                btplay.setVisibility(View.VISIBLE);
                player.seekTo(0);
            }
        });
    }

    private String convertFormat(int duration) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration), TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }
    @Override
    public void onPause()
    {
        stop();
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onDestroy()
    {
        stop();
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer()
    {
        if (player != null)
        {
            player.release();
            player = null;
        }
    }

    public void stop(){
        handler.removeCallbacks(runnable);
    }
}