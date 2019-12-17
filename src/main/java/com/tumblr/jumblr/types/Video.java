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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * An individual Video in a VideoPost
 * 
 * @author jc
 */
public class Video implements DownloadInterface {

    private Integer width;
    private String embed_code;


    public Video(String embed_code, Integer width) {
        this.embed_code = embed_code;
        this.width = width;
    }

    /**
     * Get the width of this video
     * 
     * @return width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Get the embed code for this video
     * 
     * @return embed code
     */
    public String getEmbedCode() {
        return embed_code;
    }

    

    @Override
    public List<DownloadItem> getDownloadItems() {
        List<DownloadItem> retVal = new ArrayList<DownloadItem>();
        if (getEmbedCode() != null) {
            Document doc = Jsoup.parse(getEmbedCode());
            URL urlObj;
            try {
                String src = doc.getElementsByAttribute("src").first().attr("src");
                urlObj = new URL(src);
                String filename = FilenameUtils.getName(urlObj.getPath());
                // If not contains tumblr then it's not a correct filename
                if (!filename.contains("tumblr")) {
                    String tmpSrc = src.substring(0, src.lastIndexOf("/"));
                    urlObj = new URL(tmpSrc);
                    filename = FilenameUtils.getName(urlObj.getPath());
                }

                retVal.add(new DownloadItem(src, filename, PostType.VIDEO));

            } catch (MalformedURLException ex) {
                Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retVal;
    }
    
    /*
    private void getSourceData() {
        if (getEmbedCode() != null) {
            Document doc = Jsoup.parse(getEmbedCode());
            URL urlObj;
            try {
                src = doc.getElementsByAttribute("src").first().attr("src");
                type = doc.getElementsByAttribute("type").first().attr("type");

                urlObj = new URL(src);
                filename = FilenameUtils.getName(urlObj.getPath());
                // If not contains tumblr then it's not a correct filename
                if (!filename.contains("tumblr")) {
                    String tmpSrc = src.substring(0, src.lastIndexOf("/"));
                    urlObj = new URL(tmpSrc);
                    filename = FilenameUtils.getName(urlObj.getPath());
                }
                // filename += "." + type.substring(type.lastIndexOf("/") + 1);

            } catch (MalformedURLException ex) {
                Logger.getLogger(PhotoSize.class.getName()).log(Level.SEVERE, null, ex);
                filename = null;
            } catch (NullPointerException ex) {
                Logger.getLogger(PhotoSize.class.getName()).log(Level.SEVERE, null, ex);
                filename = null;
            }
        }
    }


    @Override
    public String getSrc() {
        if (filename == null) {
            getSourceData();
        }
        return "https://vtt.tumblr.com/" + filename;
    }

    @Override
    public String getFileName() {
        if (src == null) {
            getSourceData();
        }
        return filename;
    }
    */

}
