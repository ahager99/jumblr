package com.tumblr.jumblr.types;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

/**
 * This class represents a photo at a given size
 * @author jc
 */
public class PhotoSize extends SourceInterface {

    private int width, height;
    private String url;
    private String filename;

    /**
     * Get the URL of this photo at this size
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get the width of this photo
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of this photo
     * @return height
     */
    public int getHeight() {
        return height;
    }

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

}
