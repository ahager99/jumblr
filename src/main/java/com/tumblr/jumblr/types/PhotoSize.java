package com.tumblr.jumblr.types;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tumblr.jumblr.download.DownloadInterface;
import com.tumblr.jumblr.download.DownloadItem;
import com.tumblr.jumblr.types.Post.PostType;

import org.apache.commons.io.FilenameUtils;

/**
 * This class represents a photo at a given size
 * 
 * @author jc
 */
public class PhotoSize implements DownloadInterface {

    private int width, height;
    private String url;

    public PhotoSize(String url, Integer height, Integer width) {
        this.url = url;
        this.height = height;
        this.width = width;
    }

    /**
     * Get the URL of this photo at this size
     * 
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get the width of this photo
     * 
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of this photo
     * 
     * @return height
     */
    public int getHeight() {
        return height;
    }

    @Override
    public List<DownloadItem> getDownloadItems() {
        List<DownloadItem> retVal = new ArrayList<DownloadItem>();

        URL urlObj;
        try {
            urlObj = new URL(url);
            String filename = FilenameUtils.getName(urlObj.getPath());
            retVal.add(new DownloadItem(url, filename, PostType.PHOTO));
        } catch (MalformedURLException ex) {
            Logger.getLogger(PhotoSize.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retVal;
    }

    /*

    @Override
    public String getSrc() {
         return url;
    }

    @Override
    public String getFileName() {
        if (filename == null) {
            URL urlObj;
            try {
                urlObj = new URL(url);
                filename = FilenameUtils.getName(urlObj.getPath());
            } catch (MalformedURLException ex) {
                Logger.getLogger(PhotoSize.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return filename;
    }

    */

}
