package com.example.allinone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.example.allinone.social_networks_api.reddit.GetRedditPostAsyncTask;
import com.example.allinone.social_networks_api.youtube.GetVideoAsyncTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView reddit_posts_list;
    private RecyclerView youtube_videos_list;
    private EditText search_text;
    private ScrollView scrollView;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        search_text = (EditText) findViewById(R.id.search_text);
        scrollView = findViewById(R.id.main_scroll_view);

        reddit_posts_list = findViewById(R.id.reddit_posts_list);
        youtube_videos_list = findViewById(R.id.youtube_videos_list);

        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String req = (search_text.getText().toString());

                    GetRedditPostAsyncTask get_reddit_post = new GetRedditPostAsyncTask(reddit_posts_list);
                    get_reddit_post.execute(search_text.getText().toString());


                    GetVideoAsyncTask get_videos = new GetVideoAsyncTask(youtube_videos_list, getLifecycle());
                    get_videos.execute(req);
                    TextView title = findViewById(R.id.yt_list_title);
                    title.setText("Видео по запросу - " + req);

                    handled = true;
                }
                return handled;
            }
        });

        //get the image view
        ImageView reddit_posts_btn = (ImageView) findViewById(R.id.reddit_posts_btn);

        //set the ontouch listener
        reddit_posts_btn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                GetRedditPostAsyncTask get_reddit_post = new GetRedditPostAsyncTask(reddit_posts_list);
                get_reddit_post.execute(search_text.getText().toString());


                return true;
            }
        });
        ImageView youtube_videos_btn = findViewById(R.id.youtube_videos_btn);

        //set the ontouch listener
        youtube_videos_btn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                GetVideoAsyncTask get_videos = new GetVideoAsyncTask(youtube_videos_list, getLifecycle());
                get_videos.execute(search_text.getText().toString());
                TextView title = findViewById(R.id.yt_list_title);
                title.setText("Видео по запросу - " + search_text.getText().toString());
                return true;
            }
        });


    }



}


//        class GetPlaylistAsyncTask extends AsyncTask<String, String, JSONArray> {
//
//            JSONArray videos = new JSONArray();
//
//            @Override
//            protected JSONArray doInBackground(String... strings) {
//
//                videos = YouTubeApi.get_playlists(strings[0]);
//                return videos;
//            }
//
//            @Override
//            protected void onPostExecute(JSONArray result) {
//                super.onPostExecute(result);
//                if (result != null) {
//                    videos = result;
//
//
//                }
//
//                videos_list = findViewById(R.id.youtube_videos_list);
//
//
//                layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//                videos_list.setLayoutManager(layoutManager);
//                videos_list.setHasFixedSize(true);
//
//
//                YoutubeVideosAdapter yt_videos_adapter = new YoutubeVideosAdapter(10, videos, getLifecycle());
//
//                videos_list.setAdapter(yt_videos_adapter);
//            }
//        }
//    }



