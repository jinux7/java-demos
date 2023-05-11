package com.term.demo.cotroller;

import com.term.demo.model.PageParam;
import com.term.demo.model.User;
import com.term.demo.resp.WrapResult;
import com.term.demo.service.UserService;
import com.term.demo.util.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private RedisCache redisCache;
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
//        log.info("pageParam=> {}",pageParam.toString());
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
        ArrayList list = service.getUserListByName(user);
        if(list.isEmpty()) {
            service.addUser(user);
            return WrapResult.resultSuccess("添加user成功");
        }else {
            return WrapResult.resultError("用户名已存在，请换一个");
        }
//        log.info("{}", list);
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
