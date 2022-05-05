package com.example.allinone.adapters;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;

import com.example.allinone.social_networks_api.reddit.RedditPost;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class RedditPostsAdapter extends RecyclerView.Adapter<RedditPostsAdapter.RedditPostViewHolder> {

    private final int numberPosts;
    private ArrayList<RedditPost> posts;


    public RedditPostsAdapter(int NumberOfItems, ArrayList<RedditPost> getting_posts) {
        numberPosts = NumberOfItems;
        posts = getting_posts;


    }


    @Override
    public RedditPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reddit_post_item, parent, false);
        Context context = parent.getContext();
        return new RedditPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RedditPostViewHolder holder, int position) {
        System.out.println(posts.size());
        if (posts.size() !=0) {

            RedditPost post = posts.get(position);
            System.out.println(post.imageUrl);
            if (post.title.length() > 180) {
                String out = post.title.substring(0, 180) + "....читать далее";
                holder.reddit_post_title.setText(out);
            } else {
                holder.reddit_post_title.setText(post.title);
            }
            String subreddit = "r/" + post.author;
            holder.reddit_post_subreddit.setText(subreddit);
            holder.reddit_post_time.setText(post.postTime);
            if (post.imageUrl != null) {
//            holder.reddit_post_video.setVisibility(View.GONE);
                holder.reddit_post_img.setVisibility(View.VISIBLE);
                Picasso.get().load(post.imageUrl).resize(1000, 1000).into(holder.reddit_post_img);

            }
//        else if (post.videoUrl != null){
//            Uri uri = Uri.parse(post.videoUrl);
//            holder.reddit_post_img.setVisibility(View.GONE);
//            holder.reddit_post_video.setVisibility(View.VISIBLE);
//            holder.reddit_post_video.setVideoURI(uri);
//            holder.reddit_post_video.start();
//        }
        }
        else
        {
            holder.no_content_warning.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public int getItemCount() {
        return numberPosts;
    }

    static class RedditPostViewHolder extends RecyclerView.ViewHolder {
        private final TextView reddit_post_title;
        private final TextView reddit_post_subreddit;
        private final ImageView reddit_post_img;
        private final TextView reddit_post_time;
        private final TextView no_content_warning;
//        private final VideoView reddit_post_video;

        RedditPostViewHolder(View itemView) {
            super(itemView);
            reddit_post_title = itemView.findViewById(R.id.reddit_post_title);
            reddit_post_subreddit = itemView.findViewById(R.id.reddit_post_subreddit);
            reddit_post_img = itemView.findViewById(R.id.reddit_post_img);
            reddit_post_time = itemView.findViewById(R.id.reddit_post_time);
            no_content_warning = itemView.findViewById(R.id.NoContentWarningText);
//            reddit_post_video = itemView.findViewById(R.id.reddit_post_video);
        }


    }


}



