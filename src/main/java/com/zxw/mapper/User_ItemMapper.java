package com.zxw.mapper;

import com.zxw.pojo.User_Item;
import com.zxw.util.DBUtil;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class User_ItemMapper extends BaseDaoImpl<User_Item> {

    public List<User_Item> queryResult(String subjectId) {
        Connection connection = DBUtil.getConnection();
        List<User_Item> list = new ArrayList<>();
        String sql = "SELECT DISTINCT s.id sid,s.`total`,s.`title`,s.`type`,s.`beginTime`,s.`endTime`,tp.`id` oid,tp.`name`,tp.`tp_order`,it.`id` ,ui.`userId` uid,COUNT(tp.`name`) num FROM `user_item` ui,`subject` s,`tb_option` tp,`item` it \r\n" +
                "WHERE tp.`subjectId` = s.`id` AND s.`id` = ? \r\n" +
                "AND it.`subjectId`=s.`id` AND it.`optionId` = tp.`id` AND ui.`itemId` = it.`id` GROUP BY tp.`name`";
        logger.info(sql);
        NativeQuery query = this.getSessionFactory().openSession().createSQLQuery(sql);
        query.setParameter(1, subjectId);
        List list1 = query.list();
        for (Object o : list1) {
            Object[] arr = (Object[]) o;
            User_Item user_Item = new User_Item();
            user_Item.setSubjectId((String) arr[0]);
            user_Item.setUser_num((Integer) arr[1]);
            user_Item.setTitle((String) arr[2]);
            user_Item.setType((Integer) arr[3]);
            user_Item.setBeginTime((String) arr[4]);
            user_Item.setEndTime((String) arr[5]);
            user_Item.setOptionId((String) arr[6]);
            user_Item.setName((String) arr[7]);
            user_Item.setOrder((Integer) arr[8]);
            user_Item.setItemId((String) arr[9]);
            user_Item.setUserId((String) arr[10]);
            user_Item.setNum(Integer.valueOf(String.valueOf((BigInteger) arr[11])));
            list.add(user_Item);
        }
        return list;
    }

}
