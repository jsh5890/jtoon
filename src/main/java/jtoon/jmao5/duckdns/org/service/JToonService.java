package jtoon.jmao5.duckdns.org.service;

import jtoon.jmao5.duckdns.org.common.util.CommonUtils;
import jtoon.jmao5.duckdns.org.domain.jtoon.JToon;
import jtoon.jmao5.duckdns.org.domain.jtoonlist.JToonList;
import jtoon.jmao5.duckdns.org.repository.jtoon.JToonRepository;
import jtoon.jmao5.duckdns.org.repository.jtoonlist.JToonListRepository;
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

    private final JToonRepository jToonRepository;

    private final JToonListRepository jToonListRepository;

    @Transactional
    public List<JToon> weekdayList(String day) {
        Document document = CommonUtils.getCrawling("https://comic.naver.com/webtoon/weekdayList?week=" + day);
        List<JToon> jToons = document.getElementsByClass("img_list").select("li").stream()
                .map(r -> JToon.of(r, day))
                .collect(Collectors.toList());

        for (JToon jToon : jToons) {
            if(!jToonRepository.getListExist(jToon)){
                jToonRepository.save(jToon);
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
        return jToons;
    }

    @Transactional
    public Map<String, Object> jtoonList(String href) {
        Map<String, Object> result = new HashMap<>();

        Document document = CommonUtils.getCrawling(href);

        List<Map<String, String>> resultList = new ArrayList<>();

        Elements titleElements = document.select("div.comicinfo");
        Elements elements = document.select("tbody tr:not([class])");
//        log.info("elements : " + elements);
        Long titleId = Long.valueOf(CommonUtils.getQueryMap(titleElements.select("a").first().attr("href")).get("titleId"));
        String dayOfWeek = String.valueOf(CommonUtils.getQueryMap(elements.select("a").first().attr("href")).get("weekday"));
//        log.info("dayOfWeek : "+ dayOfWeek);
//        JPosts jPostsUpdate = jPostsRepository.findByTitleId(titleId);
        JToon jToonUpdate = jToonRepository.findByTitleId2(titleId, dayOfWeek);

        String infoImg = titleElements.select("div.thumb a img").attr("abs:src");
        String infoWrtNm = titleElements.select("span.wrt_nm").text();
        int totalCnt = Integer.parseInt(CommonUtils.getQueryMap(elements.select("a").first().attr("href")).get("no"));
        jToonUpdate.update(infoImg, infoWrtNm, totalCnt);

//        Map<String, String> infoMap = new HashMap<>();
//        infoMap.put("infoImg", infoImg);
//        infoMap.put("infoTitle", titleElements.select("span.title").text());
//        infoMap.put("infoWrtNm", infoWrtNm);
//            log.info("infoMap : " + infoMap);


        List<JToonList> jToonLists = elements.stream()
                .map(JToonList::of)
                .collect(Collectors.toList());

        for (JToonList jToonList : jToonLists) {
//            if(!jToonRepository.getListExist(jToonList)){
            jToonUpdate.add(jToonList);
            jToonListRepository.save(jToonList);
//            }
        }
//        resultList.add((Map<String, String>) jToonLists);
//        for (Element element : elements) {
////                log.info(String.valueOf(element));
//            Map<String, String> map = new HashMap<>();
//            Elements aElement = element.select("td.title");
//            String date = element.select("td.num").text();
//
//            map.put("href", aElement.select("a").attr("abs:href"));
//            map.put("title", aElement.select("a").text());
//            map.put("src", element.select("img").attr("abs:src"));
//            map.put("date", date);
//            resultList.add(map);
//        }
        result.put("info", jToonUpdate);
        result.put("detailList", jToonLists);

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
