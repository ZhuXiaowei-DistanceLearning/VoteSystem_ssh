package com.zxw.mapper;

import com.zxw.pojo.Option;
import com.zxw.pojo.Subject;
import com.zxw.util.DBUtil;
import com.zxw.vo.mainVo;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OptionMapper extends BaseDaoImpl<Option> {

    public List<Option> findBySubjectId(String id) {
        String sql = "SELECT DISTINCT op.`id`,s.`total`,op.`name`,op.`tp_order`,op.`subjectId`,s.`title`,s.`beginTime`,s.`endTime`,s.`type` FROM tb_option op,SUBJECT s WHERE subjectId = ? AND s.`id` = op.`subjectId`;";
        NativeQuery query = this.getSessionFactory().openSession().createSQLQuery(sql);
        query.setParameter(1, id);
        List list = query.list();
        List<Option> options = new ArrayList<>();
        for (Object o : list) {
            Object[] arr = (Object[]) o;
            Option user = new Option();
            Subject subject = new Subject();
            user.setId((String) arr[0]);
            subject.setTotal((Integer) arr[1]);
            user.setName((String) arr[2]);
            user.setOrder((Integer) arr[3]);
            user.setSubjectId((String) arr[4]);
            subject.setTitle((String) arr[5]);
            subject.setBeginTime((String) arr[6]);
            subject.setEndTime((String) arr[7]);
            subject.setType((Integer) arr[8]);
            user.setSubject(subject);
            options.add(user);
        }

        return options;
    }

    public List<mainVo> queryMain(String id) {
        String sql = "SELECT DISTINCT op.`id`,op.`name`,op.`tp_order`,op.`subjectId`,s.`title`,s.`type`,s.`beginTime`,s.`endTime` FROM tb_option op,SUBJECT s WHERE subjectId = ? AND s.`id` = op.`subjectId`;";
        NativeQuery query = this.getSessionFactory().openSession().createSQLQuery(sql);
        query.setParameter(1,id);
        List list = query.list();
        List<mainVo> mainVos = new ArrayList<>();
        for (Object o : list) {
            Object[] arr = (Object[]) o;
            mainVo vo = new mainVo();
            vo.setOptionId((String) arr[0]);
            vo.setName((String) arr[1]);
            vo.setOrder((Integer) arr[2]);
            vo.setSubjectId((String) arr[3]);
            vo.setTitle((String) arr[4]);
            vo.setType((Integer) arr[5]);
            vo.setBeginTime((String) arr[6]);
            vo.setEndTime((String) arr[7]);
            mainVos.add(vo);
        }
        return mainVos;
    }

}
