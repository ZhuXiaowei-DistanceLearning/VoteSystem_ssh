package com.zxw.filter;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zxw.auth.entity.UserInfo;
import com.zxw.auth.utils.JwtUtils;
import com.zxw.auth.utils.RsaUtils;
import com.zxw.config.JwtProperties;
import com.zxw.util.CookieUtils;
import com.zxw.util.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class MyFilter extends AbstractInterceptor {
    Logger logger = new Logger(MyFilter.class);

    @Autowired
    private JwtProperties properties;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest req = ServletActionContext.getRequest();
        String pathInfo1 = ServletActionContext.getRequest().getPathInfo();
        String pathInfo = req.getPathInfo();
        String cookieValue = CookieUtils.getCookieValue(req, properties.getCookieName());
        try {
            UserInfo info = JwtUtils.getInfoFromToken(cookieValue, properties.getPublicKey());
            if (info != null) {
                logger.info("身份认证通过，拦截器放行");
                return invocation.invoke();
            } else {
                logger.info("用户认证失败！");
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
