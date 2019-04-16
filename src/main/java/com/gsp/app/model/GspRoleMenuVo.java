package com.gsp.app.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 角色、菜单对应表Vo
 * Created by lcy on 2018/5/30.
 */
@Data
@ToString
public class GspRoleMenuVo {
    private Long id;
    private Long roleId;
    private Long menuId;
    private String menuName;
    private String menuDesc;
    private Date createTime;
    private Date updateTime;

}
