package com.example.allinone.social_networks_api.reddit;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.adapters.RedditPostsAdapter;
import com.example.allinone.adapters.YoutubeVideosAdapter;


import java.util.ArrayList;
import java.util.Objects;

public class GetRedditPostAsyncTask extends AsyncTask<String, String, ArrayList<RedditPost>> {

    @SuppressLint("StaticFieldLeak")
    private final RecyclerView posts_list;
    private ArrayList<RedditPost> posts = new ArrayList<RedditPost>();


    public GetRedditPostAsyncTask(RecyclerView posts_list) {
        this.posts_list = posts_list;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected ArrayList<RedditPost> doInBackground(String... strings) {
        if (Objects.equals(strings[2], "true"))
            posts = RedditApi.get_trends_posts();
        else
            posts = RedditApi.get_posts(strings[0], strings[1]);
        return posts;

    }

    @Override
    protected void onPostExecute(ArrayList<RedditPost> result) {

        super.onPostExecute(result);
        if (result != null) {
            posts = result;


        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(posts_list.getContext(), LinearLayoutManager.HORIZONTAL, false);
        posts_list.setLayoutManager(layoutManager);
        posts_list.setHasFixedSize(true);


        RedditPostsAdapter reddit_posts_adapter = new RedditPostsAdapter(posts.size(), posts);

        posts_list.setAdapter(reddit_posts_adapter);
    }
}
