package com.zxw.web;

import com.zxw.auth.entity.UserInfo;
import com.zxw.config.JwtProperties;
import com.zxw.pojo.Item;
import com.zxw.pojo.Subject;
import com.zxw.pojo.User;
import com.zxw.service.UserService;
import com.zxw.service.VoteService;
import com.zxw.util.JsonUtils;
import com.zxw.util.Logger;
import com.zxw.web.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

@Controller
public class SubjectAction extends BaseAction<Subject> {
    //	UserService userService = new UserService();
    Logger logger = new Logger(SubjectAction.class);
    //	private JwtProperties properties = new JwtProperties();
    @Autowired
    private JwtProperties properties;
    @Autowired
    private VoteService voteService;

}
