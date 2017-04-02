package com.uchat.dao;

import com.uchat.entity.Student;

import java.util.List;

/**
 * Created by starwill on 2017/3/20.
 */
public interface StudentDao {
    public void update(String u_account);

    public List<Student> getInfo(String course_id);

    public void clear();
}
