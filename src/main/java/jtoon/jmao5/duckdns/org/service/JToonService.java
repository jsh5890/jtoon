package jtoon.jmao5.duckdns.org.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class JToonService {

    public List<Map<String, String>> weekdayList(String day) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        try {
            String Url = "https://comic.naver.com/webtoon/weekdayList?week=" + day;
            Connection conn = Jsoup.connect(Url);


            Document document = conn.get();
            Elements elements = document.getElementsByClass("img_list");
            Elements titleElements = elements.select("li");

            for (Element element : titleElements) {
                Map<String, String> map = new HashMap<>();
                Elements aElement = element.select("div.thumb a");
                String writer = element.select(".desc a").text();

                map.put("href", aElement.attr("abs:href"));
                map.put("title", aElement.attr("abs:title").replace("https://comic.naver.com/webtoon/", ""));
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

    public Model list(String href) {

        Model model = new ConcurrentModel();
        try {
            String Url = href;
            Connection conn = Jsoup.connect(Url);

            List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

            Document document = conn.get();
//            log.info("오리진 : " + document);
            Elements titleElements = document.select("div.comicinfo");
            Map<String, String> infoMap = new HashMap<>();
            infoMap.put("infoImg", titleElements.select("div.thumb a img").attr("abs:src"));
            infoMap.put("infoTitle", titleElements.select("span.title").text());
            infoMap.put("infoWrtNm", titleElements.select("span.wrt_nm").text());
//            log.info("infoMap : " + infoMap);
            model.addAttribute("info", infoMap);

            Elements elements = document.select("tbody tr:not([class])");

            for (Element element : elements) {
//                log.info(String.valueOf(element));
                Map<String, String> map = new HashMap<>();
                Elements aElement = element.select("td.title");
                String date = element.select("td.num").text();

                map.put("href", aElement.select("a").attr("abs:href"));
                map.put("title", aElement.select("a").text());
                map.put("src", element.select("img").attr("abs:src"));
                map.put("date", date);
                resultList.add(map);
            }
            model.addAttribute("detailList", resultList);

        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
//        log.info(String.valueOf(model));
        return model;
    }
}
