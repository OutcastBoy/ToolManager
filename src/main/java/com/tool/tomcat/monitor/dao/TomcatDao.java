package com.tool.tomcat.monitor.dao;

import com.tool.tomcat.monitor.model.TomcatModel;
import org.apache.ibatis.annotations.Delete;
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
public interface TomcatDao {

    @Select("SELECT * FROM T_TOMCAT")
    List<TomcatModel> getAll();

    @Insert("INSERT INTO T_TOMCAT(USER,PASSWORD,IP,PORT) VALUES(#{user},#{password},#{ip},#{port})")
    void add(TomcatModel model);

    @Delete("DELETE FROM T_TOMCAT WHERE ID=#{id}")
    void delete(String id);
}
