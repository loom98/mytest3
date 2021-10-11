package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/2/23 10:21
 * @description 标题
 * @package com.itheima
 */
@SpringBootApplication
@EnableDiscoveryClient//开启注册和发现
public class GatewayAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAdminApplication.class,args);
    }
}
