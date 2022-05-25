package com.example.allinone.social_networks_api.youtube;

import com.example.allinone.social_networks_api.reddit.RedditPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YouTubeApi {


    public static final Map<String, String> sort_values = new HashMap<>();
    public static final String TOKEN = "AIzaSyA1M9pXansr4yszgVp0KlJ4FGcpPtAPlY4";

    static {
        sort_values.put("popular", "rating");
        sort_values.put("date", "date");
    }

    public static ArrayList<YoutubeVideo> get_videos(String str, String sort) {


        ArrayList<YoutubeVideo> videos = new ArrayList<YoutubeVideo>();
        try {

            String req = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + str + "&maxResults=10&regionCode=RU&type=video&order=" + sort_values.get(sort) + "&key=" + TOKEN;

            URLConnection connection = new URL(req).openConnection();

            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection
                    .getInputStream()));

            StringBuffer sb = new StringBuffer();

            String line;


            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            br.close();

            JSONObject jsonObj = new JSONObject(sb.toString());

            JSONArray items = jsonObj.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {


                String title = items.getJSONObject(i).getJSONObject("snippet").getString("title");
                String description = items.getJSONObject(i).getJSONObject("snippet").getString("description");
                String video_id = items.getJSONObject(i).getJSONObject("id").getString("videoId");

                YoutubeVideo video = new YoutubeVideo(title, description, video_id);

                videos.add(video);
            }

        } catch (IOException | JSONException e) {

            e.printStackTrace();


        }


        return videos;
    }

    public static ArrayList<YoutubeVideo> get_trends_videos() {

        ArrayList<YoutubeVideo> videos = new ArrayList<YoutubeVideo>();

        try {
            String req = "https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&regionCode=RU&maxResults=15&key=" + TOKEN;

            URLConnection connection = new URL(req).openConnection();

            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection
                    .getInputStream()));

            StringBuffer sb = new StringBuffer();

            String line;


            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            br.close();

            JSONObject jsonObj = new JSONObject(sb.toString());

            JSONArray items = jsonObj.getJSONArray("items");

            System.out.println(items);
            for (int i = 0; i < items.length(); i++) {


                String title = items.getJSONObject(i).getJSONObject("snippet").getString("title");
                String description = items.getJSONObject(i).getJSONObject("snippet").getString("description");
                String video_id = items.getJSONObject(i).getString("id");

                YoutubeVideo video = new YoutubeVideo(title, description, video_id);

                videos.add(video);
            }


        } catch (IOException | JSONException e) {

            e.printStackTrace();


        }

        return videos;
    }

    public static void get_playlists(String str) {

        try {
            System.out.println("--------------PLAYLISTS--------------\n");
            String TOKEN = "AIzaSyA1M9pXansr4yszgVp0KlJ4FGcpPtAPlY4";
            String req = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + str + "&maxResults=10&type=playlist&key=" + TOKEN;
            URL u = new URL(req);
            URLConnection conn = u.openConnection();
            conn.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();

            String line;


            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            br.close();

            JSONObject jsonObj = new JSONObject(sb.toString());

            JSONArray items = jsonObj.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject objects = items.getJSONObject(i);
                System.out.println(objects.getJSONObject("snippet").get("title"));
                System.out.println("https://www.youtube.com/playlist?list=" + objects.getJSONObject("id").get("playlistId") + "\n");

            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


}
