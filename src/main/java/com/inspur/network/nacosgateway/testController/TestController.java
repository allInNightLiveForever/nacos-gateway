package com.inspur.network.nacosgateway.testController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nacos-gateway
 * @description: 测试使用的controller层
 * @author: maqian
 * @create: 2019-08-31 10:10
 **/
@RestController
@RefreshScope
public class TestController {

    @Value("${user1.name}")
    private String userName;

    @Value("${user1.age}")
    private String userAge;

    @RequestMapping(value = "/echo1/get", method = RequestMethod.GET)
    public String getNacosConfigData() {
        return userName + ":" + userAge;
    }
}
