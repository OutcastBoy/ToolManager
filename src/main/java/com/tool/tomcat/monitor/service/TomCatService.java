package com.tool.tomcat.monitor.service;

import com.tool.tomcat.monitor.dao.TomCatDao;
import com.tool.tomcat.monitor.model.TomCatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiup
 * @description TomCat任务服务层
 * @date 2019/4/16
 */
@Service
public class TomCatService {

    @Autowired
    private TomCatDao tomCatDao;

    public List<TomCatModel> getAll(){
        List<TomCatModel> list =tomCatDao.getAll();
        return list;
    }

    public  void add(TomCatModel model){
        tomCatDao.add(model);
    }
}
