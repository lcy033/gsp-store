package com.gsp.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.gsp.app.model.GspUser;
import com.gsp.app.model.ResponseVo;
import com.gsp.app.serives.MyService;
import com.gsp.app.vo.GspMenuVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * fengzhix.xu on 2018/5/19.
 */

@Controller
@RequestMapping("/user")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MyService myService;


    /**
     * 验证登录
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(GspUser user, HttpServletRequest request) {
        try {

            if (checkUser(user)) {
                return ResponseVo.ofErrorMessage("用户或密码不能为空！");
            }

            GspUser u = myService.doLogin(user);
            if (!isLoginSuc(user,u)) {
                return ResponseVo.ofErrorMessage("用户名密码错误！");
            }
            request.getSession().setAttribute("user",u);
            return ResponseVo.ofSuccess(u);

        } catch (Exception e) {
            logger.error("login error", e);
            return ResponseVo.ofErrorMessage();
        }
    }

    @RequestMapping(value = "/treeView")
    @ResponseBody
    public Object index(@RequestParam(value = "id") String id) {
        try {

            if (StringUtils.isNotBlank(id)) {
                List<GspMenuVo> gspMenus = myService.getMenuById(id);
                return CollectionUtils.isEmpty(gspMenus) ?
                        ResponseVo.ofErrorMessage() : JSONObject.toJSONString(ResponseVo.ofSuccess(gspMenus));
            }

        } catch (Exception e) {
            logger.error("query index error", e);
        }
        return ResponseVo.ofErrorMessage();
    }

    @RequestMapping(value = "logout")
    public Object logout(HttpServletRequest request) {
        request.getSession().setAttribute("user",null);
        return "login";
    }


    private boolean checkUser(GspUser user) {
        return user == null
                || StringUtils.isBlank(user.getUser())
                || StringUtils.isBlank(user.getPassword());
    }


    private boolean isLoginSuc(GspUser param,GspUser result) {
        return result != null && StringUtils.endsWithIgnoreCase(result.getPassword(),param.getPassword());
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(ResponseVo.ofSuccess(new GspUser())));
    }


}
