package com.duanzm.study.vue.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class StudyController {

    @RequestMapping("/index")
    public String index(){
        return "vue/1";
    }

}
