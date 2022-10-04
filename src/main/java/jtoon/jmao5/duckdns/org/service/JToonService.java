package jtoon.jmao5.duckdns.org.service;

import jtoon.jmao5.duckdns.org.common.util.CommonUtils;
import jtoon.jmao5.duckdns.org.domain.jposts.JPosts;
import jtoon.jmao5.duckdns.org.repository.jposts.JPostsRepository;
import jtoon.jmao5.duckdns.org.response.jposts.JPostsInfoResponse;
import jtoon.jmao5.duckdns.org.response.jposts.JToonListPageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
                .map(r -> JPosts.of(r, day))
                .collect(Collectors.toList());

        for (JPosts jPost : jPosts) {
            if(!jPostsRepository.getListExist(jPost)){
                jPostsRepository.save(jPost);
            }
        }
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

    @Transactional
    public Map<String, Object> jtoonList(String href) {
        Map<String, Object> result = new HashMap<>();

        Document document = CommonUtils.getCrawling(href);

        List<Map<String, String>> resultList = new ArrayList<>();

        Elements titleElements = document.select("div.comicinfo");
        Elements elements = document.select("tbody tr:not([class])");

        Long titleId = Long.valueOf(CommonUtils.getQueryMap(titleElements.select("a").first().attr("href")).get("titleId"));
//        log.info("titleId : "+ titleId);
        String infoImg = titleElements.select("div.thumb a img").attr("abs:src");
        String infoWrtNm = titleElements.select("span.wrt_nm").text();
        int totalCnt = Integer.parseInt(CommonUtils.getQueryMap(elements.select("a").first().attr("href")).get("no"));
        JPosts jPostsUpdate = jPostsRepository.findByTitleId(titleId);

        jPostsUpdate.update(infoImg, infoWrtNm, totalCnt);

//        Map<String, String> infoMap = new HashMap<>();
//        infoMap.put("infoImg", infoImg);
//        infoMap.put("infoTitle", titleElements.select("span.title").text());
//        infoMap.put("infoWrtNm", infoWrtNm);
//            log.info("infoMap : " + infoMap);
        result.put("info", jPostsUpdate);



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

        result.put("detailList", resultList);

        return result;
    }

    public List<Map<String, Object>> jtoonListPage(String href, String pageNo) {
//        String href = jToonListPageRequest.getHref();
//        log.info("href : "+ href);
        Document document = CommonUtils.getCrawling(href +"&page="+pageNo);

        List<Map<String, Object>> resultList = new ArrayList<>();

        Elements elements = document.select("tbody tr:not([class])");

        for (Element element : elements) {
//                log.info(String.valueOf(element));
            Map<String, Object> map = new HashMap<>();
            Elements aElement = element.select("td.title");
            String date = element.select("td.num").text();

            map.put("href", aElement.select("a").attr("abs:href"));
            map.put("title", aElement.select("a").text());
            map.put("src", element.select("img").attr("abs:src"));
            map.put("date", date);
            resultList.add(map);
        }
        return resultList;
    }
}
