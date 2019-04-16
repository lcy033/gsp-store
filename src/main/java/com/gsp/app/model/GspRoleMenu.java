package com.gsp.app.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 角色、菜单对应表
 * Created by lcy on 2018/5/19.
 */
@Data
@ToString
public class GspRoleMenu {
    private Long id;
    private Long roleId;
    private Long menuId;
    private Date createTime;
    private Date updateTime;

}
