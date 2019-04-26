package com.bambamsingh.newsdemo.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;

public class Media implements Serializable {

    @NonNull
    private String type;

    @NonNull
    private String subtype;

    @NonNull
    private String caption;

    @NonNull
    private String copyright;

    @NonNull
    @SerializedName("approved_for_syndication")
    private int approvedForSyndication;

    @NonNull
    @SerializedName("media-metadata")
    private List<MediaMetaData> mediaMetaData;

    public Media(@NonNull String type, @NonNull String subtype, @NonNull String caption, @NonNull String copyright, @NonNull int approvedForSyndication, @NonNull List<MediaMetaData> mediaMetaData) {
        this.type = type;
        this.subtype = subtype;
        this.caption = caption;
        this.copyright = copyright;
        this.approvedForSyndication = approvedForSyndication;
        this.mediaMetaData = mediaMetaData;
    }

    @NonNull
    public String getType() {
        return type;
    }

    @NonNull
    public String getSubtype() {
        return subtype;
    }

    @NonNull
    public String getCaption() {
        return caption;
    }

    @NonNull
    public String getCopyright() {
        return copyright;
    }

    @NonNull
    public int getApprovedForSyndication() {
        return approvedForSyndication;
    }

    @NonNull
    public List<MediaMetaData> getMediaMetaData() {
        return mediaMetaData;
    }

    @Override
    public String toString() {
        return "Media{" +
                "type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", caption='" + caption + '\'' +
                ", copyright='" + copyright + '\'' +
                ", approvedForSyndication=" + approvedForSyndication +
                ", mediaMetaData=" + mediaMetaData +
                '}';
    }
}