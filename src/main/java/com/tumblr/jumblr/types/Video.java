package com.tumblr.jumblr.types;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * An individual Video in a VideoPost
 * @author jc
 */
public class Video extends SourceInterface {

    private Integer width;
    private String embed_code;
    String src;
    String type;
    String filename;

    /**
     * Get the width of this video
     * @return width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Get the embed code for this video
     * @return embed code
     */
    public String getEmbedCode() {
        return embed_code;
    }

    
    private void getSourceData() {
        if (getEmbedCode() != null) {
            Document doc = Jsoup.parse(getEmbedCode());
            URL urlObj;
            try {
                src = doc.getElementsByAttribute("src").first().attr("src");
                type =  doc.getElementsByAttribute("type").first().attr("type");

                urlObj = new URL(src);
                filename = FilenameUtils.getName(urlObj.getPath());
                // If not contains tumblr then it's not a correct filename
                if (!filename.contains("tumblr")) {
                    String tmpSrc = src.substring(0, src.lastIndexOf("/"));
                    urlObj = new URL(tmpSrc);
                    filename = FilenameUtils.getName(urlObj.getPath());
                }
                filename += "." + type.substring(type.lastIndexOf("/") + 1);
                
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

}
