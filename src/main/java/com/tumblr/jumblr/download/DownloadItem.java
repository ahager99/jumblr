package com.tumblr.jumblr.download;

import com.tumblr.jumblr.types.Post.PostType;

public class DownloadItem {

    private String url;
    private String filename;
    private PostType type;

    public DownloadItem(String url, String filename, PostType type) {
        this.url = url;
        this.filename = filename;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public String getFilename() {
        return filename;
    }

    public PostType getType() {
        return type;
    }    
}
