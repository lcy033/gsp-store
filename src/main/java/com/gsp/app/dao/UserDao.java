package com.gsp.app.dao;


import com.gsp.app.model.GspUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户
 */
public interface UserDao extends BaseDao {

    List<GspUser> selectAllUser(@Param("pojo") GspUser pojo);

    GspUser findUserById(@Param("id") Long id);

    void addUser(GspUser gspUser);

    void updateUser(GspUser gspUser);

    void updateEnabledById(@Param("id")long id, @Param("enable")boolean enable);

    GspUser findUserByName(@Param("user") String userName);
}
