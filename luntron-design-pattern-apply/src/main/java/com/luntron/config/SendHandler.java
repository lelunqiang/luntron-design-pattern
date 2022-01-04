package com.luntron.config;

/**
 * @author luntron
 * @var userHandlerCode 用户的消息推送开关值,初始值为0
 * @var code            当前枚举的code值,code值为2的n次方倍
 * @var 0b              表示数的格式为二进制格式
 * @var 0b001           表示2的0次方
 * @description 假如一个用户同时开启了邮件推送   userHandlerCode=userHandlerCode | 0b001  ,以此类推
 * 如果用户关闭了邮件推送          userHandlerCode=userHandlerCode ^ 0b001  ,以此类推
 * 是否推送消息判断              userHandlerCode & code = code  条件成立就推送消息
 * @date 2022/1/2 2:58
 */

public enum SendHandler {
    //0b001 = 2>>1
    mail(2 >> 1, "mailHandler", "邮件"),
    //0b010 = 2
    sms(2, "smsHandler", "短信"),
  
    app(2 << 1, "appHandler", "站内信"),

    other(2 << 10, "otherHandler", "other"),
    ;
    private Integer code;
    private String beanName;
    private String name;

    public Integer getCode() {
        return code;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getName() {
        return name;
    }

    SendHandler(Integer code, String beanName, String name) {
        this.code = code;
        this.beanName = beanName;
        this.name = name;
    }
}
