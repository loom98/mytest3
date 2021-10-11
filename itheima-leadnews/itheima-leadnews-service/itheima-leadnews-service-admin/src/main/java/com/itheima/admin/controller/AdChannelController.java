package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.service.AdChannelService;
import com.itheima.common.exception.GlobalExceptionHandler;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 频道信息表 控制器</p>
* @author ljh
* @since 2021-09-07
*/
@Api(value="频道信息表",tags = "AdChannelController")
@RestController
@RequestMapping("/adChannel")
public class AdChannelController extends AbstractCoreController<AdChannel> {

    private AdChannelService adChannelService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    //注入
    @Autowired
    public AdChannelController(AdChannelService adChannelService) {
        super(adChannelService);
        this.adChannelService=adChannelService;
    }

    //写代码
    @GetMapping("/test")
    public String test(){
        int i = 1/0;

        //公司这么用
        LOGGER.error("错误了,错误的代码是{},产生的结果是{}","代码1","结果2");
        return "hello";
    }

}

