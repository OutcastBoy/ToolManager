package com.tool.tomcat.monitor.model;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import lombok.ToString;

/**
 * @author qiup
 * @description TomCat实体类
 * @date 2019/4/15
 */
@Data
@ToString
@Table(name="T_TOMCAT")
public class TomCatModel extends BaseModel {

    @Column(name = "ID",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private String id;

    @Column(name = "USER",type = MySqlTypeConstant.VARCHAR,length = 32)
    private String user;

    @Column(name = "PASSWORD",type = MySqlTypeConstant.VARCHAR,length = 32)
    private String password;

    @Column(name = "IP",type = MySqlTypeConstant.VARCHAR,length = 32)
    private String ip;

}
