package com.example.allinone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;
import com.example.allinone.social_networks_api.reddit.GetRedditPostAsyncTask;
import com.example.allinone.social_networks_api.youtube.GetVideoAsyncTask;

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;


    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        if (mPage == 1) {
            view = inflater.inflate(R.layout.popular_posts_layout, container, false);
            RecyclerView youtube_videos_list = view.findViewById(R.id.youtube_videos_list);
            GetVideoAsyncTask get_videos = new GetVideoAsyncTask(youtube_videos_list, getLifecycle());
            get_videos.execute("", "popular", "true");
            RecyclerView reddit_posts_list = view.findViewById(R.id.reddit_posts_list);
            GetRedditPostAsyncTask get_reddit_post = new GetRedditPostAsyncTask(reddit_posts_list);
            get_reddit_post.execute("", "popular", "true");
        } else {
            view = inflater.inflate(R.layout.favourite_posts_layout, container, false);

        }
        return view;
    }
}