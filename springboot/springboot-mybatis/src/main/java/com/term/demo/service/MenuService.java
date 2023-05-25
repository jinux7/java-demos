package com.term.demo.service;

import com.term.demo.mapper.MenuMapper;
import com.term.demo.resp.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;
    public ArrayList<Menu> getMenu(){
    return menuMapper.getMenu();
    }
}
