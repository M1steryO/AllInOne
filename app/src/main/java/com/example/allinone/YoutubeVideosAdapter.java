package com.example.allinone;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;

public class YoutubeVideosAdapter extends RecyclerView.Adapter<YoutubeVideosAdapter.VideoViewHolder> {


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {


        TextView listItemVideoTitleView;
        ImageView listItemVideoImgView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            listItemVideoTitleView = itemView.findViewById(R.id.youtube_video_title);
            listItemVideoImgView = itemView.findViewById(R.id.youtube_video_img);
        }


        void bind(String videoTitle, Uri VideoImgLink){
            listItemVideoTitleView.setText(videoTitle);
            listItemVideoImgView.setImageURI(VideoImgLink);
        }
    }
}
