package com.postgraduate.dao;

import com.postgraduate.bean.Msg;
import com.postgraduate.bean.Teacher;
import com.postgraduate.entity.MsgEntity;
import com.postgraduate.entity.TeacherEntity;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/11/8.
 */
public class TeacherDAO {
    private Session session = null;

    public List<Msg> getMsgs() {
        List<Msg> msgs = new ArrayList<>();
        Msg msg = null;
        session = HibernateSessionFactory.getSession();

        MsgEntity msgEntity = (MsgEntity)session.get(MsgEntity.class,1);
        msg = new Msg();
        msg.setMain(msgEntity.getMain());

        session.close();
        return msgs;
    }

    public TeacherEntity getTeacherInf(int id) {
        String hql = "from TeacherEntity where teaId=?";
        session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql);
        query.setParameter(0, id);
        List<TeacherEntity> teas = query.list();
        return teas.isEmpty() ? null : teas.get(0);
    }

}
