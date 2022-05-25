package com.example.allinone.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.allinone.R;
import com.example.allinone.social_networks_api.reddit.GetRedditPostAsyncTask;
import com.example.allinone.social_networks_api.youtube.GetVideoAsyncTask;

import org.w3c.dom.Text;


public class HomeFragment extends Fragment {
    private RecyclerView reddit_posts_list;
    private RecyclerView youtube_videos_list;
    private ViewFlipper vf;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        vf = view.findViewById(R.id.vf);
        vf.setDisplayedChild(0);
        reddit_posts_list = view.findViewById(R.id.reddit_posts_list);
        youtube_videos_list = view.findViewById(R.id.youtube_videos_list);
        TextView popular_list_btn = view.findViewById(R.id.popular_list_button);
        popular_list_btn.setOnClickListener(popular_list_btn_listener);
        TextView favourite_list_btn = view.findViewById(R.id.favourite_list_button);
        favourite_list_btn.setOnClickListener(favorite_list_btn_listener);
        GetRedditPostAsyncTask get_reddit_post = new GetRedditPostAsyncTask(reddit_posts_list);
        get_reddit_post.execute("", "popular");
        GetVideoAsyncTask get_videos = new GetVideoAsyncTask(youtube_videos_list, getLifecycle());
        get_videos.execute("", "popular", "true");
        return view;

    }

    View.OnClickListener popular_list_btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            vf.setDisplayedChild(0);

        }
    };

    View.OnClickListener favorite_list_btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            vf.setDisplayedChild(1);
        }
    };

}