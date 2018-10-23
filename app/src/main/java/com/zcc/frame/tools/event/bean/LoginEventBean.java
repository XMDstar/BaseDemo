package com.zcc.frame.tools.event.bean;

/**
 * @creator : yangshuai
 * @create-time : 2018/8/23 上午10:27
 * @description :登录成功的Event的message
 */
public class LoginEventBean {
    private String succeed;

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public LoginEventBean(String succeed) {

        this.succeed = succeed;
    }
}
