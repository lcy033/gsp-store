package com.gsp.app.dao;


import com.gsp.app.model.GspUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户、角色对应表
 */

public interface GspUserRoleDao extends BaseDao {

    List<GspUserRole> findUserRoleByUserId(@Param("userId") Long userId);

    void addUserRole(GspUserRole gspUserRole);

    void delUserRoleById(@Param("id") Long id);

}
