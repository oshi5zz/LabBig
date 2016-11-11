package com.postgraduate.dao;

import com.postgraduate.entity.Msg;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;
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

        session.close();
        return msgs;
    }

    public List<Student> searchStudents(Student stu) {
        List<Student> students = new ArrayList<>();
        session = HibernateSessionFactory.getSession();
        students.add(
                (Student) session.get(Student.class, 1)
        );
        return students;
    }

    public Teacher getTeacherInf(int id) {
        String hql = "from Teacher where teaId=?";
        session = HibernateSessionFactory.getSession();
        System.out.println(session == null);

        Query query = session.createQuery(hql);
        query.setParameter(0, id);
        List<Teacher> teas = query.list();
        return teas.isEmpty() ? null : teas.get(0);
    }

}
