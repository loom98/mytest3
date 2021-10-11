package com.itheima.user.service;

import com.itheima.user.pojo.ApUserRealname;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * APP实名认证信息表 服务类
 * </p>
 *
 * @author ljh
 * @since 2021-09-09
 */
public interface ApUserRealnameService extends IService<ApUserRealname> {

    //审核通过
    void pass(Integer id);

    //审核失败
    void reject(Integer id, String reason);
}
