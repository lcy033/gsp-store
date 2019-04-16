package com.gsp.app.web.controller;

import com.gsp.app.dao.GspRoleMenuDao;
import com.gsp.app.dao.MenuDao;
import com.gsp.app.dao.RoleDao;
import com.gsp.app.model.*;
import com.gsp.app.serives.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fengzhix.xu on 2018/5/20.
 */
@Controller
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private GspRoleMenuDao gspRoleMenuDao;
    @Autowired
    private MenuDao menuDao;


    @RequestMapping("/queryAll")
    public Object queryAllRole(GspRole pojo) {
        Map<String, Object> objMap = new HashMap<>();
        try {
            List<GspRole> gspRoles = roleDao.selectAllRole(pojo);
            objMap.put("total", gspRoles.size());
            objMap.put("rows", gspRoles);
            return objMap;
        } catch (Exception e) {
            log.error("role query all error", e);
        }
        return ResponseVo.ofErrorMessage();
    }

    /**
     * 启用
     */
    @RequestMapping(value = "/enable")
    @ResponseBody
    public Object enable(@RequestParam(value = "id") String id) {
        try {
            roleDao.updateEnabledById(Long.parseLong(id), Boolean.TRUE);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 禁用
     */
    @RequestMapping(value = "/stop")
    @ResponseBody
    public Object stop(@RequestParam(value = "id") String id) {
        try {
            roleDao.updateEnabledById(Long.parseLong(id), Boolean.FALSE);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 更新跳转
     */
    @RequestMapping(value = "/update/{id}")
    public String updateById(@PathVariable(value = "id") Long id, ModelMap map) {
        String url = "role/update";
        GspRole gspRole = roleDao.findRoleById(id);
        map.addAttribute("gspRole", gspRole);
        return url;
    }

    /**
     * 更新数据
     */
    @RequestMapping(value = "/updateGspRole")
    @ResponseBody
    public Object updateGspRole(GspRole role) {
        try {
            roleDao.updateRole(role);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 新增跳转
     */
    @RequestMapping("add")
    public String role() {
        return "role/add";
    }

    /**
     * 增加角色信息
     */
    @RequestMapping(value = "/addGspRole")
    @ResponseBody
    public Object addGspRole(GspRole role) {
        try {
            roleDao.addRole(role);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 查看权限跳转
     */
    @RequestMapping(value = "/roleMenu/{id}")
    public String roleMenu(@PathVariable(value = "id") Long id, ModelMap map) {
        String url = "role/roleMenu";
        GspRole gspRole = roleDao.findRoleById(id);
        map.addAttribute("gspRole", gspRole);
        return url;
    }

    /**
     * 查询角色对应菜单
     */
    @RequestMapping("/queryRoleMenu")
    public Object queryRoleMenu(GspRoleMenuVo pojo) {
        Map<String, Object> objMap = new HashMap<>();
        try {
            List<GspRoleMenuVo> gspRoleMenuVos = roleService.queryRoleMenu(pojo.getRoleId());
            objMap.put("total", gspRoleMenuVos.size());
            objMap.put("rows", gspRoleMenuVos);
            return objMap;
        } catch (Exception e) {
            log.error("query all error", e);
        }
        return ResponseVo.ofErrorMessage();
    }

    /**
     * 删除角色的菜单
     */
    @RequestMapping(value = "/delMenu")
    @ResponseBody
    public Object delMenu(@RequestParam(value = "id") Long id) {
        try {
            gspRoleMenuDao.delRoleMenuById(id);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 查询角色对应菜单下拉
     */
    @RequestMapping("/getMenuList")
    @ResponseBody
    public Object getMenuList() {
        try {
            return menuDao.selectAllMenu();
        } catch (Exception e) {
            log.error("query all error", e);
        }
        return ResponseVo.ofErrorMessage();
    }

    /**
     * 新增菜单关系
     */
    @RequestMapping(value = "/addRoleMenu")
    @ResponseBody
    public Object addRoleMenu(GspRoleMenu pojo) {
        try {
            gspRoleMenuDao.addRoleMenu(pojo);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }
}
