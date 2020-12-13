package com.duanzm.test.web;

import com.duanzm.entity.User;
import com.duanzm.common.utils.UtilsTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminTest {

    @Autowired
    private UtilsTest utilsTest;

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        utilsTest.test();
        return "admin";
    }

    @ResponseBody
    @RequestMapping("/test2")
    public String test2(){
        return "utils";
    }

    @GetMapping("/index")
    public String index(ModelMap modelMap){
        User user = new User("小红",19);
        modelMap.put("user", user);
        modelMap.put("a", 1);
        return "index";
    }
}
