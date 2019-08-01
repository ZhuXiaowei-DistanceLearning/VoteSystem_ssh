package com.zxw.web;

import com.zxw.auth.entity.UserInfo;
import com.zxw.auth.utils.JwtUtils;
import com.zxw.config.JwtProperties;
import com.zxw.service.UserService;
import com.zxw.service.VoteService;
import com.zxw.util.CookieUtils;
import com.zxw.util.JsonUtils;
import com.zxw.vo.mainVo;
import com.zxw.web.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainVoAction extends BaseAction<mainVo> {
    @Autowired
    private JwtProperties properties;
    @Autowired
    private VoteService voteService;

    public String mainVote() throws Exception {
        String value = CookieUtils.getCookieValue(ServletActionContext.getRequest(), properties.getCookieName());
        UserInfo info = JwtUtils.getInfoFromToken(value, properties.getPublicKey());
        String userId = ServletActionContext.getRequest().getParameter("userId");
        String subjectId = ServletActionContext.getRequest().getParameter("subjectId");
        if (!userId.equals(String.valueOf(info.getId()))) {
            return NONE;
        }
        List<mainVo> userList2 = voteService.mainVote(String.valueOf(info.getId()), getModel().getSubjectId());
        writeList2Json(userList2);
        return NONE;
    }

    public String updateVoteInfo() {
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
        mainVo voList = null;
        voList = JsonUtils.parse(sb.toString(), mainVo.class);
        try {
            voteService.updateVoteInfo(voList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }
}
