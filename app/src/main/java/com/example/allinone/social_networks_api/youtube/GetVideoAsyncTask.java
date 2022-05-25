package com.example.allinone.social_networks_api.youtube;

import android.os.AsyncTask;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.allinone.adapters.YoutubeVideosAdapter;

import org.json.JSONArray;

import java.util.ArrayList;

public class GetVideoAsyncTask extends AsyncTask<String, String, ArrayList<YoutubeVideo>> {

    private ArrayList<YoutubeVideo> videos = new ArrayList<YoutubeVideo>();
    private RecyclerView videos_list;
    private LinearLayoutManager layoutManager;
    private Lifecycle lifecycle;

    public YoutubeVideosAdapter yt_videos_adapter;

    public GetVideoAsyncTask(RecyclerView videos_list, Lifecycle lifecycle) {
        this.videos_list = videos_list;
        this.lifecycle = lifecycle;
    }

    @Override
    protected ArrayList<YoutubeVideo> doInBackground(String... strings) {
        if (strings[2].equals("true"))
            videos = YouTubeApi.get_trends_videos();
        else
            videos = YouTubeApi.get_videos(strings[0], strings[1]);
        return videos;
    }

    @Override
    protected void onPostExecute(ArrayList<YoutubeVideo> result) {
        super.onPostExecute(result);
        if (result != null) {
            videos = result;


        }


        layoutManager = new LinearLayoutManager(videos_list.getContext(), LinearLayoutManager.HORIZONTAL, false);
        videos_list.setLayoutManager(layoutManager);
        videos_list.setHasFixedSize(true);


        yt_videos_adapter = new YoutubeVideosAdapter(videos.size(), videos, lifecycle);

        videos_list.setAdapter(yt_videos_adapter);
    }
}
