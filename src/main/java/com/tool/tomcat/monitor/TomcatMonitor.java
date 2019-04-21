package com.tool.tomcat.monitor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tool.base.monitor.IMonitor;
import com.tool.base.utils.XmlUtils;
import com.tool.tomcat.monitor.model.TomcatModel;
import com.tool.tomcat.monitor.service.TomcatService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qiup
 * @description tomcat监控实现类
 * @date 2019/4/14
 */
@Component(value = "tomcatMonitor")
@Slf4j
public class TomcatMonitor implements IMonitor {

    @Autowired
    private TomcatService tomcatService;

    @Override
    public String getInfo() {
       List<TomcatModel> list=tomcatService.getAll();
       StringBuffer resultBuffer = new StringBuffer();
       JSONObject result=new JSONObject();
        list.forEach(m->{
            BufferedReader breader=null;
            try {
                String url_str = "http://" + m.getIp() + ":" + m.getPort() + "/manager/status?XML=true";
                String userPassword = m.getUser() + ":" + m.getPassword();
                String encoding = new BASE64Encoder().encode(userPassword.getBytes());
                URL url  = new URL(url_str);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Authorization", "Basic " + encoding);
                InputStream is = conn.getInputStream();
                breader = new BufferedReader(new InputStreamReader(is));
                String line ;
                while ((line = breader.readLine()) != null) {
                    resultBuffer.append(line);
                }
                breader.close();

              }catch (IOException e){
                if(breader!=null){
                    try {
                        breader.close();
                    }catch(IOException e1){
                        log.error("关闭读取TomCat数据输入流异常!",e1);
                    }
                }
                log.error("获取TomCat数据异常!",e);
            }
            ByteArrayInputStream inputStream=new ByteArrayInputStream(resultBuffer.toString().getBytes());
            XmlUtils xml=new XmlUtils(inputStream);
            JSONObject resultMap=new JSONObject();
            resultMap.put("address",m.getIp()+":"+m.getPort());
            List<Element> memList=xml.getDocument().selectNodes("/status/jvm/memory");
            Map<String,String> memMap=new HashMap<>();
            memList.forEach(m1->{//获取内存使用情况
                memMap.put("free",m1.attribute("free").getValue());
                memMap.put("total",m1.attribute("total").getValue());
                memMap.put("max",m1.attribute("max").getValue());
            });
            resultMap.put("memory",memMap);
            List<Element> jvmList=xml.getDocument().selectNodes("/status/jvm/memorypool");
            jvmList.forEach(j->{    //jvm使用情况
                Map<String,String> m1=new HashMap<>();
                m1.put("usageInit",j.attribute("usageInit").getValue());
                m1.put("usageCommitted",j.attribute("usageCommitted").getValue());
                m1.put("usageMax",j.attribute("usageMax").getValue());
                m1.put("usageUsed",j.attribute("usageUsed").getValue());
                resultMap.put(j.attribute("name").getValue().replace(" ",""),m1);
            });

            JSONArray threadInfo=new JSONArray();
            List<Element> thread=xml.getDocument().selectNodes("/status/connector/threadInfo");
            thread.forEach(c->{ //线程信息
                Map<String,String> m1=new HashMap<>();
                m1.put("maxThreads",c.attribute("maxThreads").getValue());
                m1.put("currentThreadCount",c.attribute("currentThreadCount").getValue());
                m1.put("currentThreadsBusy",c.attribute("currentThreadsBusy").getValue());
                String regEx="[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m2 = p.matcher(c.getParent().attribute("name").getValue().replace("\\D",""));
                m1.put("port",m2.replaceAll("").trim());
                threadInfo.add(m1);
            });
            JSONArray requestInfo=new JSONArray();
            List<Element> request=xml.getDocument().selectNodes("/status/connector/requestInfo");
            request.forEach(r->{ //连接信息


                Map<String,String> m1=new HashMap<>();
                m1.put("maxTime",r.attribute("maxTime").getValue());
                m1.put("processingTime",r.attribute("p" +
                        "rocessingTime").getValue());
                m1.put("requestCount",r.attribute("requestCount").getValue());
                m1.put("errorCount",r.attribute("errorCount").getValue());
                m1.put("bytesReceived",r.attribute("bytesReceived").getValue());
                m1.put("bytesSent",r.attribute("bytesSent").getValue());
                String regEx="[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m2 = p.matcher(r.getParent().attribute("name").getValue().replace("\\D",""));
                m1.put("port",m2.replaceAll("").trim());
                requestInfo.add(m1);
            });

            resultMap.put("threadInfo",threadInfo);
            resultMap.put("requestInfo",requestInfo);
            result.put(m.getId(),resultMap);
        });
        return result.toString();
    }

}
