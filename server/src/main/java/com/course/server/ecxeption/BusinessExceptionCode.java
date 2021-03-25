package com.course.server.ecxeption;

/**
 * @author 田付成
 * @date 2021/3/22 20:05
 */
public enum BusinessExceptionCode {
    USER_LOGIN_NAME_EXIST("登录名已存在"),
    LOGIN_ERROR("用户名或密码不存在"),

    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    LOGIN_MEMBER_ERROR("手机号不存在或密码错误"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
