package com.gsp.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * fengzhix.xu on 2018/5/19.
 */
@Controller
@RequestMapping("/home/")
public class PageController {


    @RequestMapping("login")
    public String index() {
        return "login";
    }


    @RequestMapping("index")
    public String login() {
        return "main";
    }

    @RequestMapping("getUser")
    public String user() {
        return "user/get";
    }

    @RequestMapping("getRole")
    public String role() {
        return "role/get";
    }


}
