package com.cosmos.mvc.controller;

import com.cosmos.mvc.annotation.Autowire;
import com.cosmos.mvc.annotation.Controller;
import com.cosmos.mvc.annotation.RequestMapping;
import com.cosmos.mvc.annotation.RequestParam;
import com.cosmos.mvc.service.TestService;
import com.cosmos.mvc.service.TestServiceImpl;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-19 14:29
 * @Modified By：
 */
@Controller
public class TestController {
    @Autowire
    private TestService testService;


    @RequestMapping("/")
    public String index() {
        return "欢迎来到首页！";
    }

    @RequestMapping("/add")
    public void add(){

    }

    @RequestMapping("/test")
    public String test() {
        return testService.show();
    }

    @RequestMapping("/test2")
    public String test2(@RequestParam("name") String userName) {
        return "hello!" + userName;
    }

    @RequestMapping("/test3")
    public String test3(@RequestParam("name") String userName) {
        return "hello!" + userName;
    }
}
