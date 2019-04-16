package com.tool;

import com.tool.tomcat.monitor.model.TomCatModel;
import com.tool.tomcat.monitor.service.TomCatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolMangaerApplicationTests {

    @Autowired
    private TomCatService service;
    @Test
    public void contextLoads() {
       TomCatModel t=new TomCatModel();
       t.setUser("user");
       t.setPassword("user");
       t.setIp("123");
        service.add(t);

    }

}
