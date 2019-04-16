package com.gsp.app.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 角色表
 * Created by lcy on 2018/5/19.
 */
@Data
@ToString
public class GspRole {
    private Long id;
    private String roleName;
    private String roleType;
    private String roleDesc;
    private Boolean enable;
    private Date createTime;
    private Date updateTime;

}
