package com.saifulla.server_android;


public class DataAdapter
{
    public String ImageURL;
    public String VideoTitle;
    public String VideoUser;
    public String VideoDesc;
    public String VideoURL;
    public String VideoID;

    public String getImageUrl() {
        return ImageURL;
    }

    public void setImageUrl(String imageServerUrl) {
        this.ImageURL = imageServerUrl;
    }

    public String getVideoTitle() {
        return VideoTitle;
    }

    public String getVideoUser() {
        return VideoUser;
    }

    public String getVideoDesc() {
        return VideoDesc;
    }


    public String getVideoURL() {
        return VideoURL;
    }

    public String getVideoID() {
        return VideoID;
    }


    public void setVideoTitle(String Videotitlename) {
        this.VideoTitle = Videotitlename;
    }

    public void setVideoUser(String VideoUserName) {
        this.VideoUser = VideoUserName;
    }

    public void setVideoDesc(String Videodesc) {
        this.VideoDesc = Videodesc;
    }

    public void setVideoURL(String Videourl) {
        this.VideoURL = Videourl;
    }

    public void setVideoID(String Videoid) {
        this.VideoID = Videoid;
    }

}