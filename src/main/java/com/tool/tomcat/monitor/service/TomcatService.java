package com.tool.tomcat.monitor.service;

import com.tool.tomcat.monitor.dao.TomcatDao;
import com.tool.tomcat.monitor.model.TomcatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * @author qiup
 * @description TomCat任务服务层
 * @date 2019/4/16
 */
@Service
public class TomcatService {

    @Autowired
    private TomcatDao tomcatDao;

    /**查询所有tomcat信息
     *
     * @return
     */
    public List<TomcatModel> getAll(){
        List<TomcatModel> list =tomcatDao.getAll();
        return list;
    }

    /**添加tomcat
     *
     * @param model tomcat实体类
     */
    @Transactional
    public  void add(TomcatModel model){
        tomcatDao.add(model);
    }

    /**删除tomcat
     *
     * @param id 待删除tomcat对用ID
     */
    @Transactional
    public void delete(String id){tomcatDao.delete(id);}
}
