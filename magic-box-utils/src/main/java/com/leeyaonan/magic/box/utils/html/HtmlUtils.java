package com.leeyaonan.magic.box.utils.html;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class HtmlUtils {

    /**
     * 从html中提取图片地址-htmlparser
     * @param html
     * @return
     */
    public static List<String> extractImgFromHtml1(String html) {
        List<String> imageList = new ArrayList<>();
        try {
            // 创建一个html解释器
            Parser parser = new Parser();
            parser.setInputHTML(html.replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
            // 提取所有<img>标签的地址
            ImageTag it = null;
            NodeList imageTags = parser.parse(new NodeClassFilter(ImageTag.class));
            for (int i = 0, len = imageTags.size(); i < len; i++) {
                it = (ImageTag) imageTags.elementAt(i);
                String imageUrl = it.getImageURL();
                imageList.add(imageUrl);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return imageList;
    }

    /**
     * 从html中提取图片地址-Jsoup
     * @param content
     * @return
     */
    public static List<String> extractImgFromHtml2(String content) {
        Document doc = Jsoup.parse(content);
        Elements imgElements = doc.select("img");

        if (CollectionUtils.isEmpty(imgElements)) {
            return new ArrayList<>();
        }
        return imgElements.stream()
                .filter(v -> StringUtils.isNotBlank(v.attr("src")))
                .map(v -> v.attr("src"))
                .collect(Collectors.toList());
    }


}
