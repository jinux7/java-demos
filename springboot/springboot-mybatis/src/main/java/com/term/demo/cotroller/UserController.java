package com.term.demo.cotroller;

import com.term.demo.model.PageParam;
import com.term.demo.model.User;
import com.term.demo.resp.WrapResult;
import com.term.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {

    @Value("${test.hello:TEST}") // TEST默认值
    private String testHello;
    @Autowired
    private UserService service;
    @GetMapping("/{id}")
    public WrapResult getUser(@PathVariable int id){

        User user = service.getUserById(id);
        return WrapResult.resultSuccess(user);
    }

    @GetMapping("/list")
    public WrapResult getList(PageParam pageParam){
        pageParam.setStart((pageParam.getPageNum()-1)* pageParam.getPageSize());
        pageParam.setEnd(pageParam.getPageSize());
        System.out.println(pageParam.toString());
        ArrayList<User> all = service.getAllUserList();
        ArrayList<User> list = service.getUserList(pageParam);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", all.size());
        map.put("list", list);
        return WrapResult.resultSuccess(map);
    }

    @PostMapping("/add")
    public  WrapResult add(@Valid @RequestBody User user) {
//        String sexy = user.getSexy()=="1"?"男":"女";
//        user.setSexy(sexy);
        service.addUser(user);
        return WrapResult.resultSuccess("添加user成功");
    }

    @PostMapping("/update")
    public  WrapResult update(@Validated @RequestBody User user) {
        service.updateUser(user);
        return WrapResult.resultSuccess("修改成功");
    }

    @GetMapping("/del/{id}")
    public WrapResult delUser(@PathVariable int id){

        service.delUserById(id);
        return WrapResult.resultSuccess("ok");
    }
}
