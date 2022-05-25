package com.example.allinone.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import com.example.allinone.adapters.RedditPostsAdapter;
import com.example.allinone.adapters.SampleFragmentPagerAdapter;
import com.example.allinone.social_networks_api.reddit.GetRedditPostAsyncTask;
import com.example.allinone.social_networks_api.youtube.GetVideoAsyncTask;
import com.google.android.material.tabs.TabLayout;

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

//        GetRedditPostAsyncTask get_reddit_post = new GetRedditPostAsyncTask(reddit_posts_list);
//        get_reddit_post.execute("", "popular");

// Attach adapter to recyclerView.
        // Получаем ViewPager и устанавливаем в него адаптер
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(
                new SampleFragmentPagerAdapter(getChildFragmentManager(), requireContext()));

        // Передаём ViewPager в TabLayout
        TabLayout tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);



        return view;

    }



}