package com.duanzm.springbootproject.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloWorld {

    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        return "hello, world!";
    }
}
