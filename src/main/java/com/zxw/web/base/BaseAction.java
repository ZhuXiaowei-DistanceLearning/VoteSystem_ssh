package com.zxw.web.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.zxw.util.JsonUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected T model;

    private DetachedCriteria detachedCriteria = null;

    @Override
    public T getModel() {
        return model;
    }

    public BaseAction() {
        System.out.println(this.getClass());
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> tClass = (Class<T>) actualTypeArguments[0];
        detachedCriteria = DetachedCriteria.forClass(tClass);
        try {
            model = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void writePageBean2Json(Object info) throws IOException {
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(JsonUtils.serialize(info));
    }

    public void writeList2Json(List list) throws IOException {
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(JsonUtils.serialize(list));
    }

}
