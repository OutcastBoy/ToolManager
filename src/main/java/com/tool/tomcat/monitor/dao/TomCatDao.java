package com.tool.tomcat.monitor.dao;

import com.tool.tomcat.monitor.model.TomCatModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qiup
 * @description TomCat的Dao层
 * @date 2019/4/15
 */
@Mapper
@Component(value = "tomCatDao")
public interface TomCatDao {

    @Select("SELECT * FROM T_TOMCAT")
    List<TomCatModel> getAll();

    @Insert("INSERT INTO T_TOMCAT(USER,PASSWORD,IP) VALUES(#{user},#{password},#{ip})")
    void add(TomCatModel model);
}
