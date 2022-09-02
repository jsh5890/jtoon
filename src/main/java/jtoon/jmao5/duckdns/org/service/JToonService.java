package jtoon.jmao5.duckdns.org.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class JToonService {

    public List<Map<String, String>> weekdayList(String day) {
        String Url = "https://comic.naver.com/webtoon/weekdayList?week=" + day;
        Connection conn = Jsoup.connect(Url);

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        try {
            Document document = conn.get();
            Elements elements = document.getElementsByClass("img_list");
            Elements titleElements = elements.select("li");

            for (Element element : titleElements) {
                Map<String, String> map = new HashMap<String, String>();
                Elements aElement = element.select("div.thumb a");
                String writer = element.select(".desc a").text();

                map.put("href", aElement.attr("abs:href"));
                map.put("title", aElement.attr("abs:title").replace("https://comic.naver.com/webtoon/",""));
                map.put("src", aElement.select("img").attr("abs:src"));
                map.put("writer", writer);
                result.add(map);
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
//        log.info(String.valueOf(result));
        return result;
    }

    public List<Map<String, String>> list(String href) {

        String Url = href;
        Connection conn = Jsoup.connect(Url);

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        try {
            Document document = conn.get();
            log.info("오리진 : " + document);
            Elements elements = document.select("tr");

            for (Element element : elements) {
                log.info(String.valueOf(element));
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
//        log.info(String.valueOf(result));
        return result;
    }
}
