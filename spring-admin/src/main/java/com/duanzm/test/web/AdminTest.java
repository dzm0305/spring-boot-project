package com.duanzm.test.web;

import com.duanzm.common.utils.UtilsTest;
import com.duanzm.entity.User;
import com.duanzm.test.service.UserServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminTest {

    @Autowired
    private UtilsTest utilsTest;

    @Autowired
    private UserServiceTest userServiceTest;

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
        User user = new User();
        user.setName("小红");
        user.setAge(10);
        modelMap.put("user", user);
        modelMap.put("a", 1);
        return "index";
    }

    @ResponseBody
    @RequestMapping("/user")
    public List<User> queryUserList(){
        List<User> list = userServiceTest.queryUserList();
        List<User> list2 = userServiceTest.queryUserList2();
        System.out.println(list2);
        return list;
    }
}
