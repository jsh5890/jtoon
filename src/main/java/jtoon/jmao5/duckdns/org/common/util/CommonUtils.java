package jtoon.jmao5.duckdns.org.common.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CommonUtils {

    public static Map<String, String> getQueryMap(String query) {

//        log.info(" ? : " + query.substring(query.indexOf("?")+1));
        String[] params = query.substring(query.indexOf("?") + 1).split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String[] p = param.split("=");
            String name = p[0];
            if (p.length > 1) {
                String value = p[1];
                map.put(name, value);
            }
        }
        return map;
    }

    public static Document getCrawling(String url) {
        try {
            return Jsoup.connect(url)
                    .timeout(2000)
                    .get();
        } catch (IOException e) {
            throw new RuntimeException("crawling 실패");
        }
    }
}
