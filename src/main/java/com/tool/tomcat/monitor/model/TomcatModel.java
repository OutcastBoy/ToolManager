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
public class TomcatModel {

    private String id;

    private String user;


    private String password;

    private String ip;

    private String port;
}
