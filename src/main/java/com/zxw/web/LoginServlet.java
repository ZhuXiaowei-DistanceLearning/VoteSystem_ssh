package com.zxw.web;

import com.zxw.auth.entity.UserInfo;
import com.zxw.auth.utils.JwtUtils;
import com.zxw.auth.utils.RsaUtils;
import com.zxw.config.JwtProperties;
import com.zxw.pojo.User;
import com.zxw.service.UserService;
import com.zxw.util.CookieUtils;
import com.zxw.util.JsonUtils;
import com.zxw.util.Logger;
import com.zxw.vo.MenuList;
import com.zxw.vo.menuDB;
import com.zxw.web.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Scope("prototype")
public class LoginServlet extends BaseAction<User> {
    //	UserService userService = new UserService();
    Logger logger = new Logger(LoginServlet.class);
    //	private JwtProperties properties = new JwtProperties();
    @Autowired
    private JwtProperties properties;
    @Autowired
    private UserService userService;

    public String login() throws IOException {
        String token = userService.login(getModel().getUsername(), getModel().getPassword(), properties);
        CookieUtils.setCookie(ServletActionContext.getRequest(), ServletActionContext.getResponse(),
                properties.getCookieName(), token, properties.getCookieMaxAge(), null, true);
        writePageBean2Json("success");
        return NONE;
    }

    public String verify() throws IOException {
        UserInfo info = verify(ServletActionContext.getRequest());
        User user = userService.findById(info.getId());
        if (info != null) {
            if (user.getVersion() == 1) {
                info.setQx("1");
            } else {
                info.setQx("2");
            }
            writePageBean2Json(info);
        } else {
            writePageBean2Json(info);
            throw new RuntimeException();
        }
        return NONE;
    }

    public String findMenu() {
        String cookieValue = CookieUtils.getCookieValue(ServletActionContext.getRequest(), properties.getCookieName());
        try {
            UserInfo userInfo = JwtUtils.getInfoFromToken(cookieValue, properties.getPublicKey());
            User user2 = userService.findById(userInfo.getId());
            if (user2 != null) {
                if (user2.getVersion() == 1) {
                    List<MenuList> list = menuDB.adminMenu();
                    if (list != null) {
                        writeList2Json(list);
                    }
                } else {
                    List<MenuList> list = menuDB.UserMenu();
                    if (list != null) {
                        writeList2Json(list);
                    }
                }
            } else {
                logger.info("用户查找失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }

    public String register() throws IOException {
        User user = getModel();
        user.setVersion(0);
        user.setStatus(1);
        userService.register(user);
        return NONE;
    }

    public UserInfo verify(HttpServletRequest request) {
        String value = CookieUtils.getCookieValue(request, properties.getCookieName());
        try {
            UserInfo info = JwtUtils.getInfoFromToken(value, properties.getPublicKey());
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
