package com.gsp.app.constant;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * fengzhix.xu on 2018/5/19.
 */
@Getter
@AllArgsConstructor
public enum MenuEnum {
    one(1, "报表管理"),
    two(2, "采购管理"),
    three(3, "基础管理"),
    four(4, "库存管理"),
    five(5, "系统管理"),
    six(6, "销售管理");
    private int id;
    private String name;


    public static List<Integer> getParentMenu() {
        List<Integer> ids = Lists.newArrayList();
        for (MenuEnum menuEnum : MenuEnum.values()) {
            ids.add(menuEnum.getId());
        }
        return ids;
    }


    public static MenuEnum getMenuById(int id) {
        for (MenuEnum menuEnum : MenuEnum.values()) {
            if (menuEnum.id == id) {
                return menuEnum;
            }
        }
        return null;
    }

    public static String format(MenuEnum menuEnum) {
        Map<String,String> map = Maps.newHashMap();
        map.put(menuEnum.getId() + "",menuEnum.getName());
        return JSONObject.toJSONString(map);
    }

    public static void main(String[] args) {
        System.out.println(format(MenuEnum.five));
    }
}
