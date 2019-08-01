package com.zxw.web;

import com.zxw.auth.entity.UserInfo;
import com.zxw.auth.utils.JwtUtils;
import com.zxw.config.JwtProperties;
import com.zxw.pojo.Item;
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

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class ItemAction extends BaseAction<Item> {
    //	UserService userService = new UserService();
    Logger logger = new Logger(ItemAction.class);
    //	private JwtProperties properties = new JwtProperties();
    @Autowired
    private JwtProperties properties;
    @Autowired
    private UserService userService;

}
