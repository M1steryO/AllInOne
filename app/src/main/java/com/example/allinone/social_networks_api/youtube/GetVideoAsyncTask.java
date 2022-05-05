package com.example.allinone.social_networks_api.youtube;

import android.os.AsyncTask;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.allinone.adapters.YoutubeVideosAdapter;

import org.json.JSONArray;

public class GetVideoAsyncTask extends AsyncTask<String, String, JSONArray> {

    private JSONArray videos = new JSONArray();
    private RecyclerView videos_list;
    private LinearLayoutManager layoutManager;
    Lifecycle lifecycle;

    public GetVideoAsyncTask(RecyclerView videos_list, Lifecycle lifecycle) {
        this.videos_list = videos_list;
        this.lifecycle = lifecycle;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {

        videos = YouTubeApi.get_videos(strings[0]);
        return videos;
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        super.onPostExecute(result);
        if (result != null) {
            videos = result;


        }


        layoutManager = new LinearLayoutManager(videos_list.getContext(), LinearLayoutManager.HORIZONTAL, false);
        videos_list.setLayoutManager(layoutManager);
        videos_list.setHasFixedSize(true);


        YoutubeVideosAdapter yt_videos_adapter = new YoutubeVideosAdapter(videos.length(), videos, lifecycle);

        videos_list.setAdapter(yt_videos_adapter);
    }
}
