package com.example.allinone.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;


public class YoutubeVideosAdapter extends RecyclerView.Adapter<YoutubeVideosAdapter.ViewHolder> {

    private final int numberVideos;
    JSONArray videos;
    private Lifecycle lifecycle;


    public YoutubeVideosAdapter(int NumberOfItems, JSONArray getting_videos, Lifecycle lifecycle) {
        numberVideos = NumberOfItems;
        videos = getting_videos;
        this.lifecycle = lifecycle;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_video_item, parent, false);
        lifecycle.addObserver(youTubePlayerView);


        return new ViewHolder(youTubePlayerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.cueVideo((String) videos.getJSONObject(position).getJSONObject("id").get("videoId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return numberVideos;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private YouTubePlayerView youTubePlayerView;
        private YouTubePlayer youTubePlayer;
        private String currentVideoId;

        ViewHolder(YouTubePlayerView playerView) {
            super(playerView);
            youTubePlayerView = playerView;

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer initializedYouTubePlayer) {
                    youTubePlayer = initializedYouTubePlayer;
                    youTubePlayer.cueVideo(currentVideoId, 0);
                }
            });
        }

        void cueVideo(String videoId) {
            currentVideoId = videoId;

            if (youTubePlayer == null)
                return;
            if (videoId == null) {
                return;
            }

            youTubePlayer.cueVideo(videoId, 0);
        }
    }



}


