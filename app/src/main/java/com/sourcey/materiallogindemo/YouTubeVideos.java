package com.sourcey.materiallogindemo;

/**
 * Created by HP on 14-Sep-18.
 */

public class YouTubeVideos {

    String videoUrl;

    public YouTubeVideos(){

    }

    public YouTubeVideos(String videoUrl){
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl(){
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl){
        this.videoUrl = videoUrl;
    }
}
