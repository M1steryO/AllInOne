package com.example.allinone;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class YoutubeVideosAdapter extends RecyclerView.Adapter<YoutubeVideosAdapter.VideoViewHolder> {

    private int numberVideos;

    JSONArray videos;
    public YoutubeVideosAdapter(int NumberOfItems) {
        numberVideos = NumberOfItems;
        videos = YouTubeApi.get_videos("math");
        System.out.println(videos);
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.youtube_video_item;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        JSONObject video;
        try {
            video = videos.getJSONObject(position);
            holder.listItemVideoTitleView.setText((String) video.getJSONObject("snippet").get("title"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return numberVideos;
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {


        TextView listItemVideoTitleView;
        ImageView listItemVideoImgView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            listItemVideoTitleView = itemView.findViewById(R.id.youtube_video_title);

        }


        void bind(String videoTitle, Uri VideoImgLink) {
            listItemVideoTitleView.setText(videoTitle);
            listItemVideoImgView.setImageURI(VideoImgLink);
        }
    }
}
