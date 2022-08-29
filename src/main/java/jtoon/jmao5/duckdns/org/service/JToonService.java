package jtoon.jmao5.duckdns.org.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class JToonService {

    public List<Map<String, String>> weekdayList(){
        String Url = "https://comic.naver.com/webtoon/weekdayList?week=mon";
        Connection conn = Jsoup.connect(Url);

        try {
            Document document = conn.get();
            Elements elements = document.getElementsByClass("img_list");
//            System.out.println("elements : " + elements);
            Elements titleElements = elements.select("li");
            for (Element element : titleElements) {
                Elements linkElements = element.select("div.thumb a");

                log.info("linkElements : " + linkElements);
                log.info("href : " + linkElements.attr("abs:href"));
                log.info("title : " + linkElements.attr("abs:title"));
                log.info("src : " + linkElements.attr("abs:src"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
