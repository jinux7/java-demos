package com.term.demo.mapper;

import com.term.demo.model.Login;
import com.term.demo.model.PageParam;
import com.term.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> getAllUsers();

    User getUserById(int id);
    ArrayList<User> getUserList(PageParam pageParam);

    ArrayList<User> getAllUserList();

    void addUser(User user);

    void updateUser(User user);

    void delUserById(int id);

    ArrayList<User> getUserListByLogin(Login login);
    ArrayList<User> getUserListByName(User user);
}
