package com.duanzm.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common")
public class CommonTest {

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        return "common";
    }
}
