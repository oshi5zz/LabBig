package com.postgraduate.dao;

import com.postgraduate.bean.Teacher;
import com.postgraduate.entity.TeacherEntity;

import java.sql.SQLException;

/**
 * Created by zhao on 2016/11/8.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAO();
        TeacherEntity teacher = teacherDAO.getTeacherInf(1);
        System.out.println(teacher.getAge());
    }
}