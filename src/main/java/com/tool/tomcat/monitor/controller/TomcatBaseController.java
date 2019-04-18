package com.tool.tomcat.monitor.controller;

import com.tool.tomcat.monitor.TomcatMonitor;
import com.tool.tomcat.monitor.model.TomcatModel;
import com.tool.tomcat.monitor.service.TomcatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiup
 * @description TomCat基础控制器
 * @date 2019/4/15
 */
@RestController
@Slf4j
public class TomcatBaseController {


    @Autowired
    private TomcatService tomcatService;

    @Autowired
    private TomcatMonitor tomcatMonitor;

    /**获取tomcat信息
     *
     * @return
     */
    @RequestMapping("/getTomcatInfo.do")
    public  String getInfo(){

        return tomcatMonitor.getInfo();
    }

    /**添加Tomcat
     *
     * @param model
     * @return
     */
    @RequestMapping("/addTomcat.do")
    public String addTomcat(@RequestBody TomcatModel model){
        try {
            tomcatService.add(model);
            return "succeed";
        }catch (Exception e){
            log.error("添加tomcat异常!",e);
            return "fail";
        }
    }

    @RequestMapping("/deleteTomcat.do")
    public String deleteTomcat(@RequestParam(name = "id") String id){
        try{
            tomcatService.delete(id);
            return "succeed";
        }catch (Exception e){
            log.error("删除tomcat异常!",e);
            return "fail";
        }
    }
}
