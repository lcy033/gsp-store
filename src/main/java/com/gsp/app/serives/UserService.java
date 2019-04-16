package com.gsp.app.serives;

import com.gsp.app.dao.GspUserRoleDao;
import com.gsp.app.dao.RoleDao;
import com.gsp.app.model.GspRole;
import com.gsp.app.model.GspUserRole;
import com.gsp.app.model.GspUserRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 * fengzhix.xu on 2018/5/20.
 */
@Service
public class UserService {

    @Autowired
    private GspUserRoleDao gspUserRoleDao;
    @Autowired
    private RoleDao roleDao;

    public List<GspUserRoleVo> queryUserRole(Long userId) {
        List<GspUserRoleVo> list = new ArrayList<>();

        List<GspUserRole> gspUserRoles = gspUserRoleDao.findUserRoleByUserId(userId);
        if (!gspUserRoles.isEmpty()){
            for (GspUserRole gspUserRole : gspUserRoles){
                GspUserRoleVo gspUserRoleVo = new GspUserRoleVo();
                BeanUtils.copyProperties(gspUserRole, gspUserRoleVo);

                GspRole gspRole = roleDao.findRoleById(gspUserRoleVo.getRoleId());

                gspUserRoleVo.setRoleName(gspRole.getRoleName());
                gspUserRoleVo.setRoleDesc(gspRole.getRoleDesc());

                list.add(gspUserRoleVo);
            }
        }
        return list;
    }
}
