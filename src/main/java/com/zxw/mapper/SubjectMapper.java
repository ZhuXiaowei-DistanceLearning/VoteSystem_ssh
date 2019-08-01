package com.zxw.mapper;

import com.zxw.pojo.*;
import com.zxw.util.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubjectMapper extends BaseDaoImpl<Subject> {
//    public User_Item queryUserIsVote(String id) {
//        String sql = "FROM `user_item` WHERE userId=?0";
//        List<?> list = this.getHibernateTemplate().find(sql, id);
//        return list.size() >= 1 ? (User_Item) list.get(0) : null;
//    }



}
