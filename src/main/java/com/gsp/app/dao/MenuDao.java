package com.gsp.app.dao;


import com.gsp.app.model.GspMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单
 */

public interface MenuDao extends BaseDao {

    List<GspMenu> selectMenuById(@Param("id") String userId);

    List<GspMenu> selectAllMenu();

    GspMenu findMenuById(@Param("id") Long id);

}
