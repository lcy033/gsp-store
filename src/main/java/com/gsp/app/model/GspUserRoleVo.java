package com.gsp.app.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 用户、角色对应表Vo
 * Created by lcy on 2018/5/19.
 */
@Data
@ToString
public class GspUserRoleVo {
    private Long id;
    private Long userId;
    private Long roleId;
    private String roleName;
    private String roleDesc;
    private Date createTime;
    private Date updateTime;

}
