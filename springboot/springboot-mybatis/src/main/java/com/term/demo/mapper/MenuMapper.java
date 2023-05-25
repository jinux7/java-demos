package com.term.demo.mapper;

import com.term.demo.model.Login;
import com.term.demo.model.PageParam;
import com.term.demo.model.User;
import com.term.demo.resp.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface MenuMapper {
    ArrayList<Menu> getMenu();
}
