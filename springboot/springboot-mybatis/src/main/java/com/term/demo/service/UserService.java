package com.term.demo.service;

import com.github.pagehelper.PageHelper;
import com.term.demo.mapper.UserMapper;
import com.term.demo.model.PageParam;
import com.term.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public User getUserById(int id){
//        System.out.println(id);
        return userMapper.getUserById(id);
    }

    public ArrayList<User> getUserList(PageParam pageParam) {
        // 之前是自己写的分页，现在引入了PageHelper插件，不用自己的了
        // ArrayList<User> list = userMapper.getUserList(pageParam);

        // 使用PageHelper插件
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        ArrayList<User> list = userMapper.getAllUserList();
        return list;
    }

    public ArrayList<User> getAllUserList() {
        ArrayList<User> list = userMapper.getAllUserList();
        return list;
    }

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public void updateUser(User user) {

        userMapper.updateUser(user);
    }

    public void delUserById(int id) {
        userMapper.delUserById(id);
    }
}
