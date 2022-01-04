package com.luntron;

import com.luntron.config.NotificationFactory;
import com.luntron.config.SendHandler;
import com.luntron.entity.RequestData;
import com.luntron.entity.ResponseResult;
import com.luntron.handler.DispatchHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


/**
 * @author luntron
 * @description 启动类
 * @date 2022/1/2 2:34
 */
@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
public class DesignPatternApplyApp {
    public static void main(String[] args) {
        SpringApplication.run(DesignPatternApplyApp.class, args);
    }

    @Autowired
    private DispatchHandler dispatchHandler;

    /**
     * 单类型发发送  站内信、短信、邮件
     */
    @RequestMapping("/send")
    public ResponseResult sendMessage() {
        RequestData request = new RequestData().setCode(SendHandler.sms.getCode()).setProviderCode(NotificationFactory.SmsWeiWang.getProviderCode());
        dispatchHandler.getHandler(request.getCode()).send(request);
        return new ResponseResult();
    }

    /**
     * 多类型发送
     */
    @RequestMapping("/type/send-way/1")
    public ResponseResult sendAllTypeMessage() {
        RequestData request = new RequestData().setCode(0b010).setProviderCode(NotificationFactory.SmsWeiWang.getProviderCode());
        RequestData request2 = new RequestData().setCode(0b001).setProviderCode(NotificationFactory.MailTencent.getProviderCode());
        List<RequestData> requestData = Arrays.asList(request, request2);
        requestData.forEach(r -> dispatchHandler.getHandler(r.getCode()).send(r));
        return new ResponseResult();
    }

}
