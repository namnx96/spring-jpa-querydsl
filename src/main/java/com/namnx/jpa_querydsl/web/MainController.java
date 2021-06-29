package com.namnx.jpa_querydsl.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("")
    public String redirectSwagger() {
        return "redirect:swagger-ui.html";
    }
}
