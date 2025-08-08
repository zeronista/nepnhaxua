package com.dev.thucduong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

    // 1-level path (bỏ qua api/actuator/static/public/assets/webjars)
    @GetMapping("/{path:[^\\.]*}")
    public String forwardLevel1() {
        return "forward:/index.html";
    }

    // 2-level path
    @GetMapping("/{dir:[^\\.]*}/{path:[^\\.]*}")
    public String forwardLevel2() {
        return "forward:/index.html";
    }
}


