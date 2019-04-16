package com.gsp.app.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 用户、角色对应表
 * Created by lcy on 2018/5/19.
 */
@Data
@ToString
public class GspUserRole {
    private Long id;
    private Long userId;
    private Long roleId;
    private Date createTime;
    private Date updateTime;

}
