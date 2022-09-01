package jtoon.jmao5.duckdns.org.controller;

import jtoon.jmao5.duckdns.org.service.JToonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JToonController {

    private final JToonService jToonService;

    @GetMapping("/jtoon/weekdayList/{day}")
    public List<Map<String, String>> weekdayList(@PathVariable String day){
//        log.info("jtoon day : " + day);
        return jToonService.weekdayList(day);
    }

    @GetMapping("jtoon/list")
    public List<Map<String, String>> list(@RequestParam("href") String href){
        return jToonService.list(href);
    }
}
