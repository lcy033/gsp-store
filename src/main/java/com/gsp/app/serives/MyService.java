package com.gsp.app.serives;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.gsp.app.constant.MenuEnum;
import com.gsp.app.dao.MenuDao;
import com.gsp.app.dao.UserDao;
import com.gsp.app.model.GspMenu;
import com.gsp.app.model.GspUser;
import com.gsp.app.model.User;
import com.gsp.app.vo.GspMenuVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

/**
 * Created by finup on 2018/5/19.
 */
@Service
public class MyService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private UserDao userDao;


    public GspUser doLogin(GspUser user) {
       return userDao.findUserByName(user.getUser());
    }

    /**
     * 菜单
     * @param userId
     * @return
     */
    public List<GspMenuVo> getMenuById(String userId) {
        Multimap<MenuEnum,GspMenuVo> multimap = HashMultimap.create();

        List<GspMenu> gspMenu = menuDao.selectMenuById(userId);
        if (CollectionUtils.isNotEmpty(gspMenu)) {
            gspMenu.forEach(menu -> {
                int parentId = menu.getMenuFatherId();
                MenuEnum menuEnum = MenuEnum.getMenuById(parentId);
                GspMenuVo vo = transformVo(menu);
                multimap.put(menuEnum,vo);

            });
        }
        Map<MenuEnum,Collection<GspMenuVo>> mu = multimap.asMap();
        List<GspMenuVo> vo = Lists.newArrayList();
        for (Map.Entry<MenuEnum,Collection<GspMenuVo>> entry : mu.entrySet()) {
            MenuEnum menuEnum = entry.getKey();
            GspMenuVo vo1 = new GspMenuVo(menuEnum.getId()+ "",menuEnum.getName());
            vo1.setChildren(entry.getValue());
            vo.add(vo1);
        }


        return vo;
    }

    /**
     * 转换2层
     */
    private GspMenuVo transformVo(GspMenu gm){

            Map<String, String> map = new HashMap<>();
            GspMenuVo gmv = new GspMenuVo(gm.getId().toString(), gm.getMenuName());
            map.put("url", gm.getMenuUrl());
            gmv.setAttributes(map);


        return gmv;
    }


}
