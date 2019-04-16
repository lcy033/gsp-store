package com.gsp.app.serives;

import com.gsp.app.dao.*;
import com.gsp.app.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理
 */
@Service
public class RoleService {

    @Autowired
    private GspRoleMenuDao gspRoleMenuDao;
    @Autowired
    private MenuDao menuDao;

    public List<GspRoleMenuVo> queryRoleMenu(Long roleId) {
        List<GspRoleMenuVo> list = new ArrayList<>();

        List<GspRoleMenu> gspRoleMenus = gspRoleMenuDao.findRoleMenuByRoleId(roleId);
        if (!gspRoleMenus.isEmpty()){
            for (GspRoleMenu gspRoleMenu : gspRoleMenus){
                GspRoleMenuVo gspRoleMenuVo = new GspRoleMenuVo();
                BeanUtils.copyProperties(gspRoleMenu, gspRoleMenuVo);

                GspMenu gspMenu = menuDao.findMenuById(gspRoleMenuVo.getMenuId());

                gspRoleMenuVo.setMenuName(gspMenu.getMenuName());
                gspRoleMenuVo.setMenuDesc(gspMenu.getMenuDesc());

                list.add(gspRoleMenuVo);
            }
        }
        return list;
    }
}
