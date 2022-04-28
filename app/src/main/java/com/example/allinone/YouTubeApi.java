package com.example.allinone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class YouTubeApi {


    public static JSONArray get_videos(String str) {

        try {
            System.out.println("--------------VIDEOS--------------\n");
            String TOKEN = "AIzaSyA1M9pXansr4yszgVp0KlJ4FGcpPtAPlY4";
            String req = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + str + "&maxResults=10&type=video&key=" + TOKEN;
            URLConnection conn= null;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try  {
                        URLConnection connection = new URL(req).openConnection();
                        connection.connect();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(1);
                    }
                }
            });

            thread.start();





            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();

            String line;

            System.out.println(1);
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            br.close();

            JSONObject jsonObj = new JSONObject(sb.toString());

            JSONArray items = jsonObj.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {

                JSONObject objects = items.getJSONObject(i);


                System.out.println(objects.getJSONObject("snippet").get("title")+"\n");

                System.out.println("https://www.youtube.com/watch?v=" + objects.getJSONObject("id").get("videoId") + "\n");

            }
            return items;


        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
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
