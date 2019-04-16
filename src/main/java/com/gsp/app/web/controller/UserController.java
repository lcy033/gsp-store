package com.gsp.app.web.controller;

import com.gsp.app.dao.GspUserRoleDao;
import com.gsp.app.dao.RoleDao;
import com.gsp.app.dao.UserDao;
import com.gsp.app.model.*;
import com.gsp.app.serives.UserService;
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
 * fengzhix.xu on 2018/5/19.
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GspUserRoleDao gspUserRoleDao;
    @Autowired
    private RoleDao roleDao;


    @RequestMapping("/queryAll")
    public Object queryAllUser(GspUser pojo) {
        Map<String, Object> objMap = new HashMap<>();
        try {
            List<GspUser> gspUsers = userDao.selectAllUser(pojo);
            objMap.put("total", gspUsers.size());
            objMap.put("rows", gspUsers);
            return objMap;
        } catch (Exception e) {
            log.error("user query all error", e);
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
            userDao.updateEnabledById(Long.parseLong(id), Boolean.TRUE);
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
            userDao.updateEnabledById(Long.parseLong(id), Boolean.FALSE);
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
        String url = "user/update";
        GspUser gspUser = userDao.findUserById(id);
        map.addAttribute("gspUser", gspUser);
        return url;
    }

    /**
     * 更新数据
     */
    @RequestMapping(value = "/updateGspUser")
    @ResponseBody
    public Object updateGspUser(GspUser user) {
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 新增跳转
     */
    @RequestMapping("add")
    public String user() {
        return "user/add";
    }

    /**
     * 增加用户信息
     */
    @RequestMapping(value = "/addGspUser")
    @ResponseBody
    public Object addGspUser(GspUser user) {
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 查看权限跳转
     */
    @RequestMapping(value = "/userRole/{id}")
    public String userRole(@PathVariable(value = "id") Long id, ModelMap map) {
        String url = "user/userRole";
        GspUser gspUser = userDao.findUserById(id);
        map.addAttribute("gspUser", gspUser);
        return url;
    }

    /**
     * 查询用户对应角色
     */
    @RequestMapping("/queryUserRole")
    public Object queryUserRole(GspUserRoleVo pojo) {
        Map<String, Object> objMap = new HashMap<>();
        try {
            List<GspUserRoleVo> gspUserRoleVos = userService.queryUserRole(pojo.getUserId());
            objMap.put("total", gspUserRoleVos.size());
            objMap.put("rows", gspUserRoleVos);
            return objMap;
        } catch (Exception e) {
            log.error("query all error", e);
        }
        return ResponseVo.ofErrorMessage();
    }

    /**
     * 删除用户的角色
     */
    @RequestMapping(value = "/delRole")
    @ResponseBody
    public Object delRole(@RequestParam(value = "id") Long id) {
        try {
            gspUserRoleDao.delUserRoleById(id);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 查询用户对应角色下拉
     */
    @RequestMapping("/getRoleList")
    @ResponseBody
    public Object getRoleList() {
        try {
            return roleDao.selectAllRole(new GspRole());
        } catch (Exception e) {
            log.error("query all error", e);
        }
        return ResponseVo.ofErrorMessage();
    }

    /**
     * 新增角色关系
     */
    @RequestMapping(value = "/addUserRole")
    @ResponseBody
    public Object addUserRole(GspUserRole pojo) {
        try {
            gspUserRoleDao.addUserRole(pojo);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

}
