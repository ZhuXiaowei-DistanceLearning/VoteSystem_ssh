package com.zxw.web;

import com.zxw.auth.entity.UserInfo;
import com.zxw.auth.utils.JwtUtils;
import com.zxw.config.JwtProperties;
import com.zxw.service.VoteService;
import com.zxw.util.CookieUtils;
import com.zxw.util.JsonUtils;
import com.zxw.vo.voteInfo;
import com.zxw.web.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;

public class VoteInfoAction extends BaseAction<voteInfo> {

    @Autowired
    private VoteService voteService;
    @Autowired
    private JwtProperties properties;

    public String insertVote() throws Exception {
        String cookieValue = CookieUtils.getCookieValue(ServletActionContext.getRequest(), properties.getCookieName());
        UserInfo userInfo = JwtUtils.getInfoFromToken(cookieValue, properties.getPublicKey());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = ServletActionContext.getRequest().getReader()) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        voteInfo v = JsonUtils.parse(sb.toString(), voteInfo.class);
        try {
            voteService.insertVote(v, userInfo.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }
}
