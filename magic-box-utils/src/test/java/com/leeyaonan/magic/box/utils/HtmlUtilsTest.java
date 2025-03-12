package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.html.HtmlUtils;
import org.junit.Test;

import java.util.List;

public class HtmlUtilsTest {

    @Test
    public void testExtractImgFromHtml1() {
        String html = "<div id=\"offer-template-0\"></div><div id=\"offer-template-0\">&nbsp;</div>\r\n<div style=\"width: 790.0px;\"><img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN01aPdlbp1WNuuKN4bYd_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_0\"/> <img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN015oldZo1WNuuJ4qraQ_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_1\"/> <img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN01fLQqSI1WNuuKwlUU1_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_2\"/> <img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN01OKdtaC1WNuuIGawqb_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_3\"/> <img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN01SwTykb1WNuuK3IirL_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_4\"/> <img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN01pNmkhw1WNuuK3IvLn_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_5\"/> <img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN01JFlqFk1WNuuJ4nVc0_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_6\"/> <img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN01Xq1HhB1WNuuLrIvL7_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_7\"/> <img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN01Nr9XPI1WNuuK3OpR1_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_8\"/> <img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN015ZI3ib1WNuuKINpYu_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_9\"/> <img style=\"display: block;width: 100.0%;height: auto;\" src=\"https://cbu01.alicdn.com/img/ibank/O1CN01MBkHax1WNuuLtQuha_!!2212837412777-0-cib.jpg\" usemap=\"#_sdmap_10\"/></div>";
        List<String> imgUrls1 = HtmlUtils.extractImgFromHtml1(html);
        List<String> imgUrls2 = HtmlUtils.extractImgFromHtml2(html);
        System.out.println(imgUrls1);
        System.out.println(imgUrls2);
    }
}
