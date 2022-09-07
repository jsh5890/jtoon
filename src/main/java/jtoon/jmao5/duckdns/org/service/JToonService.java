package jtoon.jmao5.duckdns.org.service;

import jtoon.jmao5.duckdns.org.common.util.CommonUtils;
import jtoon.jmao5.duckdns.org.domain.jposts.JPosts;
import jtoon.jmao5.duckdns.org.repository.jposts.JPostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JToonService {

    private final JPostsRepository jPostsRepository;

    @Transactional
    public List<JPosts> weekdayList(String day) {
        Document document = CommonUtils.getCrawling("https://comic.naver.com/webtoon/weekdayList?week=" + day);
        List<JPosts> jPosts = document.getElementsByClass("img_list").select("li").stream()
                .map(JPosts::of)
                .collect(Collectors.toList());

        jPostsRepository.saveAll(jPosts);

//        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
//        Elements elements = document.getElementsByClass("img_list").select("li");
//        for (Element element : elements) {
//            Map<String, String> map = new HashMap<>();
//            Elements aElement = element.select("div.thumb a");
//
//            map.put("href", element.select("dt a").attr("abs:href"));
//            map.put("title", element.select("dt a").text());
//            map.put("src", element.select("img").attr("abs:src"));
//            map.put("writer", element.select(".desc a").text());
//            result.add(map);
//        }

        return jPosts;
    }

    public Model list(String href) {

        Model model = new ConcurrentModel();
        try {
            log.info("href : " + href);
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

            Map queryStringMap = CommonUtils.getQueryMap(elements.select("a").first().attr("abs:href"));
            int no = Integer.parseInt((String) queryStringMap.get("no")) / 10 + 1;
//            for (int i = 2; i <= no; i++) {
//                String subUrl = href + "&page=" + i;
//                Connection subConn = Jsoup.connect(subUrl);
//                Document subDocument = subConn.get();
//                Elements subElements = subDocument.select("tbody tr:not([class])");
//
//                for (Element element : subElements) {
//                    Map<String, String> map = new HashMap<>();
//                    Elements aElement = element.select("td.title");
//                    String date = element.select("td.num").text();
//
//                    map.put("href", aElement.select("a").attr("abs:href"));
//                    map.put("title", aElement.select("a").text());
//                    map.put("src", element.select("img").attr("abs:src"));
//                    map.put("date", date);
//                    resultList.add(map);
//                }
//            }

            model.addAttribute("detailList", resultList);

        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
        log.info(String.valueOf(model));
        return model;
    }
}
