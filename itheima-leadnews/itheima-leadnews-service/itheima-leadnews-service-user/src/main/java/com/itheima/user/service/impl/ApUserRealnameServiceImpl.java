package com.itheima.user.service.impl;

import com.itheima.article.feign.ApAuthorFeign;
import com.itheima.article.pojo.ApAuthor;
import com.itheima.common.constants.BusinessConstants;
import com.itheima.media.feign.WmUserFeign;
import com.itheima.media.pojo.WmUser;
import com.itheima.user.mapper.ApUserMapper;
import com.itheima.user.pojo.ApUser;
import com.itheima.user.pojo.ApUserRealname;
import com.itheima.user.mapper.ApUserRealnameMapper;
import com.itheima.user.service.ApUserRealnameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * APP实名认证信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-09-09
 */
@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {

    @Autowired
    private ApUserRealnameMapper apUserRealnameMapper;

    @Autowired
    private WmUserFeign wmUserFeign;

    @Autowired
    private ApUserMapper apUserMapper;

    @Autowired
    private ApAuthorFeign apAuthorFeign;

    @Override
    public void pass(Integer id) {
        //修改数据库中状态值
        ApUserRealname entity = new ApUserRealname();
        entity.setId(id);
        entity.setStatus(BusinessConstants.ApUserRealnameConstants.SHENHE_SUCCESS);
        apUserRealnameMapper.updateById(entity);

        //远程调用自媒体微服务 创建自媒体账号
        WmUser wmUser = new WmUser();
        Integer apUserId = apUserRealnameMapper.selectById(id).getUserId();
        ApUser apUser = apUserMapper.selectById(apUserId);
        BeanUtils.copyProperties(apUser,wmUser);
        wmUser.setApUserId(apUser.getId());

        //如果没有账号则创建账号
        WmUser wmUser1 = wmUserFeign.getByApUserId(apUserId);
        if(wmUser1 == null){
            wmUser = wmUserFeign.save(wmUser).getData();
        }

        //远程调用文章微服务 创建文章作者
        //如果没有账号则创建账号
        ApAuthor apAuthor = apAuthorFeign.getByApUserId(apUserId);

        if (apAuthor == null) {
            apAuthor = new ApAuthor();

            apAuthor.setName(apUser.getName());
            apAuthor.setType(1);
            apAuthor.setCreatedTime(LocalDateTime.now());
            apAuthor.setUserId(apUser.getId());
            apAuthor.setWmUserId(wmUser.getId());
            apAuthorFeign.save(apAuthor);
        }

    }

    @Override
    public void reject(Integer id, String reason) {
        //修改数据库中状态值
        ApUserRealname entity = new ApUserRealname();
        entity.setId(id);
        entity.setStatus(BusinessConstants.ApUserRealnameConstants.SHENHE_FAIL);
        entity.setReason(reason);
        apUserRealnameMapper.updateById(entity);
    }
}
