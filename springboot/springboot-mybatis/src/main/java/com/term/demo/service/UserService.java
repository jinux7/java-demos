package com.term.demo.service;

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

        ArrayList<User> list = userMapper.getUserList(pageParam);
//        System.out.println("list" + list.toString());
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
