package com.term.demo.cotroller;

import com.term.demo.model.Login;
import com.term.demo.model.PageParam;
import com.term.demo.model.User;
import com.term.demo.resp.Menu;
import com.term.demo.resp.WrapResult;
import com.term.demo.service.LoginService;
import com.term.demo.service.MenuService;
import com.term.demo.service.UserService;
import com.term.demo.util.UUIDUtil;
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
//@RequestMapping("")
@CrossOrigin(origins="*")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private LoginService loginService;
    @Autowired
    private MenuService menuService;
    @PostMapping("/login")
    public  WrapResult login(@Valid @RequestBody Login login) {
        ArrayList<User> list = loginService.getUserListByLogin(login);
        if(!list.isEmpty()) {
            HashMap<String, Object> map = new HashMap<>();
            String uuid = UUIDUtil.getUUID();
            map.put("token", uuid);
            redisCache.setCacheObject(uuid, login.getLoginName(), 3000, TimeUnit.SECONDS);
            return WrapResult.resultSuccess(map);
        }else {
            return WrapResult.resultError("用户名不存在或密码错误");
        }
    }

    // 获取路由menu
    @GetMapping("/getmenu")
    public WrapResult getmenu() {
        ArrayList<Menu> list = menuService.getMenu();
        return WrapResult.resultSuccess(list);
    }

    // 设置redis测试接口
    @GetMapping("/setredis/{value}")
    public WrapResult setredis(@PathVariable Integer value) {
        redisCache.setCacheObject("testNum", value, 10, TimeUnit.SECONDS);
        return WrapResult.resultSuccess("redis设置成功");
    }
    // 获取redis测试接口
    @GetMapping("/getredis")
    public WrapResult getredis() {
//        Object aaa = redisCache.getCacheObject("7cc0d0af77294a068428c8f40ba4de37");
//        log.info("uuid-> {}", aaa);
        Integer sessionObj = redisCache.getCacheObject("testNum");
        return WrapResult.resultSuccess(sessionObj);
    }
}
