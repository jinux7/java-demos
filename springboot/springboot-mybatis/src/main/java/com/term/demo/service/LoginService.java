package com.term.demo.service;

import com.term.demo.mapper.UserMapper;
import com.term.demo.model.Login;
import com.term.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public ArrayList<User> getUserListByLogin(Login login) {
        return userMapper.getUserListByLogin(login);
    }
}
