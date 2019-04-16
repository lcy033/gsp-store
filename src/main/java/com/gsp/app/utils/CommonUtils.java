package com.gsp.app.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 监控系统常用工具类
 * @author xiaobowen
 *
 */
public class CommonUtils {

    
    public static String getString(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
    
    
    public static List<Long> getLonglist(Object obj){
    	String str = getString(obj);
    	List<Long> list = new ArrayList<Long>();
    	if(StringUtils.isNotBlank(str)){
			if(str.contains(",")){
				String[] strArr = StringUtils.split(str, ",");
				for(int i=0;i<strArr.length;i++){
					list.add(Long.valueOf(strArr[i]));
				}
			}else{
				list.add(Long.valueOf(str));
			}
		}
    	return list;
    }
}
