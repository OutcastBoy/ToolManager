package com.tool.base.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * @author qiup
 * @description xml解析工具类
 * @date 2019/4/17
 */
@Slf4j
@Getter
public class XmlUtils {

    private Document document=null;

    public XmlUtils(InputStream input){
        try{
            SAXReader reader=new SAXReader();
            document=reader.read(input);
            log.debug("加载XML文件成功!");
        }catch (Exception e){
            log.error("加载XML文件异常!",e);
        }
    }
    public  void dom4jList(Element element, Map<String,String> result) {
        //获取文件中父元素的名称和文本内容

    //    result.put(element.element("name"), element.getTextTrim());
        //创建迭代器对象判断该父元素是否还有子元素，有的话，就获取子元素的名称和文本内容
        Iterator iterator = element.elementIterator();

        while (iterator.hasNext()) {
            Element e = (Element) iterator.next();
            //递归调用自身方法判断该子元素是否还存在子元素，以此类推并获取信息
         //   log.info(e.toString());
            dom4jList(e, result);
        }

    }
}
