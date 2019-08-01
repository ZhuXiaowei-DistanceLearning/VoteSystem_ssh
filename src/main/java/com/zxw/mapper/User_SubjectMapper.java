package com.zxw.mapper;

import com.zxw.pojo.Option;
import com.zxw.pojo.Subject;
import com.zxw.pojo.User;
import com.zxw.pojo.User_Subject;
import com.zxw.util.DBUtil;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class User_SubjectMapper extends BaseDaoImpl<User_Subject> {
    public List<User_Subject> querySubjectByUser(String id) {
        String s = "SELECT DISTINCT u.`id`AS uid,u.`username`,u.`version`,s.`beginTime`,s.`endTime`,s.`title`,s.`id` AS sid,s.`type`,us.`status`,s.`total` FROM `user` u,`user_subject` us,`subject` s WHERE u.`id` = us.`userId` AND us.`subjectId` = s.`id`";
        NativeQuery query = this.getSessionFactory().openSession().createSQLQuery(s);
        List list = query.list();
        List<User_Subject> us = new ArrayList<>();
        for (Object o : list) {
            Object[] arr = (Object[]) o;
            System.out.println(o);
            User user = new User();
            Subject subject = new Subject();
            User_Subject user_Subject = new User_Subject();
            // ------------
            user_Subject.setUserId((String) arr[0]);
            user.setUsername((String) arr[1]);
            user.setVersion((Integer) arr[2]);
            subject.setBeginTime((String) arr[3]);
            subject.setEndTime((String) arr[4]);
            subject.setTitle((String) arr[5]);
            user_Subject.setSubjectId((String) arr[6]);
            subject.setType((Integer) arr[7]);
            user_Subject.setStatus((Integer) arr[8]);
            subject.setTotal((Integer) arr[9]);
            user_Subject.setUser(user);
            user_Subject.setSubject(subject);
            // -------------
            us.add(user_Subject);
        }
        return us;
    }

    public List<User_Subject> selectSubjectByUser(String id) {
        List<User_Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM `user_subject` us,`subject` s WHERE us.`subjectId` = s.`id` AND us.`userId` = ?";
        logger.info(sql);
        NativeQuery query = this.getSessionFactory().openSession().createSQLQuery(sql);
        query.setParameter(1, id);
        List o = query.list();
        for (Object obj : o) {
            Object[] arr = (Object[]) obj;
            Subject subject = new Subject();
            User_Subject user_Subject = new User_Subject();
            // ------------
            subject.setTitle((String) arr[4]);
            subject.setType((Integer) arr[5]);
            subject.setBeginTime((String) arr[6]);
            subject.setEndTime((String) arr[7]);
            subject.setTotal((Integer) arr[8]);
            user_Subject.setSubject(subject);
            // -------------
            user_Subject.setUserId((String) arr[0]);
            user_Subject.setSubjectId((String) arr[1]);
            user_Subject.setStatus((Integer) arr[2]);
            list.add(user_Subject);
        }
        return list;
    }

    public User_Subject queryVoteBySubjectAndOption(String userId, String subjectId) {
        String sql = "select distinct s.`id` sid,s.`beginTime`,s.`endTime`,s.`total`,s.`title`,s.`type`,op.`id` oid,op.`name`,op.`tp_order`,us.`status` from `user` u,`user_subject` us,`subject` s,`tb_option` op "
                + "where us.`subjectId` = s.`id` " + "and us.`userId` = u.`id` " + "and s.`id`=op.`subjectId` "
                + "and u.`id`=? and s.`id`=?;";
        NativeQuery query = this.getSessionFactory().openSession().createSQLQuery(sql);
        query.setParameter(1, userId);
        query.setParameter(2, subjectId);
        List list = query.list();
        User_Subject user_Subject = new User_Subject();
        List<User_Subject> us = new ArrayList<>();
        for (Object o : list) {
            Object[] arr = (Object[]) o;
            Subject subject = new Subject();
            Option option = new Option();
            // ------------
            subject.setBeginTime((String) arr[1]);
            subject.setEndTime((String) arr[2]);
            subject.setTotal((Integer) arr[3]);
            subject.setTitle((String) arr[4]);
            subject.setType((Integer) arr[5]);
            option.setId((String) arr[6]);
            option.setName((String) arr[7]);
            option.setOrder((Integer) arr[8]);
//            user_Subject.setOptions(option);
            // -------------
            user_Subject.setSubject(subject);
            user_Subject.setSubjectId((String) arr[0]);
            user_Subject.setStatus((Integer) arr[9]);
            us.add(user_Subject);
        }
        return user_Subject;
    }
}
