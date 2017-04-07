package com.rahul_lohra.redditstar.modal.custom;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rkrde on 22-02-2017.
 */

public class DetailPostModal implements Parcelable {
    private String id;          
    private String subreddit;   
    private String postHint;
    private String ups;         
    private String title;       
    private String commentsCount;
    private String thumbnail;
    private String time;        
    private String author;      
    private Integer likes;      
    private String name;        
    private String bigImageUrl;
    private String url;
    private String domain;
    private int thumbnailHasImage;
    private int BigImageUrlHasImage;

    public DetailPostModal(String id,
                           String subreddit,
                           String postHint,
                           String ups,
                           String title,
                           String commentsCount,
                           String thumbnail,
                           String time,
                           String author,
                           Integer likes,
                           String name,
                           String bigImageUrl,
                           String url,
                           String domain,
                           int thumbnailHasImage,
                           int bigImageUrlHasImage) {
        this.id = id;
        this.subreddit = subreddit;
        this.postHint = postHint;
        this.ups = ups;
        this.title = title;
        this.commentsCount = commentsCount;
        this.thumbnail = thumbnail;
        this.time = time;
        this.author = author;
        this.likes = likes;
        this.name = name;
        this.bigImageUrl = bigImageUrl;
        this.url = url;
        this.domain = domain;
        this.thumbnailHasImage = thumbnailHasImage;
        BigImageUrlHasImage = bigImageUrlHasImage;
    }

    public int getThumbnailHasImage() {
        return thumbnailHasImage;
    }

    public int getBigImageUrlHasImage() {
        return BigImageUrlHasImage;
    }

    public String getDomain() {
        return domain;
    }

    public String getUrl() {
        return url;
    }

    public String getPostHint() {
        return postHint;
    }

    public String getBigImageUrl() {
        return bigImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getUps() {
        return ups;
    }

    public void setUps(String ups) {
        this.ups = ups;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.subreddit);
        dest.writeString(this.postHint);
        dest.writeString(this.ups);
        dest.writeString(this.title);
        dest.writeString(this.commentsCount);
        dest.writeString(this.thumbnail);
        dest.writeString(this.time);
        dest.writeString(this.author);
        dest.writeValue(this.likes);
        dest.writeString(this.name);
        dest.writeString(this.bigImageUrl);
    }

    protected DetailPostModal(Parcel in) {
        this.id = in.readString();
        this.subreddit = in.readString();
        this.postHint = in.readString();
        this.ups = in.readString();
        this.title = in.readString();
        this.commentsCount = in.readString();
        this.thumbnail = in.readString();
        this.time = in.readString();
        this.author = in.readString();
        this.likes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.bigImageUrl = in.readString();
    }

    public static final Creator<DetailPostModal> CREATOR = new Creator<DetailPostModal>() {
        @Override
        public DetailPostModal createFromParcel(Parcel source) {
            return new DetailPostModal(source);
        }

        @Override
        public DetailPostModal[] newArray(int size) {
            return new DetailPostModal[size];
        }
    };
}
