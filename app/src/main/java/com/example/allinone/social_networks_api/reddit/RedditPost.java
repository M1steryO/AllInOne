package com.example.allinone.social_networks_api.reddit;

public class RedditPost {


    public String author;
    public String imageUrl;
    public String postTime;
    public String rScore;
    public String title;
    public String videoUrl;

    public RedditPost(String author, String imageUrl, String postTime, String rScore, String title, String videoUrl) {
        this.author = author;
        this.imageUrl = imageUrl;
        this.postTime = postTime;
        this.rScore = rScore;
        this.title = title;
        this.videoUrl = videoUrl;
    }
}
