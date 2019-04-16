package com.tool.tomcat.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiup
 * @description TomCat基础控制器
 * @date 2019/4/15
 */
@RestController
public class TomCatBaseController {
    @RequestMapping("/hello")
    public  String helloWord(){
        return "HELLO WORD";
    }
}
