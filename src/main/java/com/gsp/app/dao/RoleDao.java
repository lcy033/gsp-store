package com.gsp.app.dao;


import com.gsp.app.model.GspRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色
 */

public interface RoleDao extends BaseDao {

    List<GspRole> selectAllRole(@Param("pojo") GspRole pojo);

    GspRole findRoleById(@Param("id") Long id);

    void addRole(GspRole gspRole);

    void updateRole(GspRole gspRole);

    void updateEnabledById(@Param("id")long id, @Param("enable")boolean enable);

}
