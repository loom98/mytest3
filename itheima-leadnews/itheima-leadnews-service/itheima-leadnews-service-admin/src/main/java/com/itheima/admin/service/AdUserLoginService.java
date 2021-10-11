package com.itheima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.admin.pojo.AdUser;
import com.itheima.admin.pojo.AdUserLogin;
import com.itheima.common.exception.LeadNewsException;

import java.util.Map;

/**
 * <p>
 * 管理员登录行为信息表 服务类
 * </p>
 *
 * @author ljh
 * @since 2021-09-07
 */
public interface AdUserLoginService extends IService<AdUserLogin> {

    Map<String, Object> login(AdUser adUser) throws LeadNewsException;
}
