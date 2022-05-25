package com.example.allinone.social_networks_api.reddit;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.adapters.RedditPostsAdapter;
import com.example.allinone.adapters.YoutubeVideosAdapter;


import java.util.ArrayList;

public class GetRedditPostAsyncTask extends AsyncTask<String, String, ArrayList<RedditPost>> {

    private final RecyclerView posts_list;
    private ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
    public RedditPostsAdapter reddit_posts_adapter;
    private LinearLayoutManager layoutManager;


    public GetRedditPostAsyncTask(RecyclerView posts_list) {
        this.posts_list = posts_list;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected ArrayList<RedditPost> doInBackground(String... strings) {

        posts = RedditApi.get_posts(strings[0], strings[1]);
        return posts;

    }

    @Override
    protected void onPostExecute(ArrayList<RedditPost> result) {

        super.onPostExecute(result);
        if (result != null) {
            posts = result;


        }


        layoutManager = new LinearLayoutManager(posts_list.getContext(), LinearLayoutManager.HORIZONTAL, false);
        posts_list.setLayoutManager(layoutManager);
        posts_list.setHasFixedSize(true);


        reddit_posts_adapter = new RedditPostsAdapter(posts.size(), posts);

        posts_list.setAdapter(reddit_posts_adapter);
    }
}
