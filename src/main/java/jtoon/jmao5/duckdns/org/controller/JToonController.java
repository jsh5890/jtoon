package jtoon.jmao5.duckdns.org.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JToonController {

    @GetMapping("/")
    public String hello(){
        return "ㅎㅇ";
    }
}
