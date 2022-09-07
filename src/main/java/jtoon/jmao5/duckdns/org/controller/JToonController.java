package jtoon.jmao5.duckdns.org.controller;

import jtoon.jmao5.duckdns.org.domain.jposts.JPosts;
import jtoon.jmao5.duckdns.org.service.JToonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Map<String, Object> list(@RequestParam("href") String href) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, String>> model = (List<Map<String, String>>) jToonService.list(href).getAttribute("detailList");
        Map<String, String> result = (Map<String, String>) jToonService.list(href).getAttribute("info");

        map.put("infoData", result);
        map.put("detailList", model);

        return map;
    }
}
