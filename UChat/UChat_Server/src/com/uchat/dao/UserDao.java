package com.uchat.dao;
import java.util.List;

import com.uchat.entity.User;

/**
 * Created by xuge on 2017/3/9.
 */
public interface UserDao {
    public boolean delete(String u_account);
    public boolean modifytype(String u_account, int u_permission);
    public List<User> select();
    public boolean queryUserByAccount(String u_account);
    public void insertUser(User user);
    public void updateUser(String account,String type,String content);

}
