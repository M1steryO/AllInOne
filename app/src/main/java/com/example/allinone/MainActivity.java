package com.example.allinone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView videos_list;
    private YoutubeVideosAdapter yt_videos_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        videos_list = findViewById(R.id.youtube_videos_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        videos_list.setLayoutManager(layoutManager);

        videos_list.setHasFixedSize(true);

        yt_videos_adapter = new YoutubeVideosAdapter(10);
        videos_list.setAdapter(yt_videos_adapter);

    }


}
