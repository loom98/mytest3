package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.admin.mapper.AdUserMapper;
import com.itheima.admin.pojo.AdUser;
import com.itheima.admin.service.AdUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员用户信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-09-07
 */
@Service
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements AdUserService {

}
