package com.zxw.mapper;

import com.zxw.pojo.User;
import com.zxw.util.Logger;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserMapper extends BaseDaoImpl<User> {
    Logger logger = new Logger(UserMapper.class);

    public User login(String id, String password) {
        HibernateTemplate template = this.getHibernateTemplate();
        List<?> list = this.getHibernateTemplate().find("FROM User where id = ?0 and password = ?1", id, password);
        Object o = list.get(0);
        User user = (User) o;
        return list.size() >= 1 ? (User) list.get(0) : null;
    }
}
