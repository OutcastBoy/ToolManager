package com.tool;

import com.tool.tomcat.monitor.model.TomcatModel;
import com.tool.tomcat.monitor.service.TomcatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolMangaerApplicationTests {

    @Autowired
    private TomcatService service;
    @Test
    public void contextLoads() {
       TomcatModel t=new TomcatModel();
       t.setUser("user");
       t.setPassword("user");
       t.setIp("123");
        service.add(t);

    }

}
