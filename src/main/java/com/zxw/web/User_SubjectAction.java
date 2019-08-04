package com.zxw.web;

import com.zxw.auth.entity.UserInfo;
import com.zxw.auth.utils.JwtUtils;
import com.zxw.config.JwtProperties;
import com.zxw.mapper.BaseDaoImpl;
import com.zxw.pojo.User_Subject;
import com.zxw.service.UserService;
import com.zxw.service.VoteService;
import com.zxw.util.CookieUtils;
import com.zxw.util.JsonUtils;
import com.zxw.vo.voteInfo;
import com.zxw.web.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.Serializers;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class User_SubjectAction extends BaseAction<User_Subject> {

    private String subjectId;
    private String status;
    private String radios;

    /**
     * 页面表达式需要使用get
     *
     * @return
     */
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRadios() {
        return radios;
    }

    public void setRadios(String radios) {
        this.radios = radios;
    }

    @Autowired
    VoteService voteService;

    @Autowired
    JwtProperties properties;

    public String findAll() throws IOException {
        String value = CookieUtils.getCookieValue(ServletActionContext.getRequest(), properties.getCookieName());
        UserInfo info = null;
        try {
            info = JwtUtils.getInfoFromToken(value, properties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<User_Subject> list = voteService.findAll(info.getId());
        writeList2Json(list);
        return NONE;
    }

    public String querySubjectByUser() throws Exception {
        String value = CookieUtils.getCookieValue(ServletActionContext.getRequest(), properties.getCookieName());
        UserInfo info = null;
        info = JwtUtils.getInfoFromToken(value, properties.getPublicKey());
        List<User_Subject> userList = voteService.selectSubjectByUser(info.getId());
        writeList2Json(userList);
        return NONE;
    }

    public String updateOptionStatus() throws Exception {
        String value = CookieUtils.getCookieValue(ServletActionContext.getRequest(), properties.getCookieName());
        UserInfo info = null;
        info = JwtUtils.getInfoFromToken(value, properties.getPublicKey());
        voteService.updateOptionStatus(getModel().getSubjectId(), String.valueOf(info.getId()), String.valueOf(getModel().getStatus()));
        return NONE;
    }

    public String insertItem() throws Exception {
        String cookieValue1 = CookieUtils.getCookieValue(ServletActionContext.getRequest(), properties.getCookieName());
        UserInfo userInfo = JwtUtils.getInfoFromToken(cookieValue1, properties.getPublicKey());
        try {
            voteService.insertItem(userInfo.getId(),radios,getModel().getSubjectId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        writePageBean2Json("success");
        return NONE;
    }
}
