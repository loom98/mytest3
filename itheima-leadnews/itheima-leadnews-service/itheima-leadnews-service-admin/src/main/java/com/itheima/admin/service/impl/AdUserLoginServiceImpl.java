package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.admin.mapper.AdUserLoginMapper;
import com.itheima.admin.mapper.AdUserMapper;
import com.itheima.admin.pojo.AdUser;
import com.itheima.admin.pojo.AdUserLogin;
import com.itheima.admin.service.AdUserLoginService;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.util.AppJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 管理员登录行为信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-09-07
 */
@Service
public class AdUserLoginServiceImpl extends ServiceImpl<AdUserLoginMapper, AdUserLogin> implements AdUserLoginService {

    @Autowired
    private AdUserMapper adUserMapper;

    @Override
    public Map<String, Object> login(AdUser adUser) throws LeadNewsException{

        //校验数据是否为空
        if (StringUtils.isEmpty(adUser.getName()) || StringUtils.isEmpty(adUser.getPassword())){
            throw new LeadNewsException("用户名和密码不能为null");
        }
        //根据用户名获取用户信息
        QueryWrapper<AdUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",adUser.getName());
        AdUser adUserFromDb = adUserMapper.selectOne(queryWrapper);

        if(adUserFromDb == null){
            throw new LeadNewsException("用户名或密码错误");
        }
        //校验密码
        //获取用户密码密文
        String passwordFromWeb = DigestUtils.md5DigestAsHex((adUser.getPassword() + adUserFromDb.getSalt()).getBytes());
        String passwordFromDb = adUserFromDb.getPassword();
        if(!passwordFromDb.equals(passwordFromWeb)){
            throw new LeadNewsException("用户名或密码错误");
        }

        //生成令牌 返回数据
        String token = AppJwtUtil.createToken(Long.valueOf(adUserFromDb.getId()));

        Map<String,Object> info = new HashMap<>();
        info.put("token",token);

        //清理不必要的数据
        adUserFromDb.setSalt("");
        adUserFromDb.setPassword("");
        info.put("user",adUserFromDb);

        return info;
    }
}
