/*
 * Copyright 2017 ahage.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tumblr.jumblr.types;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ahage
 */
public abstract class SourceInterface {
       
    public abstract String getSrc();
    public abstract String getFileName();

    public static List<SourceInterface> parseFigures(String html) {

        List<SourceInterface> retVal = new ArrayList<SourceInterface>();
        
        Document doc = Jsoup.parse(html);
        for (Element fig : doc.getElementsByTag("figure")) {
            // Fetch all images
            for (Element img : fig.getElementsByTag("img")) {
                String url = img.attr("src");
                Integer height = Integer.valueOf(img.attr("data-orig-height"));
                Integer width = Integer.valueOf(img.attr("data-orig-width"));
                retVal.add(new PhotoSize(url, height, width));
            }
            // Fetch all videos
            Elements videos = fig.getElementsByTag("video");
            if (!videos.isEmpty()) {
                Integer width = Integer.valueOf(fig.attr("data-orig-width"));
                String embed_code = videos.first().html();
                retVal.add(new Video(embed_code, width));
            }
        }
        
        return retVal;
    }
}
