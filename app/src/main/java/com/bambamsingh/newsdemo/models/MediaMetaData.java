package com.bambamsingh.newsdemo.models;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class MediaMetaData implements Serializable {

    @NonNull
    private String url;

    @NonNull
    private String format;

    @NonNull
    private int height;

    @NonNull
    private int width;

    public MediaMetaData(@NonNull String url, @NonNull String format, @NonNull int height, @NonNull int width) {
        this.url = url;
        this.format = format;
        this.height = height;
        this.width = width;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public String getFormat() {
        return format;
    }

    @NonNull
    public int getHeight() {
        return height;
    }

    @NonNull
    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "MediaMetaData{" +
                "url='" + url + '\'' +
                ", format='" + format + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}