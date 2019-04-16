package com.gsp.app.dao;


import com.gsp.app.model.GspRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色、菜单对应表
 */

public interface GspRoleMenuDao extends BaseDao {

    List<GspRoleMenu> findRoleMenuByRoleId(@Param("roleId") Long roleId);

    void addRoleMenu(GspRoleMenu gspUserRole);

    void delRoleMenuById(@Param("id") Long id);
}
