package jtoon.jmao5.duckdns.org.controller;

import jtoon.jmao5.duckdns.org.domain.jposts.JPosts;
import jtoon.jmao5.duckdns.org.response.jposts.JToonListPageRequest;
import jtoon.jmao5.duckdns.org.service.JToonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JToonController {

    private final JToonService jToonService;

    @GetMapping("/jtoon/weekdayList/{day}")
    public List<JPosts> weekdayList(@PathVariable String day) {
        return jToonService.weekdayList(day);
    }

    @GetMapping("jtoon/list")
    public Map<String, Object> jtoonList(@RequestParam("href") String href) {
        return jToonService.jtoonList(href);
    }

    @GetMapping("jtoon/listpage")
    public List<Map<String, Object>> jtoonListPage(@RequestParam("href") String href
            , @RequestParam("pageNo") String pageNo) {
        return jToonService.jtoonListPage(href, pageNo);
    }
}
