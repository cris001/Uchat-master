package com.uchat.dao;

import com.uchat.entity.Admin;

import java.util.List;

/**
 * Created by xuge on 2017/3/7.
 */
public interface AdminDao {
    public boolean add(Admin admin);
    public boolean delete(int tid);
    public Admin modify(Admin admin, String type);
    public Admin selectbyid(int id);
    public Admin login(String loginname, String loginpsw);
    public List<Admin> select();
}
