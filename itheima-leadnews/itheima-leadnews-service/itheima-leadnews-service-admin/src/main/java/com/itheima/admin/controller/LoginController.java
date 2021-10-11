package com.itheima.admin.controller;

import com.itheima.admin.pojo.AdUser;
import com.itheima.admin.service.AdUserLoginService;
import com.itheima.admin.service.AdUserService;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private AdUserLoginService adUserLoginService;

    //登陆
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody AdUser adUser) throws LeadNewsException {
        Map<String,Object> mapInfo = adUserLoginService.login(adUser);
        return Result.ok(mapInfo);
    }

    public static void main(String[] args) {
        String password = "123456" + "abc";
        String s = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(s);
    }
}
